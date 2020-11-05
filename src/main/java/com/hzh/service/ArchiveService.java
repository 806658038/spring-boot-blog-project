package com.hzh.service;

import com.hzh.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface ArchiveService {

    //  对所有的数据进行归档
    Map<String, List<Blog>> archiveBlog();



}
