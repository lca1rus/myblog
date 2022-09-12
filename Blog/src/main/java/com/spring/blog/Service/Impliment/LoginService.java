package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.LoginUserService;
import com.spring.blog.dao.usersmapper;
import com.spring.blog.model.Users;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
用户已经登录的认证
 */
@Service
public class LoginService implements  UserDetailsService, LoginUserService {

    @Autowired
   private usersmapper usersmapper;
//


    public Users findUserByUsernameAndPassword(String username, String password) {
        Users user = usersmapper.selectByusername(username);
        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在");

            System.out.println("不存在");
        }
//        if (!MD5Utils.matchBC(password, user.getPassword())) {
//            throw new UsernameNotFoundException("密码错误");
//        }
        return user;
    }

    //UserDetailsService通过此方法在数据库中查询用户

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersmapper.selectByusername(username);
        if (user == null) {

            System.out.println("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        //System.out.println("成功");
        //把对应的信息封装成UserDetails
    //   System.out.println(user.getPassword());

        return user;
    }


}
