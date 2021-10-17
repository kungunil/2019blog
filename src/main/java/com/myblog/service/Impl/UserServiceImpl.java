package com.myblog.service.Impl;

import com.myblog.dao.UserRepository;
import com.myblog.entity.User;
import com.myblog.service.UserService;
import com.myblog.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘涛
 * @Date: 2021/10/08/22:08
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        if(username==null&& password==null){
            return null;
        }
        User user = userRepository.findByUsernameAndPassword(username, password);

        return user;
    }
}
