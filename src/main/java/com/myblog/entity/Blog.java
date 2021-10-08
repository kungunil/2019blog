package com.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huanglong
 * @Date: 2021/10/05/19:06
 * @Description:
 */

@Entity
@Table(name = "t_blog")
@Data
public class Blog {
    @GeneratedValue
    @Id
    private Long id;

    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentAble;
    private boolean published;
    private boolean recommend;
    private String  description;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Type type;
    @ManyToMany(cascade = CascadeType.REFRESH,fetch= FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;
    @OneToMany(mappedBy = "blog",cascade = CascadeType.REFRESH)
    private List<Comment> comments= new ArrayList<>();

    /*不会入库*/
    @Transient
    private String tagIds;

    public void init(){
        this.tagIds=listToStr(this.getTags());
    }
    
    public String listToStr(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer buffer =new StringBuffer();
            Boolean flag =false;
            for (Tag tag : tags) {
               if (flag){
                   buffer.append(",");
               }else {
                   flag=true;
               }
               buffer.append(tag.getId());
            }
            return buffer.toString();
        }else {
            return tagIds;
        }
    }
}

