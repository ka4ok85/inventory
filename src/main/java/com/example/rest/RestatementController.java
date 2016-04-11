package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Restatementjob;
import com.example.service.OrphanService;
import com.example.service.RestatementjobService;
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
}
