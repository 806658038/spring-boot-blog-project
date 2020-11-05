package com.hzh.service.Impl;

import com.hzh.mapper.TypeMapper;
import com.hzh.pojo.Type;
import com.hzh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    private static void ListSort(List<Type> TypeListShow) {
        Collections.sort(TypeListShow, new Comparator<Type>(){
            public int compare(Type o1, Type o2) {
                if(o1.getBlogs().size() < o2.getBlogs().size()){
                    return 1;
                }
                if(o1.getBlogs().size() == o2.getBlogs().size()){
                    return 0;
                }
                return -1;
            }
        });
    }

    @Override
    public int insertType(Type type) {
        return typeMapper.insertType(type);
    }

    @Override
    public Type queryOneType(Long id) {
        return typeMapper.queryTypeById(id);
    }

    @Override
    public Type queryTypeByName(String name) {
        return typeMapper.queryTypeByName(name);
    }

    @Override
    public List<Type> getListType() {
        return typeMapper.getAllTypeList();
    }

    @Override
    public List<Type> getListTypeTop(Integer size) {
        return typeMapper.getSomeTypeShow(size);
    }

    // 展示所有标签
    @Override
    public List<Type> getListAllTypeShow() {
        List<Type> typeShow = typeMapper.getAllTypeShow();
        ListSort(typeShow);

        return typeShow;
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteTypeById(id);
    }

}
