package com.hzh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.pojo.Type;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.service.BlogService;
import com.hzh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeServiceImpl;
    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum,
                         Long id, Model model){

        // 获取所有的标签
        List<Type> typeList = typeServiceImpl.getListAllTypeShow();
        if(id==null){
            id=typeList.get(0).getId();
        }

        PageHelper.startPage(pageNum,5);
        List<BlogHome> blogListByTypeId = blogServiceImpl.getBlogListByTypeId(id);
        PageInfo<BlogHome> pageInfo = new PageInfo<>(blogListByTypeId);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",typeList);
        model.addAttribute("activeTypeId",id);
        return "/types";
    }

}
