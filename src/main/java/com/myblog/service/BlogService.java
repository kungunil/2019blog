package com.myblog.service;

import com.myblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huanglong
 * @Date: 2021/10/5:29
 * @Description:
 */
public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable,Long tagId);

    void deleteBlog(long id);

    Blog updateBlog(long id,Blog blog);

    Page<Blog> queryConditional(String title,Long typeId,Long recommend);

    List<Blog> listRecommendBlogTop(Integer size);


    Page<Blog> listTypeBlog(Pageable pageable,Long id);

}
