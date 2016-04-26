package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Orphan;
import com.example.entity.Restatementjob;
import com.example.repository.OrphanRepository;
import com.example.repository.RestatementjobRepository;

@ComponentScan
@Service
public class OrphanService {

    @Autowired
    private RestatementjobRepository restatementjobRepository;

    @Autowired
    private OrphanRepository orphanRepository;

    public OrphanService() {
    }

    public Orphan addOrphan(Long restatementjobId, Long quantity) {
        Restatementjob restatementjob = restatementjobRepository.findOne(restatementjobId);
        if (restatementjob == null) {
            throw new NotFoundException(restatementjobId.toString());
        }

        Orphan newOrphan = new Orphan();
        newOrphan.setProduct(restatementjob.getProduct());
        //newOrphan.setStore(restatementjob.getStore());
        newOrphan.setStore(restatementjob.getStore().getId());
        newOrphan.setRestatementjob(restatementjobId);
        newOrphan.setQuantity(quantity);
        newOrphan.setStatus(Orphan.STATUS_NEW);

        orphanRepository.save(newOrphan);

        return newOrphan;
    }

}
