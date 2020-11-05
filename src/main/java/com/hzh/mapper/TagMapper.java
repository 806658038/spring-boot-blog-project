package com.hzh.mapper;

import com.hzh.pojo.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    // 只获取标签的名字 和name
    @Select("select * from t_tag")
    List<Tag> getAllTagList();

    //   获取tag 的同时获取其对应的blog
    List<Tag> getAllTagAndBlogList();
    //   获取 id 列表 获取 tag
    List<Tag> getTagListByIdList(List<Long> idList);
    //   首页展示 几个推荐 tag
    List<Tag> getSomeTagShow(@Param("size") Integer size);
    // 通过id 查找tag
    @Select("select * from t_tag where id=#{0}")
    Tag queryTagById(Long id);

    // 通过博客id 找其所有的标签
    @Select("select t.id,t.name\n" +
            "from t_blog b,t_blog_tags bt, t_tag t\n" +
            "where b.id =bt.blog_id and bt.tag_id=t.id\n" +
            "and blog_id=#{blog_id}   ")
    List<Tag> getTagListByBlogId(@Param("blog_id") Long blog_id);

    // 通过标签名字 找 tag
    @Select("select * from t_tag where name=#{0}")
    Tag queryTagByName(String name);

    // 管理员添加 tag
    @Insert("insert into t_tag(id,name) values(#{id},#{name})")
    int insertTag(Tag tag);

    // 管理员删除tag
    @Delete("delete from t_tag where id=#{id}")
    int deleteTagById(@Param("id")Long id);
    // 管理员更新tag
    @Update("update t_tag set name=#{name} where id=#{id}")
    int updateTag(Tag tag);



}
