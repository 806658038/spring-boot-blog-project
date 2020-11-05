package com.hzh.controller;


import com.hzh.service.ArchiveService;
import com.hzh.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveShowController {

    @Autowired
    ArchiveService archiveServiceImpl;
    @Autowired
    BlogService blogServiceImpl;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",archiveServiceImpl.archiveBlog());
        model.addAttribute("blogCount",blogServiceImpl.countBlog());
        return "/archives";
    }





}
