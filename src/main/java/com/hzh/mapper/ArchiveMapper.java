package com.hzh.mapper;


import com.hzh.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArchiveMapper {

    @Select(" select date_format(b.update_time,'%Y') as year \n" +
            "from t_blog b \n" +
            "group by year \n" +
            "order by year desc;  ")
    List<String> getGroupByYear();

    // 通过年份查询 博客
    @Select("select * from t_blog b \n" +
            "where date_format(b.update_time,'%Y')=#{year} ")
    List<Blog> getBlogListByYear(@Param("year") String year);

}
