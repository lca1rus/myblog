package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.LoginService;
import com.spring.blog.dao.usersmapper;
import com.spring.blog.model.entity.Usermodel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
用户已经登录的认证
 */
@Service
public class LoginServiceImpl implements  UserDetailsService, LoginService {

    @Autowired
   private usersmapper usersmapper;
//


    public Users findUserByUsernameAndPassword(String username, String password) {
        Users user = usersmapper.selectByusername(username);
        if (user == null) {
            System.out.println("不存在");
            throw new UsernameNotFoundException("用户不存在");
        }

        return user;
    }

    //UserDetailsService通过此方法在数据库中查询用户

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersmapper.selectByusername(username);
        if (user == null) {

            System.out.println("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }


}
