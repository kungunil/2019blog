package com.myblog.controller;

import com.myblog.entity.Tag;
import com.myblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 潘金龙
 * @Date: 2021/10/7/12:29
 * @Description:
 */

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagsService tagsService;

    /*去Tags管理页*/
    @GetMapping("/tags-manage")
    public String toAminTagsPage(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                                  Model model){
        model.addAttribute("page",tagsService.listTag(pageable));
        return "admin/tags-manage";
    }

    /*去标签新增页面*/
    @GetMapping("/tags-input")
    public String toTagsInputPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
    /*新增一个标签*/
    @PostMapping("/tags-add")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tagByName = tagsService.getTagByName(tag.getName());
        if(tagByName!=null){
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag1  = tagsService.saveTag(tag);
        if(tag1==null){
            attributes.addFlashAttribute("msg","很遗憾,新增失败了!");
        }else {
            attributes.addFlashAttribute("msg","恭喜,新增成功!");
        }
        return "redirect:/admin/tags-manage";
    }

    /*标签编辑显示在页面上*/
    @GetMapping("/tags/{id}/input")
    public String editorTag(@PathVariable long id, Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tags-input";
    }
    /*编辑一个类型*/
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable long id, RedirectAttributes attributes){
        Tag tagByName = tagsService.getTagByName(tag.getName());
        if(tagByName!=null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if(result.hasErrors()){

            return "admin/tags-input";
        }
        Tag tag1 = tagsService.updateTag(id,tag);
        if(tag1==null){
            attributes.addFlashAttribute("msg","很遗憾,更新失败了!");
        }else {
            attributes.addFlashAttribute("msg","恭喜,更新成功!");
        }
        return "redirect:/admin/tags-manage";
    }
    @GetMapping("/tags/{id}/delete")
    public String tagDelete(@PathVariable long id,RedirectAttributes redirectAttributes){
        tagsService.deleteTag(id);
        redirectAttributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/tags-manage";
    }
}
