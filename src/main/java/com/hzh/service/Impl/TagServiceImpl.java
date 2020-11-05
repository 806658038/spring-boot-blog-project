package com.hzh.service.Impl;

import com.hzh.mapper.BlogAndTagMapper;
import com.hzh.mapper.TagMapper;
import com.hzh.pojo.Tag;
import com.hzh.pojo.Type;
import com.hzh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    private void tagListSort(List<Tag> tagListShow) {
        Collections.sort(tagListShow, new Comparator<Tag>(){
            public int compare(Tag o1, Tag o2) {
                if(o1.getBlogs().size() < o2.getBlogs().size()){
                    return 1;
                }
                if(o1.getBlogs().size() == o2.getBlogs().size()){
                    return 0;
                }
                return -1;
            }
        });
    }

    @Override
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public Tag queryOneTag(Long id) {
        return tagMapper.queryTagById(id);
    }

    @Override
    public Tag queryTagByName(String name) {
        return tagMapper.queryTagByName(name);
    }

    // 只获取标签
    @Override
    public List<Tag> getListTag() {
        return tagMapper.getAllTagList();
    }

    @Override
    public List<Tag> getListTagByStringId(String ids) {

        List<Long> idList = new BlogServiceImpl().stringIdsToListId(ids);

        return tagMapper.getTagListByIdList(idList);
    }

    // 通过博客id获取 其所有的标签
    @Override
    public List<Tag> getTagListByBlogId(Long blog_id) {
        return tagMapper.getTagListByBlogId(blog_id);
    }

    @Override
    public List<Tag> getSomeTagShow(Integer size) {

        return tagMapper.getSomeTagShow(size);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTagById(id);
    }

    // 获取标签（包括博客的数量）
    @Override
    public List<Tag> getAllTagList() {
        List<Tag> tagList = tagMapper.getAllTagAndBlogList();
        tagListSort(tagList);

        return tagList;
    }

}
