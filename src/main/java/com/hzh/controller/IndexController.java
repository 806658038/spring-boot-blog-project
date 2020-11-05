package com.hzh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.pojo.dto.BlogDetails;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.service.BlogService;
import com.hzh.service.TagService;
import com.hzh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    TypeService typeServiceImpl;
    @Autowired
    TagService tagServiceImpl;

    @RequestMapping("/")
    public String types(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum,
                        Model model){

        // 坑  PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.你放其后面语句都会拦截下来加入 limit
        // 一定要 放在需要分页的sql查询的上面，不然不知道给哪条sql做分页
        PageHelper.startPage(pageNum,5);
        List<BlogHome> blogHomeList = blogServiceImpl.getBlogHomeList();
        PageInfo<BlogHome> pageInfo = new PageInfo<>(blogHomeList);

        System.out.println(blogHomeList);

        model.addAttribute("pageInfo",pageInfo);
        // 推荐博客的数量
        model.addAttribute("recommendBlog",blogServiceImpl.getRecommendBlog(5));
        // 分类的数量
        model.addAttribute("types",typeServiceImpl.getListTypeTop(6));
        // 标签的数量
        model.addAttribute("tags",tagServiceImpl.getSomeTagShow(3));

        return "/index";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum,
                         @RequestParam("query") String query,
                         Model model){

        PageHelper.startPage(pageNum,5);
        List<BlogHome> searchBlogList = blogServiceImpl.getSearchBlogList(query);
        PageInfo<BlogHome> pageInfo = new PageInfo<>(searchBlogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "/search";
    }

    // 博客详情
    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable("id") Long id,
                          Model model){

        BlogDetails blogDetails = blogServiceImpl.getBlogDetails(id);
        blogDetails.setTags(tagServiceImpl.getTagListByBlogId(id));

        System.out.println(blogDetails);
        model.addAttribute("blog",blogDetails);

        return "/blog";
    }

    @GetMapping("/about")
    public String about(){
        return "/about";
    }


    @GetMapping("/footer/newBlog")
    public String newBlog(Model model){
        model.addAttribute("newBlogList",blogServiceImpl.getRecommendBlog(3));

        return "_fragments :: newBlogList";
    }


}
