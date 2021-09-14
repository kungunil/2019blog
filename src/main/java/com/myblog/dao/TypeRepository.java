package com.myblog.dao;

import com.myblog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/10/15:29
 * @Description:
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
        Type findByName(String name);

        @Query("select t from Type t")
        List<Type> findTop(Pageable pageable);
}
