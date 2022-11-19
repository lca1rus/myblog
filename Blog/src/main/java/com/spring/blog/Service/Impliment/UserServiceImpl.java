package com.spring.blog.Service.Impliment;

import com.spring.blog.Service.UserService;
import com.spring.blog.dao.usersmapper;
import com.spring.blog.model.entity.Usermodel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：lcarus
 * @date： 2022/11/18 16:07
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private usersmapper usersmapper;

    @Override
    public List<Users> GetUsersInfo() {
       List<Users>  usersInfo=  usersmapper.GetUsersInfo();
        return usersInfo;
    }

    @Override
    public void DeleteById(Integer id) {
        usersmapper.DeleteById(id);
    }

    @Override
    public List<Users> FindUserByName(String name)
    {
        List<Users> usersInfo=usersmapper.FindUserByName(name);

        return usersInfo;
    }


}
