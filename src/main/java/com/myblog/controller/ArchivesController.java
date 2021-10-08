package com.myblog.controller;

import com.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: chengyanyu
 * @Date: 2021/10/5:29
 * @Description:
 */

@Controller
public class ArchivesController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String toArchivePage(Model model){

        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.archiveCount());
        return "archives";
    }

}
