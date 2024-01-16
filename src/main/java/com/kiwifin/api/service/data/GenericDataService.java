package com.kiwifin.api.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericDataService<T, S extends Serializable, E extends JpaRepository<T, S>> {

    @Autowired
    public
    E repository;

    public void save(T tClass) {
        this.repository.save(tClass);
    }

    public void save(List<T> tClass) {
        this.repository.saveAll(tClass);
    }

    public Optional<T> findById(S id) {
        return this.repository.findById(id);
    }

    public T getOne(S id){
        return this.repository.getOne(id);
    }

    public Iterable<T> findAllById(List<S> ids) {
        return this.repository.findAllById(ids);
    }

    public Iterable<T> findAll() {
        return this.repository.findAll();
    }

    public void delete(T tClass) {
        this.repository.delete(tClass);
    }

    public void delete(List<T> tClass) {
        this.repository.deleteAll(tClass);
    }

    public void saveAndFlush(T tClass) {
        this.repository.saveAndFlush(tClass);
    }

    public void flush() {
        this.repository.flush();
    }
}
