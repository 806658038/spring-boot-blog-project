package com.hzh.controller;

import com.hzh.pojo.Comment;
import com.hzh.pojo.User;
import com.hzh.service.BlogService;
import com.hzh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentServiceImpl;

    @Autowired
    BlogService blogServiceImpl;

    @Value("${comment.avatar}")
    protected String avatar;

    @RequestMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Long blogId, Model model){
        List<Comment> comments = commentServiceImpl.getCommentListByBlogId(blogId);
        model.addAttribute("comments",comments);

        System.out.println(comments);

        return "blog ::commentList";
    }

    // 发布评论
    @PostMapping("/comments")
    public String addComments(Comment comment, HttpSession session){
        Long blogId=comment.getBlog().getId();
        comment.setBlogId(blogId);
        comment.setBlog(blogServiceImpl.queryOneBlog(blogId));
        User u = (User) session.getAttribute("admin");
        if(u!=null){
            comment.setAvatarAddress(u.getAvatarAddress());
            comment.setAdminComment(true);
        }else{
            comment.setAvatarAddress(avatar);
        }
        commentServiceImpl.insertComment(comment);

        return "redirect:/comments/"+comment.getBlog().getId();
    }



}
