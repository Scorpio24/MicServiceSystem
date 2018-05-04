package com.yyu.service.impl;

import com.yyu.domain.User;
import com.yyu.mapper.UserMapper;
import com.yyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User服务层杰克实现类
 * Service("userService")用于将当前类注释为一个Spring的bean，名为userService
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * 自动注入UserMapper
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * UserService借口login方法实现
     * @see { UserService }
     */
    @Override
    public User login(String loginname, String password){
        return userMapper.findWithLoginnameAndPassword(loginname, password);
    }
}
