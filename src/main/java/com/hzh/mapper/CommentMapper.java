package com.hzh.mapper;


import com.hzh.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    // 查询该博客下该评论的 所有父评论
    List<Comment> getParentCommentListByBlogId(Long blogId);

    // 查询该博客下 该评论的直接子评论
    List<Comment> queryChildComment(@Param("blog_id") Long blog_id,@Param("parentId") Long parentId);

    // 新增评论
    @Insert(" insert into t_comment(blog_id,parent_comment_id,nick_name,email,admin_comment,content,avatar_address,create_time)\n" +
            "values(#{blog.id},#{parentComment.id},#{nickName},#{email},#{adminComment},#{content},#{avatarAddress},#{createTime} )")
    int insertComment(Comment comment);





}
