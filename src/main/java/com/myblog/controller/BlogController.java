package com.myblog.controller;

import com.myblog.entity.Blog;
import com.myblog.entity.Type;
import com.myblog.entity.User;
import com.myblog.service.BlogService;
import com.myblog.service.TagsService;
import com.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagsService tagsService;

    /*去博客管理页并初始化数据列表*/
    @GetMapping("/blog-manage")
    public String toBlogmanage(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                               Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listType());
        return "admin/blog-manage";
    }
    /*去博客新增页*/
    @GetMapping("/blog-input")
    public String toBlogInputPage(Model model){
        Blog blog = new Blog();
        Type type = new Type();
        Long aLong = new Long(-1);
        type.setId(aLong);
        blog.setType(type);
        model.addAttribute("blog",blog);
        setTypesAndTags(model);
        return "admin/blog-input";
    }
    /*新增一个博客*/
    @PostMapping("/blog-add")
    public String blogAdd(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        Blog blog1 =null;
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagsService.listTag(blog.getTagIds()));
        if(blog.getId()==null){
            blog1 = blogService.saveBlog(blog);
            if(blog1==null){
                redirectAttributes.addFlashAttribute("msg","新增失败");
            }else {
                redirectAttributes.addFlashAttribute("msg","新增成功");
            }

        }else {
            Blog blog2 = blogService.updateBlog(blog.getId(), blog);
            if(blog2==null){
                redirectAttributes.addFlashAttribute("msg","更新失败");
            }else {
                redirectAttributes.addFlashAttribute("msg","更新成功");
            }
        }

        return "redirect:/admin/blog-manage";
    }

    /*删除一篇博客*/
    @GetMapping("/blog/{id}/delete")
    public String delete_blog(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("msg","删除成功!");
        return "redirect:/admin/blog-manage";
    }

    /*展示待编辑的博客*/
    @GetMapping("/blog/{id}/input")
    public String show_Edit(@PathVariable("id") Long id,Model model){
        setTypesAndTags(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blog-input";
    }


    /*条件查询*/
    @PostMapping("/queryConditional")
    public String queryConditional(@RequestParam("title") String title,
                                   @RequestParam("type") Long typeId,
                                   @RequestParam("recommend") Long recommend,
                                    RedirectAttributes redirectAttributes){
        Page<Blog> page = blogService.queryConditional(title, typeId, recommend);
        redirectAttributes.addFlashAttribute("page",page);
        return "redirect:/admin/blog-manage";
    }

    public void setTypesAndTags(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagsService.listTag());
    }
}
