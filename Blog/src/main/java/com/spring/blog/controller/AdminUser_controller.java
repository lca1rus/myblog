package com.spring.blog.controller;

import com.spring.blog.Service.Impliment.BlogServiceImpl;
import com.spring.blog.Service.Impliment.UserServiceImpl;
import com.spring.blog.model.entity.Usermodel.Users;
import com.spring.blog.model.vo.AdminBlogInfo;
import com.spring.blog.model.vo.PageResult;
import com.spring.blog.model.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：lcarus
 * @date： 2022/11/18 16:06
 * @Description:user的管理
 */
@RequestMapping("/Admin")
@RestController
public class AdminUser_controller {

    @Autowired
    private UserServiceImpl userService;

@GetMapping("/GetUsersInfo")//使用get的话只能得到数据，不能加参数
public Result GetBlogs() {
    try {

        List<Users> usersInfo=userService.GetUsersInfo();

        return Result.ok("请求成功", usersInfo);
    }

    catch (Exception e) {
        System.out.println(e.getMessage());
        return Result.create(500, "异常错误");
    }
}
    @DeleteMapping("/DeleteById")
    public Result DeleteById(@RequestParam Integer id) {
        try {
        userService.DeleteById(id);

            return Result.ok("请求成功");
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.create(500, "异常错误");
        }

    }

@RequestMapping("/FindUserByName")
public  Result FindUserByName(@RequestParam String username){
    try {

        List<Users> usersInfo=userService.FindUserByName(username);

        return Result.ok("请求成功", usersInfo);
    }

    catch (Exception e) {
        System.out.println(e.getMessage());
        return Result.create(500, "异常错误");
    }
}
}
