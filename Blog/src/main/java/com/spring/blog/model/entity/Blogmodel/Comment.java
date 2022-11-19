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
    private Long id;//评论的id
    private String username;//评论的用户昵称
    private String content;//评论内容
    private String avatar;//头像(图片路径)
    private Date createTime;//评论时间
    private String ip;//评论者ip地址


    private Blog blog;//属与哪个的文章
    private Comment parentComment;//父评论
    private List<Comment> replyComments = new ArrayList<>();//回复该评论的评论
}