package com.hzh.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.pojo.Tag;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.service.BlogService;
import com.hzh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    TagService tagServiceImpl;
    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum,
                       Long id, Model model){

        // 获取所有的标签(包含博客的个数)
        List<Tag> tagList = tagServiceImpl.getAllTagList();

        if(id==null){
            id=tagList.get(0).getId();
        }
        PageHelper.startPage(pageNum,5);
        List<BlogHome> blogListByTypeId = blogServiceImpl.getBlogListByTagId(id);

        PageInfo<BlogHome> pageInfo = new PageInfo<>(blogListByTypeId);

        System.out.println("--------------");
        System.out.println("pageInfo="+pageInfo);
        System.out.println("----------");

        model.addAttribute("tagList",tagList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",id);
        return "/tags";
    }



}
