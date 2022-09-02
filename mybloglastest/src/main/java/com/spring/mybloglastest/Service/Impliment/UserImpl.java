package com.spring.mybloglastest.Service.Impliment;




import com.spring.mybloglastest.Service.UserService;
import com.spring.mybloglastest.model.loginUser;
import com.spring.mybloglastest.model.users;
import com.spring.mybloglastest.dao.usersmapper;
import com.spring.mybloglastest.uitl.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
/**
 * @Description: 用户业务层接口实现类

 */
@Service
public class UserImpl implements UserService,UserDetailsService {
    @Autowired
   private usersmapper usersmapper;


//UserDetailsService通过此方法在数据库中查询用户
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users user = usersmapper.selectByusername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //把对应的信息封装成UserDetails
        return new loginUser(user);
    }

    public users findUserByUsernameAndPassword(String username, String password) {
        users user = usersmapper.selectByusername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!MD5Utils.matchBC(password, user.getUserpassword())) {
            throw new UsernameNotFoundException("密码错误");
        }
        return user;
    }
}
