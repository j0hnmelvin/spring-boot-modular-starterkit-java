package com.jms.jobmarketplace.service;

import com.jms.jobmarketplace.constant.JMJobStatus;
import com.jms.jobmarketplace.domain.JMJob;
import com.jms.jobmarketplace.repo.JMBidRepo;
import com.jms.jobmarketplace.repo.JMJobRepo;
import org.springframework.stereotype.Service;

@Service
public class JMJobService {
    private final JMJobRepo jmJobRepo;
    private final JMBidRepo jmBidRepo;

    public JMJobService(JMJobRepo jmJobRepo, JMBidRepo jmBidRepo) {
        this.jmJobRepo = jmJobRepo;
        this.jmBidRepo = jmBidRepo;
    }

    public void closeOrAwardExpiredJob(JMJob jmJob) {
        // Open -> Closed (when expiration date is reached and job has no bids)
        // Open -> Awarded (when expiration date is reached and job has bids)

        // Optional<JMBid> winningBid = jmBidRepo.findOneByJobIdOrderByAmountAsc(jmJob.getId());
        // if (winningBid.isEmpty())
        if (jmJob.getCurMinBid() == null)
            jmJob.setStatus(JMJobStatus.CLOSED);
        else {
            jmJob.setStatus(JMJobStatus.AWARDED);
        }
        jmJobRepo.save(jmJob);
    }
}
