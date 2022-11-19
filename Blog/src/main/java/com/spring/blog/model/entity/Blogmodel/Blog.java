package com.spring.blog.model.entity.Blogmodel;

import com.spring.blog.model.vo.UserLoginMsg;
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


public class Blog {
    private Long id;
    private String title;//文章标题
    private String content;//文章正文
    private String firstPicture;//文章首图
    private long comment;//评论数
    private Integer recommend;//推荐点赞数
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer views;//浏览次数
    private Integer words;//文章字数
    private long publish;//是否公开
    private int writerid;//文章作者id
    private boolean isEdit;
    private UserLoginMsg userLoginMsg;//文章作者(因为是个人博客，也可以不加作者字段，暂且加上)
    private Comment comments;//文章的评论
    private Category category;//文章分类
    private List<Tag> tags = new ArrayList<>();//文章标签
}
