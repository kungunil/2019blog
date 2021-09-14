package com.myblog.service;

import com.myblog.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/09/21:29
 * @Description:
 */
public interface UserService {
    User checkUser(String username, String password);
}
