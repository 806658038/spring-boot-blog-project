package com.hzh.service.Impl;

import com.hzh.mapper.ArchiveMapper;
import com.hzh.pojo.Blog;
import com.hzh.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    ArchiveMapper archiveMapper;

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = archiveMapper.getGroupByYear();
        Map<String, List<Blog>> map = new TreeMap<>();
        // 倒序
        map = ((TreeMap<String, List<Blog>>) map).descendingMap();

        for (String y : years) {
            map.put(y, archiveMapper.getBlogListByYear(y));
        }

        return map;
    }


}
