package com.mqm.community.service;

import com.mqm.community.mapper.UserMapper;
import com.mqm.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser ==null){
            //insert
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //update
            dbUser.setGmtModify(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
