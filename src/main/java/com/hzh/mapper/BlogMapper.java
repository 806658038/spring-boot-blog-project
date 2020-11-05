package com.hzh.mapper;

import com.hzh.pojo.Blog;
import com.hzh.pojo.dto.BlogDetails;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.pojo.dto.BlogQuery;
import com.hzh.pojo.dto.BlogShow;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    // 博客管理后台首页
    List<BlogQuery> getFewBlogList();
    // 博客搜索
    List<BlogQuery> searchByTitleOrTypeOrRecommend(BlogQuery searchBlog);

    @Select("select id,title from t_blog b where b.recommend=true  order by id limit 0,#{size}")
    List<Blog> getListRecommendBlogShow(@Param("size") Integer size);

    List<BlogHome> getHomeBlogList();

    List<BlogHome> getQueryBlogList(@Param("query") String query);

    // 获取一个博客详情界面
    BlogDetails getBlogDetails(@Param("blog_id") Long blog_id);

    @Select("select b.id,b.title,b.type_id,b.content,t.name type_name,flag,description,first_picture,recommend,share_statement,appreciation,open_comment,published\n" +
            "from t_blog b,t_type t\n" +
            "where b.type_id=t.id and b.id=#{0}")
    BlogShow getListBlogByBlogId(Long blog_id);

    // 通过类型获取 博客
    List<BlogHome> getHomeBlogListByTypeId(Long typeId);
    // 通过标签获取博客
    List<BlogHome> getHomeBlogListByTagId(@Param("tagId") Long tagId);

    @Select("select * from t_blog where id=#{0}")
    Blog queryOneBlog(Long id);

    Long insertBlog(Blog blog);

    @Delete("delete from t_blog where id=#{0}")
    int deleteBlog(Long id);

    int updateBlog(Blog blog);

    // 浏览次数+1
    @Update("update t_blog set views=views+1 where id=#{0}")
    int updateBlogViews(Long blogId);

    @Select(" select count(id) from t_blog ")
    Long countBlog();

}

