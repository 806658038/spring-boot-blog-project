package com.hzh.mapper;

import com.hzh.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    @Select("select * from t_type")
    List<Type> getAllTypeList();

    List<Type> getSomeTypeShow(@Param("size") Integer size);

    List<Type> getAllTypeShow();

    @Select("select * from t_type where id=#{0}")
    Type queryTypeById(Long id);

    @Select("select * from t_type where name=#{0}")
    Type queryTypeByName(String name);

    @Insert("insert into t_type(id,name) values(#{id},#{name})")
    int insertType(Type type);

    @Delete("delete from t_type where id=#{id}")
    int deleteTypeById(@Param("id")Long id);
    @Update("update t_type set name=#{name} where id=#{id}")
    int updateType(Type type);

}
