package com.hzh.pojo.dto;


import com.hzh.pojo.Type;

import java.util.Date;

public class BlogQuery {
    private Long id;
    private String title;
    private String typeName;
    private boolean recommend;
    private Date updateTime;
    private Boolean published;
    private Long typeId;
    private Type type;

    public BlogQuery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BlogQuery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typeName='" + typeName + '\'' +
                ", recommend=" + recommend +
                ", updateTime=" + updateTime +
                ", published=" + published +
                ", typeId=" + typeId +
                ", type=" + type +
                '}';
    }

}


