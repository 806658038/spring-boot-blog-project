package com.hzh.controller.admin;

import com.hzh.pojo.User;
import com.hzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userServiceImpl;

    @GetMapping
    public String loginPage(){
        return "/admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        User user = userServiceImpl.userLogin(username,password);
        if(user!=null) {
            user.setPassword(null);
            session.setAttribute("admin", user);
            return "/admin/index";
        }
        attributes.addFlashAttribute("message","用户名或密码错误");

        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin";
    }


}
