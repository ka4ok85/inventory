package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Store;
import com.example.service.StoreService;
import com.example.wrappers.StoreWrapperShort;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/api/getstores/short", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.wrappers.StoreWrapperShort.class)
    public List<StoreWrapperShort> getStoresShort() {
        ArrayList<StoreWrapperShort> storeList = new ArrayList<StoreWrapperShort>();

        Iterable<Store> iterable = storeService.getAll();
        for (Store store : iterable) {
        	StoreWrapperShort storeWrapperShort = new StoreWrapperShort(store.getId(), store.getName());
        	storeList.add(storeWrapperShort);
        }

        return storeList;
    }
}
