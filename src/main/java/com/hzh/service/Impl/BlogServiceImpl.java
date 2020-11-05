package com.hzh.service.Impl;

import com.hzh.exception.NotFoundException;
import com.hzh.mapper.BlogAndTagMapper;
import com.hzh.mapper.BlogMapper;
import com.hzh.pojo.Blog;
import com.hzh.pojo.Tag;
import com.hzh.pojo.dto.BlogDetails;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.pojo.dto.BlogQuery;
import com.hzh.pojo.dto.BlogShow;
import com.hzh.service.BlogService;
import com.hzh.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    BlogAndTagMapper blogAndTagMapper;

    // 将字符串id 转为 List存储
    public List<Long> stringIdsToListId(String stringIds){
        List<Long> IdList =new ArrayList<>();
        String []str=stringIds.split(",");
        for (int i=0;i<str.length;i++){
            Long id= Long.valueOf(str[i]);
            IdList.add(id);
        }
        return IdList;
    }

    // 将list 转为 string 类型
    String tagsToIdS(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            for (Tag tag:tags){
                ids.append(tag.getId()+",");
            }
            ids.delete(ids.length()-1,ids.length());
            return ids.toString();
        }else{
            return null;
        }
    }

    @Transactional
    @Override
    public int insertBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        Long j = blogMapper.insertBlog(blog);
        System.out.println(blog.getId());

        int i = blogAndTagMapper.insertBlogAndTags(blog.getId(), stringIdsToListId(blog.getTagIds()));
        if(i==0 || j==0) {
            throw new RuntimeException("操作失败");
        }
        return i;
    }

    @Override
    public List<BlogQuery> getFewListBlog() {
        return blogMapper.getFewBlogList();
    }

    @Override
    public List<BlogQuery> searchBlog(BlogQuery blogQuery) {
        return blogMapper.searchByTitleOrTypeOrRecommend(blogQuery);
    }

    @Override
    public List<Blog> getRecommendBlog(Integer size) {
        return blogMapper.getListRecommendBlogShow(size);
    }

    @Override
    public List<BlogHome> getSearchBlogList(String query) {


        return blogMapper.getQueryBlogList(query);
    }

    @Override
    public BlogShow getBlogShowList(Long blog_id) {
        List<Tag> tags = blogAndTagMapper.getListBlogAndTag(blog_id);

        BlogShow blogShow = blogMapper.getListBlogByBlogId(blog_id);
        String tagIds = tagsToIdS(tags);
        if(tagIds!=null) {
            blogShow.setTagIds(tagIds);
        }
        return blogShow;
    }

    // 通过类型获取博客
    @Override
    public List<BlogHome> getBlogListByTypeId(Long typeId) {

        return blogMapper.getHomeBlogListByTypeId(typeId);
    }

    // 通过标签获取博客
    @Override
    public List<BlogHome> getBlogListByTagId(Long tagId) {

        return blogMapper.getHomeBlogListByTagId(tagId);
    }

    // 单个博客详情
    @Transactional
    @Override
    public BlogDetails getBlogDetails(Long blog_id) {
        BlogDetails blogDetails=blogMapper.getBlogDetails(blog_id);
        if(blogDetails==null){
            throw  new NotFoundException("该博客不存在");
        }
        blogMapper.updateBlogViews(blog_id);
        
        String content =blogDetails.getContent();
        blogDetails.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        return blogDetails;
    }

    @Override
    public List<BlogHome> getBlogHomeList() {

        return blogMapper.getHomeBlogList();
    }

    @Override
    public Blog queryOneBlog(Long id) {
        return blogMapper.queryOneBlog(id);
    }

    @Override
    public int deleteBlogById(Long id) {
        blogAndTagMapper.deleteBlogAndTagsByBolgId(id);
        return blogMapper.deleteBlog(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        int i = blogAndTagMapper.deleteBlogAndTagsByBolgId(blog.getId());
        List<Long> idsList = stringIdsToListId(blog.getTagIds());
        int j = blogAndTagMapper.insertBlogAndTags(blog.getId(), idsList);

        if(blog.getViews()==null){
            blog.setViews(1);
        }else{
            blog.setViews(blog.getViews()+1);
        }
        return blogMapper.updateBlog(blog);
    }

    // 统计有多少博客
    @Override
    public Long countBlog() {

        return blogMapper.countBlog();
    }

}
