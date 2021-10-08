package com.myblog.dao;

import com.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘涛
 * @Date: 2021/10/08/21:20
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);

}
