package org.first;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DadoRepository implements PanacheRepository<Dado> {
}
