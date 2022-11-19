package com.spring.blog.model.vo;

import com.spring.blog.model.entity.Usermodel.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminBlogInfo {
    private Long id;
    private String title;
    private String content;
    private boolean isEdit;
    private UserLoginMsg userLoginMsg;
}
