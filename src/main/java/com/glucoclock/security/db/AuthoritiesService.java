package com.glucoclock.security.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;


@Service
public class AuthoritiesService extends CrudService<Authorities, Integer> {

    private AuthoritiesRepository repository;

    public AuthoritiesService(@Autowired AuthoritiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthoritiesRepository getRepository() {
        return repository;
    }

}
