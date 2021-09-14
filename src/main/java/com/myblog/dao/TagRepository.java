package com.myblog.dao;

import com.myblog.entity.Tag;
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
public interface TagRepository extends JpaRepository<Tag,Long> {
        Tag findByName(String name);

        @Query("select t from Tag t")
        List<Tag> findTop(Pageable pageable);

}
