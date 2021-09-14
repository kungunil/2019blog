package com.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myblog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/09/10/19:59
 * @Description:
 */
@Mapper
@Component
public interface BlogMapper  {
    List<Blog> queryConditional(String title,Long typeId, Long recommend);

}
