package com.jms.jobmarketplace.scheduler;

import com.jms.jobmarketplace.domain.JMJob;
import com.jms.jobmarketplace.repo.JMBidRepo;
import com.jms.jobmarketplace.repo.JMJobRepo;
import com.jms.jobmarketplace.service.JMJobService;
import java.time.Instant;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JMJobExpirationScheduler {
    private final JMJobRepo jmJobRepo;
    private final JMJobService jmJobService;

    public JMJobExpirationScheduler(JMJobRepo jmJobRepo, JMBidRepo jmBidRepo, JMJobService jmJobService) {
        this.jmJobRepo = jmJobRepo;
        this.jmJobService = jmJobService;
    }

    @Scheduled(fixedRateString = "${job-marketplace.job.expiration.check.interval:120000}") // Check every 2 minutes
    public void processExpiredJobs() {
        Instant now = Instant.now();
        System.out.println("Starting job expiration check at " + now);
        List<JMJob> expiredJobs = jmJobRepo.findByExpirationDateBefore(now);
        for (JMJob jmJob : expiredJobs) {
            jmJobService.closeOrAwardExpiredJob(jmJob);
        }
        System.out.println("Ending job expiration check at " + Instant.now());
    }
}
