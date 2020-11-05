package com.hzh.service;

import com.hzh.pojo.Blog;
import com.hzh.pojo.dto.BlogDetails;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.pojo.dto.BlogQuery;
import com.hzh.pojo.dto.BlogShow;

import java.util.List;

public interface BlogService {

    int insertBlog(Blog blog);

    List<BlogQuery> getFewListBlog();
    // 后台搜索博客
    List<BlogQuery> searchBlog(BlogQuery blogQuery);

    List<Blog> getRecommendBlog(Integer size);
    // 首页搜索博客
    List<BlogHome> getSearchBlogList(String query);

    BlogShow getBlogShowList(Long blog_id);

    // 通过类型获取博客
    List<BlogHome> getBlogListByTypeId(Long typeId);

    // 通过标签获取博客
    List<BlogHome> getBlogListByTagId(Long tagId);

    // 单个博客详情
    BlogDetails getBlogDetails(Long blog_id);

    List<BlogHome> getBlogHomeList();

    Blog queryOneBlog(Long id);

    int deleteBlogById(Long id);

    int updateBlog(Blog blog);
    // 统计有多少博客
    Long countBlog();


}
