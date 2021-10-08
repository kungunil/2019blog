package com.myblog.controller;

import com.myblog.entity.User;
import com.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    /*去登录页面*/
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /*判断登录是否成功*/
    @PostMapping("/login-submit")
    public String checkUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password,
                            HttpSession session, RedirectAttributes redirectAttributes){
        User user = userService.checkUser(username, password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/blog-manage";
        }else {
            redirectAttributes.addFlashAttribute("msg","用户名或密码错误!滚回去去检查");
            return "redirect:/admin/login";
        }

    }

    /*注销操作*/
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/login";
    }
}
