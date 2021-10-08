package com.myblog.service;

import com.myblog.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘涛
 * @Date: 2021/10/08/21:08
 * @Description:
 */

public interface UserService {
    User checkUser(String username, String password);
}
