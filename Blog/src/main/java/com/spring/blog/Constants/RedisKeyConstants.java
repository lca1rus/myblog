package com.spring.blog.Constants;

public class RedisKeyConstants {
    //用户登录的在线状态
    public static final String USER_STATE="User_State";

    public static final String ZSET="zset1";
    /**
     * 首页博客简介列表 分页对象key
     * homeBlogInfoList : {{1,"第一页的缓存"},{2,"第二页的缓存"}}
     */
    public static final String AdminBlogInfo = "AdminBlogInfo";
    public static final String IndexBlogInfo= "IndexBlogInfo";
    public static final String UsersInfo= "UsersInfo";

}

