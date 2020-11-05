package com.hzh.service;

import com.hzh.pojo.Tag;

import java.util.List;

public interface TagService {

    int insertTag(Tag tag);

    Tag queryOneTag(Long id);

    Tag queryTagByName(String name);

    List<Tag> getListTag();

    List<Tag> getListTagByStringId(String id);

    List<Tag> getTagListByBlogId(Long blog_id);

    List<Tag> getSomeTagShow(Integer size);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    // 获取所有的标签展示
    List<Tag> getAllTagList();

}
