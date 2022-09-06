package com.spring.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginMsg {
    private String username;
    private String password;

}
