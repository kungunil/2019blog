package com.myblog.service;

import com.myblog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 潘金龙
 * @Date: 2021/10/7/15:26
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
