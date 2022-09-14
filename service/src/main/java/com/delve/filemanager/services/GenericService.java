package com.delve.filemanager.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface GenericService <T, U> {

    T getById(Long id);

    Page<U> listAll(T obj, Pageable pageable);

    T create(T obj) throws IOException;

    T update(T obj) throws IOException;

    void deleteById(Long id);

    void deleteAll(T obj) throws IOException;
}
