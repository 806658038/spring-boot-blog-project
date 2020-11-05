package com.hzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootBlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBlogProjectApplication.class, args);
    }

}
