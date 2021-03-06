package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Storelocation;
import com.example.security.MemberServiceImpl;
import com.example.service.StorelocationService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class StorelocationController {

    @Autowired
    private StorelocationService storelocationService;
    
    @Autowired
    private MemberServiceImpl userDetailsService;

    @RequestMapping(value = "/api/getstorelocationsbystore", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Storelocation.class)
    public List<Storelocation> getStorelocationsByStoreId() {
        ArrayList<Storelocation> storelocationList = new ArrayList<Storelocation>();
        
        Long storeId = Long.parseLong(userDetailsService.getStoreId());

        Iterable<Storelocation> iterable = storelocationService.getAllStorelocationsByStore(storeId);
        for (Storelocation storelocation : iterable) {
        	storelocationList.add(storelocation);
        	System.out.println(storelocation);
        }        

        return storelocationList;
    }
}
