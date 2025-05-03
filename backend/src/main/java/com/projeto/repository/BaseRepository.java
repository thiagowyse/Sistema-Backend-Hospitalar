package com.projeto.repository;


import java.util.List;

public interface BaseRepository <T, Long>{

    T insert(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity) throws Exception;

    void delete(Long id);
}
