package com.hzh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.pojo.Blog;
import com.hzh.pojo.User;
import com.hzh.pojo.dto.BlogQuery;
import com.hzh.pojo.dto.BlogShow;
import com.hzh.service.BlogService;
import com.hzh.service.TagService;
import com.hzh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    BlogService blogServiceImpl;
    @Autowired
    TypeService typeServiceImpl;
    @Autowired
    TagService tagServiceImpl;

    // 博客列表
    @RequestMapping("/show")
    public String types(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum, Model model){
        int pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        List<BlogQuery> list = blogServiceImpl.getFewListBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeServiceImpl.getListType());
        return "/admin/blog";
    }

    // 搜索博客
    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "0",value = "pageNum") Integer pageNum,
                         BlogQuery blogQuery,
                         Model model){
        int pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        List<BlogQuery> list = blogServiceImpl.searchBlog(blogQuery);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "/admin/blog :: blogList";
    }

    // 跳转新增博客界面
    @RequestMapping("/addPage")
    public String addPage(Model model){
        getListTypeAndTag(model);
        return "/admin/blog-add";
    }

    // 新增博客
    @RequestMapping("/add")
    public String insertBlog(Blog blog ,RedirectAttributes attributes,HttpSession session){
        User user = (User) session.getAttribute("admin");
        blog.setUser(user);
        blog.setType(typeServiceImpl.queryOneType(blog.getTypeId()));
        System.out.println(blog);


        int i = blogServiceImpl.insertBlog(blog);
        if(i>=1 ){
            attributes.addFlashAttribute("success","添加成功");
        }else{
            attributes.addFlashAttribute("fail","添加失败");
        }
        return "redirect:/admin/blog/show";
    }

    // 删除博客
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable  Long id,
                         RedirectAttributes attributes){
        int i = blogServiceImpl.deleteBlogById(id);
        if(i==1){
            attributes.addFlashAttribute("success","删除成功");
        }else{
            attributes.addFlashAttribute("fail","删除失败");
        }
        return "redirect:/admin/blog/show";
    }

    // 跳转更新博客界面
    @GetMapping("/{id}/updatePage")
    public String updatePage(@PathVariable Long id,Model model){
        BlogShow blogShow=blogServiceImpl.getBlogShowList(id);

        model.addAttribute("blog",blogShow);
        model.addAttribute("tags",tagServiceImpl.getListTag());
        model.addAttribute("types",typeServiceImpl.getListType());
        return "/admin/blog-edit";
    }

    // 更新博客
    @PostMapping("/update")
    public String update(Blog blog,RedirectAttributes attributes){
        int i = blogServiceImpl.updateBlog(blog);
        if(i==1){
            attributes.addFlashAttribute("success","更新成功");
        }else{
            attributes.addFlashAttribute("fail","更新失败");
        }
        return "redirect:/admin/blog/show";
    }

    void getListTypeAndTag(Model model){
        model.addAttribute("tags",tagServiceImpl.getListTag());
        model.addAttribute("types",typeServiceImpl.getListType());
    }

}
