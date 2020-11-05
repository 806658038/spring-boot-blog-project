package com.hzh.service.Impl;

import com.hzh.mapper.UserMapper;
import com.hzh.pojo.User;
import com.hzh.service.UserService;
import com.hzh.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(String username, String password) {
        User u = userMapper.queryUser(username,MD5Utils.code(password));

        return u;
    }

}
