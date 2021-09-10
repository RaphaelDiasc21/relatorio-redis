package org.first;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;

@Path("/redis")
public class RelatorioResource {

    @Inject
    RedisClient redisClient;

    @Inject
    DadoRepository dadoRepository;

    @GET
    @Path("/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKey(@PathParam("key") String key) {
        return redisClient.get(key);
    }


    @POST
    @Path("/{key}")
    public long setValues(@PathParam("key") String key) {
        long startTime = System.currentTimeMillis();
        for(int i =0; i < 1000; i++) {
            redisClient.set(Arrays.asList(key, String.valueOf(i)));
        }

        return System.currentTimeMillis() - startTime;
    }


    @POST
    @Path("/mysql/{key}")
    @Transactional
    public long setMysqlValues(@PathParam("key") String key) {
        long startTime = System.currentTimeMillis();
        for(int i =0; i < 1000; i++) {
            dadoRepository.persistAndFlush(Dado.builder().value("Banco entidade " + i).build());
        }

        return System.currentTimeMillis() - startTime;
    }

}