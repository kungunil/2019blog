package com.myblog.controller;

import com.myblog.entity.Type;
import com.myblog.service.TypeService;
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
 * @Author: chent
 * @Date: 2021/10/07/17:31
 * @Description:分类信息请求控制类，响应http请求
 */
@Controller
@RequestMapping("/admin")
public class TypesController {

    @Autowired
    private TypeService typeService;

    /*去类型新增页面*/
    @GetMapping("/types-input")
    public String toTypesInputPage(Model model){
        Type type = new Type();

        model.addAttribute("type",type);
        System.out.println("------------------------type:---"+type);
        return "admin/types-input";
    }

    /*去管理类型的页面*/
    @GetMapping("/types-manage")
    public String toAminTypesPage(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                                  Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types-manage";
    }

    /*新增一个类型*/
    @PostMapping("/types-add")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName!=null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if(result.hasErrors()){

            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if(type1==null){
            attributes.addFlashAttribute("msg","很遗憾,新增失败了!");
        }else {
            attributes.addFlashAttribute("msg","恭喜,新增成功!");
        }
        return "redirect:/admin/types-manage";
    }

    /*编辑分类显示在页面上*/
    @GetMapping("/types/{id}/input")
    public String editorType(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }
    /*编辑一个类型*/
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName!=null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if(result.hasErrors()){

            return "admin/types-input";
        }
        Type type1 = typeService.updateType(id,type);
        if(type1==null){
            attributes.addFlashAttribute("msg","很遗憾,更新失败了!");
        }else {
            attributes.addFlashAttribute("msg","恭喜,更新成功!");
        }
        return "redirect:/admin/types-manage";
    }
    @GetMapping("/types/{id}/delete")
    public String TypeDelete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/types-manage";
    }
}
