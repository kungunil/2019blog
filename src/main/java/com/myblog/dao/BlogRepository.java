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
 * @Author: huanglong
 * @Date: 2021/10/5:29
 * @Description:
 */
public interface BlogRepository extends JpaRepository<Blog, Long> ,JpaSpecificationExecutor<Blog>{

        @Query("select b from Blog b where b.recommend=true ")
        List<Blog> findRecommendTop(Pageable pageable);

        @Query("select b from Blog b where b.type.id = :id")
        Page<Blog> findTypeBlog(Pageable pageable,@Param("id") Long id);


}
