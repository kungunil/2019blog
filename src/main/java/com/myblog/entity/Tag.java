package com.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/09/20:02
 * @Description:
 */
@Entity
@Table(name = "t_tag")
@Data
public class Tag {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(mappedBy = "tags",fetch=FetchType.EAGER)
    private List<Blog> blogs = new ArrayList<>();
}
