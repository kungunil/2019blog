package com.myblog.service.Impl;

import com.myblog.dao.UserRepository;
import com.myblog.entity.User;
import com.myblog.service.UserService;
import com.myblog.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/09/21:30
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
        User user = userRepository.findByUsernameAndPassword(username, MD5.getMD5(password));

        return user;
    }
}
