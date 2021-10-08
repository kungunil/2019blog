package com.myblog.dao;

import com.myblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huanglong , chenyanyu
 * @Date: 2021/10/5:29
 * @Description:
 */
public interface BlogRepository extends JpaRepository<Blog, Long> ,JpaSpecificationExecutor<Blog>{

        @Query("select b from Blog b where b.recommend=true ")
        List<Blog> findRecommendTop(Pageable pageable);

        @Query("select b from Blog b where b.type.id = :id")
        Page<Blog> findTypeBlog(Pageable pageable,@Param("id") Long id);

        //归档：陈彦宇
        @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
        List<String> findGroupYear();

        @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
        List<Blog> findByYear(String year);

        @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
        Page<Blog> findByQuery(Pageable pageable,String query);
}
