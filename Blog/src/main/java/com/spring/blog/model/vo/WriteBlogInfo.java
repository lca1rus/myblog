package com.spring.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class WriteBlogInfo implements Serializable {
    private Long id;
    private Date createTime;
    private Date UpdateTime;
    private String title;
    private String content;
    private String firstPicture;
    private Long recommend;//点赞数
    private Long comment;//评论数
    private long publish;//是否公开
    private long writerid;//文章作者id
}
