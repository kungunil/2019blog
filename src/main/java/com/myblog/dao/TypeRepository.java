package com.myblog.dao;

import com.myblog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: chent
 * @Date: 2021/10/7/15:29
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
        Type findByName(String name);

        @Query("select t from Type t")
        List<Type> findTop(Pageable pageable);
}
