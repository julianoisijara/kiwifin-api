package com.kiwifin.api.service.conversor;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericConversor<E,D> {

    public List<D> entityList2DtoList(List<E> entityList) {

        List<D> entityListDTO = new ArrayList<>();

        for (E entity : entityList) {
            entityListDTO.add(entity2Dto(entity));
        }

        return entityListDTO;
    }

    public abstract D entity2Dto(E entity);
}

