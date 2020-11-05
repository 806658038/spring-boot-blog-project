package com.hzh.mapper;


import com.hzh.pojo.Blog;
import com.hzh.pojo.Tag;
import com.hzh.pojo.dto.BlogShow;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogAndTagMapper {

    // 博客 与标签 新增
    int insertBlogAndTags(@Param("blogId") Long blogId, List<Long> tagsIdList);
    
    @Delete("delete from t_blog_tags where blog_id=#{0}")
    int deleteBlogAndTagsByBolgId(Long blogId);

    List<Tag> getListBlogAndTag(Long blog_id);


    @Select("select t.id,t.title\n" +
            "from t_blog t,t_blog_tags tb\n" +
            "where t.id=tb.blog_id and tb.tag_id=#{tag_id}")
    List<Blog> getListBlogByTagId(@Param("tag_id") Long tag_id);


}
