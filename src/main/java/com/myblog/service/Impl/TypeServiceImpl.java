package com.myblog.service.Impl;

import com.myblog.dao.TypeRepository;
import com.myblog.entity.Type;
import com.myblog.exception.NotFoundException;
import com.myblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/10/15:29
 * @Description:
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return repository.save(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return repository.findById(id).get();
    }
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type type1 = repository.findById(id).get();
        if(type1==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,type1);
        return repository.save(type1);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return repository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size){
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return repository.findTop(pageable);
    }
}
