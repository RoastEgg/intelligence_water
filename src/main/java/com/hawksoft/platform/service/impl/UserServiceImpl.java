package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.UserDao;
import com.hawksoft.platform.entity.User;
import com.hawksoft.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User findUser(Map<String, Object> map) {
        return userDao.findUser(map);
    }
    @Override
    public User findUserByName(String uname){
        return userDao.findUserByName(uname);
    }
    @Override
    public int saveUser(User user){
        return userDao.saveUser(user);
    }
}
