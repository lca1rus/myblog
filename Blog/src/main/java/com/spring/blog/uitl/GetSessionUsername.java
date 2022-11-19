package com.spring.blog.uitl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetSessionUsername {

        public static String getTheUsername(HttpServletRequest req){
            String username = req.getHeader("username");

            return username;
        }


}
