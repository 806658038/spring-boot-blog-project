package com.hzh.service;

import com.hzh.pojo.Type;

import java.util.List;

public interface TypeService {

    int insertType(Type type);

    Type queryOneType(Long id);

    Type queryTypeByName(String name);

    List<Type> getListType();
    // 选择展示的标签数
    List<Type> getListTypeTop(Integer size);

    List<Type> getListAllTypeShow();

    int updateType(Type type);

    int deleteType(Long id);

}
