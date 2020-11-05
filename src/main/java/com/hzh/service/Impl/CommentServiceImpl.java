package com.hzh.service.Impl;

import com.hzh.mapper.CommentMapper;
import com.hzh.pojo.Comment;
import com.hzh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    // 根据时间排序
    private static void ListSort(List<Comment> tempReplys) {
        tempReplys.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
    }

    // 查找该评论的子评论
    private void getAllChildComment(List<Comment> commentList,Comment comment){
        if(commentList.size()>0) {
            for (Comment c : commentList) {
                c.setParentComment(comment);
                // 获取 一个父评论的直接子评论
                List<Comment> childcommentList = commentMapper.queryChildComment(c.getBlogId(), c.getId());
                tempReplys.addAll(childcommentList);
                getAllChildComment(childcommentList,c);
            }
        }
    }

    // 获取该博客的评论
    @Override
    public List<Comment> getCommentListByBlogId(Long blogId) {
        // 获取父评论
        List<Comment> commentList = commentMapper.getParentCommentListByBlogId(blogId);

        // 获取所有父评论的子评论
        for (Comment comment :commentList){
            // 获取 一个父评论的直接子评论
            List<Comment> childcommentList = commentMapper.queryChildComment(comment.getBlogId(), comment.getId());
            tempReplys.addAll(childcommentList);
            getAllChildComment(childcommentList,comment);

            ListSort(tempReplys);

            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return commentList;
    }

    @Override
    public int insertComment(Comment comment) {
        Long parentCommentId =comment.getParentComment().getId();
        // 等于-1 表示它有父评论，需要获取父评论，将子评论
        if(parentCommentId!=-1){
            comment.setParentCommentId(parentCommentId);
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());

        return commentMapper.insertComment(comment);
    }




}