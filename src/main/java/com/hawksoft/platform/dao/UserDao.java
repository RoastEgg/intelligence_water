package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {

    public User findUser(Map<String, Object> map);
    public User findUserByName(String uname);
    public int saveUser(User user);
}
