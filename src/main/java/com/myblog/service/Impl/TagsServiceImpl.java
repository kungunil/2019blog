package com.myblog.service.Impl;

import com.myblog.dao.TagRepository;
import com.myblog.entity.Tag;
import com.myblog.exception.NotFoundException;
import com.myblog.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 潘金龙
 * @Date: 2021/10/7/17:41
 * @Description:
 */
@Service
public class TagsServiceImpl implements TagsService {
    @Autowired
    private TagRepository tagRepository;
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(myStrToList(ids));
    }
    private List<Long> myStrToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids)&&ids!=null){
            String[] split = ids.split(",");
            for (String s : split) {
                list.add(new Long(s));
            }
        }
        return list;
    }
    @Transactional
    @Override
    public Tag updateTag(long id, Tag tag) {
        Tag tag1 = tagRepository.findById(id).get();
        if(tag1==null){
            throw new NotFoundException("不存在改标签");
        }
        BeanUtils.copyProperties(tag,tag1);
        return tagRepository.save(tag1);
    }
    @Transactional
    @Override
    public void deleteTag(long id) {
         tagRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }
}
