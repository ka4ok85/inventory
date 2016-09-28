package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Restatementjob;
import com.example.service.OrphanService;
import com.example.service.RestatementjobService;
import com.example.wrappers.RestatementJobWrapperAdd;
import com.example.wrappers.RestatementJobWrapperUserList;
import com.example.wrappers.RestatementjobWrapperFull;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class RestatementController {

    @Autowired
    private RestatementjobService restatementjobService;

    @Autowired
    private OrphanService orphanService;

    @RequestMapping(value = "/api/addRestatementJob/{productId}/{storeId}/{storeLocationId}/{expectedQuantity}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Restatementjob.class)
    @Transactional
    public Restatementjob addRestatementJob(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("storeLocationId") Long storeLocationId, @PathVariable("expectedQuantity") Long expectedQuantity) {
        Restatementjob restatementjob = restatementjobService.addJob(productId, storeId, storeLocationId, expectedQuantity);

        return restatementjob;
    }
/*
    @RequestMapping(value = "/api/addRestatementJob/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    @Transactional
    public RestatementjobWrapperFull addRestatementJob(@RequestBody RestatementjobWrapperFull restatementjobWrapperFull) {
    	
    	RestatementjobWrapperFull restatementjobWrapperFullAdded = new RestatementjobWrapperFull();
     	Restatementjob restatementjobAdded = restatementjobService.addJob(restatementjobWrapperFull);
	    restatementjobWrapperFullAdded = new RestatementjobWrapperFull(restatementjobAdded);

        return restatementjobWrapperFullAdded;
    }
  */  
    @RequestMapping(value = "/api/addRestatementJob/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    @Transactional
    public Restatementjob addRestatementJob(@RequestBody RestatementJobWrapperAdd restatementJobWrapperAdd) {
    	System.out.println(restatementJobWrapperAdd);
     	Restatementjob restatementjobAdded = restatementjobService.addJob(restatementJobWrapperAdd);

        return restatementjobAdded;
    }

    @RequestMapping(value = "/api/completeRestatementJob/{restatementjobId}/{quantity}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Restatementjob.class)
    @Transactional
    public Restatementjob completeRestatementJob(@PathVariable("restatementjobId") Long restatementjobId, @PathVariable("quantity") Long quantity) {
        Restatementjob restatementjob = restatementjobService.completeJob(restatementjobId);

        Long orphanQuantity = quantity - restatementjob.getExpectedQuantity();
        if (orphanQuantity != 0) {
            orphanService.addOrphan(restatementjobId, orphanQuantity);
        }

        return restatementjob;
    }

    @RequestMapping(value = "/api/getAllRestatementJobs", method = RequestMethod.POST, produces = "application/json")
    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    public List<RestatementjobWrapperFull> getAllRestatementJobs() {
        ArrayList<RestatementjobWrapperFull> restatementjobList = new ArrayList<RestatementjobWrapperFull>(); 

        Iterable<Restatementjob> iterable = restatementjobService.getAll();
        for (Restatementjob restatementjob : iterable) {
            System.out.println(restatementjob);
            RestatementjobWrapperFull restatementjobWrapper = new RestatementjobWrapperFull(restatementjob);
            restatementjobList.add(restatementjobWrapper);
        }

        return restatementjobList;
    }

    @RequestMapping(value = "/api/getAllRestatementJobsForStoreAndUser/{storeId}/{userId}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    public List<RestatementJobWrapperUserList> getAllRestatementJobsForStoreAndUser(@PathVariable("storeId") Long storeId, @PathVariable("userId") Long userId) {
        ArrayList<RestatementJobWrapperUserList> restatementjobList = new ArrayList<RestatementJobWrapperUserList>(); 

        Iterable<Restatementjob> iterable = restatementjobService.getAllByStoreAndUserId(storeId, userId);
        for (Restatementjob restatementjob : iterable) {
            RestatementJobWrapperUserList restatementjobWrapper = new RestatementJobWrapperUserList(restatementjob);
            restatementjobList.add(restatementjobWrapper);
        }

        return restatementjobList;
    }

    @RequestMapping(value = "/api/getRestatementJob/{id}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    public RestatementjobWrapperFull getRestatementJobById(@PathVariable("id") Long id) {
        Restatementjob restatementjob = restatementjobService.getById(id);
        RestatementjobWrapperFull restatementjobWrapper = new RestatementjobWrapperFull(restatementjob);

        return restatementjobWrapper;
    }

}
