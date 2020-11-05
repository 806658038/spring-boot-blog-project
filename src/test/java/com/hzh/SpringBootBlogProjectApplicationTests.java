package com.hzh;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzh.mapper.BlogAndTagMapper;
import com.hzh.mapper.CommentMapper;
import com.hzh.pojo.Blog;
import com.hzh.pojo.Comment;
import com.hzh.pojo.Tag;
import com.hzh.pojo.Type;
import com.hzh.pojo.dto.BlogHome;
import com.hzh.pojo.dto.BlogQuery;
import com.hzh.pojo.dto.BlogShow;
import com.hzh.service.BlogService;
import com.hzh.service.CommentService;
import com.hzh.service.TagService;
import com.hzh.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SpringBootBlogProjectApplicationTests {

    @Autowired
    TypeService typeServiceImpl;

    @Autowired
    BlogService blogServiceImpl;

    @Autowired
    TagService tagServiceImpl;

    @Autowired
    BlogAndTagMapper blogAndTagMapper;

    @Autowired
    CommentService commentServiceImpl;


    @Test
    void test1(){
        int pageNum=1;
        int pageSize=10;
        PageHelper.startPage(pageNum,pageSize);
        List<Type> list = typeServiceImpl.getListType();

        PageInfo<Type> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
    }

    @Test
    void test2(){
        int pageNum=3;
        int pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        List<Type> list = typeServiceImpl.getListType();

        PageInfo<Type> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
    }

    @Test
    void test3(){
        int pageNum=1;
        int pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        List<BlogQuery> list = blogServiceImpl.getFewListBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
    }

    @Test
    void test4(){
       String ids="2,3";
        List<Tag> listTagByStringId = tagServiceImpl.getListTagByStringId(ids);
        System.out.println(listTagByStringId);

    }

    @Test
    void test5(){
        List<Tag> someTagShow = tagServiceImpl.getSomeTagShow(5);

        for (Tag tag:someTagShow){
            System.out.println(tag);
            System.out.println("---------------");
            System.out.println(tag.getBlogs().size());
        }
    }


    @Test
    void test6(){
        List<Comment> commentList = commentServiceImpl.getCommentListByBlogId(1L);
        for (Comment comment :commentList){
            System.out.println("----------");
            System.out.println(comment.getNickName()+":"+comment.getContent()+"--"+comment.getReplyComments());
            System.out.println("----------");
        }
    }


    private static void ListSort(List<Comment> tempReplys) {
        //用Collections这个工具类传list进来排序
        Collections.sort(tempReplys, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date dt1 =o1.getCreateTime();
                    Date dt2 =o2.getCreateTime();
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    }else {
                        return 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    @Test
    void test7(){

        /*
        List<Comment> commentList = new ArrayList<>();
        Comment c1= new Comment();
        c1.setCreateTime(new Date());
        Comment c2= new Comment();

        c2.setCreateTime(new Date());
        Comment c3= new Comment();

        c3.setCreateTime(new Date());

        Comment c4= new Comment();
        c4.setCreateTime(new Date());
        */

        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String  dString = formatter.format(date);
        System.out.println("dString="+dString);

        Date datas = java.sql.Date.valueOf(dString);
        System.out.println("datas="+datas);


    }

    @Test
    void test8(){
        List<BlogHome> blogListByTagId = blogServiceImpl.getBlogListByTagId(1L);
        for (BlogHome b : blogListByTagId){
            System.out.println(b);

        }
    }


}
