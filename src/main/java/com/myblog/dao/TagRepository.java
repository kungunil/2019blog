package com.myblog.dao;

import com.myblog.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 潘金龙
 * @Date: 2021/10/7/15:29
 * @Description:
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
        Tag findByName(String name);

        @Query("select t from Tag t")
        List<Tag> findTop(Pageable pageable);

}
