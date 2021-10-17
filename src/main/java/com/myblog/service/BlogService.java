package com.myblog.service;

import com.myblog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huanglong ,chengyanyu
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



    List<Blog> listRecommendBlogTop(Integer size);


    Page<Blog> listTypeBlog(Pageable pageable,Long id);

    /*归档页面展示*/
    Map<String,List<Blog>> archiveBlog();

    Long archiveCount();

}
