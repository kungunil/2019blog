package com.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/09/20:02
 * @Description:
 */
@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    /*用户的类型 用户还是管理员*/
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
