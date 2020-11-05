package com.hzh.service;

import com.hzh.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentListByBlogId(Long blogId);

    int insertComment(Comment comment);


}
