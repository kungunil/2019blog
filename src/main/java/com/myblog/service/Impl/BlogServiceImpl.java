package com.myblog.service.Impl;


import com.myblog.dao.BlogMapper;
import com.myblog.dao.BlogRepository;
import com.myblog.entity.Blog;
import com.myblog.exception.NotFoundException;
import com.myblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huanglong
 * @Date: 2021/10/5:29
 * @Description:
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogMapper blogMapper;


    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Transactional
    @Override
    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }
    @Transactional
    @Override
    public Blog updateBlog(long id, Blog blog) {
        Blog byId = blogRepository.getById(id);
        if(byId==null){
            throw  new NotFoundException("更新的博客不存在");
        }

        blog.setCreateTime(byId.getCreateTime());
        blog.setViews(byId.getViews());

        BeanUtils.copyProperties(blog,byId);
        return blogRepository.save(byId);
    }

    @Override
    public Page<Blog> queryConditional(String title, Long typeId, Long recommend) {
        List<Blog> blogs = blogMapper.queryConditional(title, typeId, recommend);
        Page<Blog> page = new Page<Blog>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super Blog, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Blog> getContent() {
                return blogs;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Blog> iterator() {
                return null;
            }
        } ;
        return page;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.findRecommendTop(pageable);
    }



    @Override
    public Page<Blog> listTypeBlog(Pageable pageable, Long id) {
        return blogRepository.findTypeBlog(pageable,id);
    }


}
