package com.spring.blog.model.entity.Blogmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    private Long id;
    private String nickname;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    private Date createTime;//评论时间
    private String ip;//评论者ip地址
    private boolean published;//公开或回收站
    private boolean adminComment;//博主回复
    private Integer page;//0普通文章，1关于我页面
    private boolean notice;//接收邮件提醒

    private Blog blog;//所属的文章
    private Comment parentComment;//父评论
    private List<Comment> replyComments = new ArrayList<>();//回复该评论的评论
}