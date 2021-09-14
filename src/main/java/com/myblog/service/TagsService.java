package com.myblog.service;

import com.myblog.entity.Tag;
import com.myblog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/10/15:26
 * @Description:
 */
public interface TagsService {
    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
    Tag updateTag(long id,Tag tag);
    void deleteTag(long id);
    Tag getTagByName(String name);
    List<Tag> listTagTop(Integer size);
}
