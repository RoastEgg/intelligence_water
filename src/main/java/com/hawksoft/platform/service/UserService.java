package com.hawksoft.platform.service;

import com.hawksoft.platform.entity.User;

import java.util.Map;

public interface UserService {

    public User findUser(Map<String, Object> map);
    public User findUserByName(String uname);
    public int saveUser(User user);

}
