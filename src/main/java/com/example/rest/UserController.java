package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Restatementjob;
import com.example.entity.Userstore;
import com.example.security.MemberServiceImpl;
import com.example.service.RestatementjobService;
import com.example.service.UserService;
import com.example.wrappers.RestatementJobWrapperUserList;
import com.example.wrappers.RestatementjobWrapperFull;
import com.example.wrappers.UserWrapperShort;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private MemberServiceImpl userDetailsService;

    @RequestMapping(value = "/api/getusers/{status}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.wrappers.UserWrapperShort.class)
    public List<UserWrapperShort> getRestatementJobById(@PathVariable("status") String status) {
        ArrayList<UserWrapperShort> userList = new ArrayList<UserWrapperShort>();
        
        Long storeId = Long.parseLong(userDetailsService.getStoreId());

        Iterable<Userstore> iterable = userService.getAllByStoreAndStatus(storeId, status);
        for (Userstore userstore : iterable) {
            UserWrapperShort userWrapperShort = new UserWrapperShort(userstore.getUser());
            userList.add(userWrapperShort);
        }

        return userList;
    }
}
