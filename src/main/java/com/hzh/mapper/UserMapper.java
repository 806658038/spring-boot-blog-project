package com.hzh.mapper;

import com.hzh.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from t_user where username=#{username} and password=#{password}")
    User queryUser(@Param("username") String username,@Param("password")String password);

    @Select("select  nick_name,avatar_address from t_user where id=#{0}")
    User getUserNickName(Long id);


}
