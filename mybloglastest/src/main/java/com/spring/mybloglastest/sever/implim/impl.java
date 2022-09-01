package com.spring.mybloglastest.sever.implim;



import com.spring.mybloglastest.mapper.usersmapper;
import com.spring.mybloglastest.model.users;
import com.spring.mybloglastest.sever.mapperimlentment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class impl implements mapperimlentment {
    @Autowired
   usersmapper usersmapper;


    @Override
    public users selectByusername(String username) {
        users u1=usersmapper.selectByusername(username);
        return u1;
    }
}
