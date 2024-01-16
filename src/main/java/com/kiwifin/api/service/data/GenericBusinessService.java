package com.kiwifin.api.service.data;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericBusinessService<E,F> {

    @Autowired
    protected E conversorService;

    @Autowired
    protected F dataService;

    public GenericBusinessService() {
        super();
    }
}
