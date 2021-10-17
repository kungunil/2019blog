package com.myblog.controller;

import com.myblog.dao.BlogRepository;
import com.myblog.entity.Blog;
import com.myblog.entity.Tag;
import com.myblog.entity.Type;
import com.myblog.service.BlogService;
import com.myblog.service.TagsService;
import com.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: heyuqiao
 * @Date: 2021/10/17/17:16
 * @Description:
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogRepository blogRepository;
    /*去首页*/
    @GetMapping("/")
    public String index(@PageableDefault(size = 2,sort = {"views"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagsService.listTagTop(6));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(6));
        return "index";
    }
    /*去分类页*/
    @GetMapping("/types/{id}")
    public String toTypesPage(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                              Model model,@PathVariable("id") Long id){
        List<Type> types = typeService.listTypeTop(10000);
        if (id==-1){
            id = types.get(0).getId();
        }
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listTypeBlog(pageable,id));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
   /* 标签页*/
    @GetMapping("/tags/{id}")
    public String toTagPage(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                              Model model,@PathVariable("id") Long id){
        List<Tag> tags = tagsService.listTagTop(10000);
        if (id==-1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(pageable,id));
        model.addAttribute("activeTagId",id);
        return "tags";
    }

    /*去博客详情页*/
    @GetMapping("/blogs/{id}")
    public String toDetailPage(@PathVariable("id") Long id,Model model){
        Blog blog = blogService.getBlog(id);
        if(blog!=null){
            blog.setViews(blog.getViews()+1);
            blogService.saveBlog(blog);
            model.addAttribute("blog",blog);
        }else {
            model.addAttribute("msg","该博客不存在");
        }
        return "blog";
    }

}
