package com.hzh.pojo.dto;

import java.util.Date;

public class BlogShow {
    // 博客id
    private Long id;
    // 博客标题
    private String title;
    // 分类id
    private String typeId;
    // 内容
    private String content;
    // 首图
    private String firstPicture;
    // 描述
    private String description;
    private String flag;
    // 推荐
    private boolean recommend;
    // 转载声明 是否开启
    private boolean shareStatement;
    // 赞赏
    private boolean appreciation;
    // 评论
    private boolean openComment;
    // 发布或保存
    private Boolean published;
    // 更新时间
    private Date updateTime;

    private String tagIds;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isOpenComment() {
        return openComment;
    }

    public void setOpenComment(boolean openComment) {
        this.openComment = openComment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BlogShow{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typeId='" + typeId + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", recommend=" + recommend +
                ", shareStatement=" + shareStatement +
                ", appreciation=" + appreciation +
                ", openComment=" + openComment +
                ", published=" + published +
                ", updateTime=" + updateTime +
                '}';
    }
}
