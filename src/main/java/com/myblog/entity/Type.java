package com.myblog.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(name = "t_type")
@Data
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名称不能为空!")
    private String name;

    @OneToMany(mappedBy = "type",fetch=FetchType.EAGER )
    private List<Blog> blogs = new ArrayList<>();
}
