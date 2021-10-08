package com.myblog.service;

import com.myblog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: chent
 * @Date: 2021/10/07/15:26
 */
public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Page<Type> listType(Pageable pageable);
    Type updateType(Long id,Type type);
    void deleteType(Long id);
    Type getTypeByName(String name);

    List<Type> listType();
    List<Type> listTypeTop(Integer size);
}
