package com.jms.jobmarketplace.controller;

import com.jms.jobmarketplace.constant.JMUserType;
import com.jms.jobmarketplace.domain.JMBid;
import com.jms.jobmarketplace.domain.JMJob;
import com.jms.jobmarketplace.repo.JMBidRepo;
import com.jms.jobmarketplace.repo.JMJobRepo;
import com.jms.jobmarketplace.repo.JMUserRepo;
import com.jms.jobmarketplace.service.JMJobService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job-marketplace/api/bids")
public class JMBidController {
    private final JMBidRepo jmBidRepo;
    private final JMJobRepo jmJobRepo;
    private final JMUserRepo jmUserRepo;
    private final JMJobService jmJobService;

    public JMBidController(JMBidRepo jmBidRepo, JMJobRepo jmJobRepo, JMUserRepo jmUserRepo, JMJobService jmJobService) {
        this.jmBidRepo = jmBidRepo;
        this.jmJobRepo = jmJobRepo;
        this.jmUserRepo = jmUserRepo;
        this.jmJobService = jmJobService;
    }

    @PostMapping("")
    @ResponseBody
    public JMBid createBid(
            @Valid
            @RequestBody JMBid jmBid
    ) {
        return this.jmUserRepo.findByIdAndUserType(jmBid.getBidByUserId(), JMUserType.BIDDER).map(x -> {
            Optional<JMJob> jmJob = this.jmJobRepo.findById(jmBid.getJobId());
            if (!jmJob.isEmpty()) {
                if (Instant.now().compareTo(jmJob.get().getExpirationDate()) >= 0) {
                    // Job had expired!
                    jmJobService.closeOrAwardExpiredJob(jmJob.get());
                    throw new RuntimeException("Job with ID# " + jmJob.get().getId() + " had Expired");
                } else {
                    if ((jmJob.get().getCurMinBid() == null && jmBid.getAmount() <= jmJob.get().getMinBid())
                            || (jmJob.get().getCurMinBid() == null && jmBid.getAmount() < jmJob.get().getCurMinBid())
                    ) {
                        // Job has no bid AND bid amount is less than or equal to minimum bid amount!
                        // Job has bid AND bid amount is less than **current minimum bid amount!
                        return updateJobStatsAndSaveBid(jmBid, jmJob.get());
                    } else {
                        // Bid amount is greater than minimum bid amount or current minimum bid amount!
                        throw new RuntimeException("Bid amount is greater than minimum bid amount or current minimum bid amount");
                    }
                }
            } else {
                throw new RuntimeException("Job not found with ID# " + jmBid.getJobId());
            }
        }).orElseThrow(() -> new RuntimeException("User not found with ID# " + jmBid.getBidByUserId() + " and type BIDDER"));
    }

    @Transactional
    private JMBid updateJobStatsAndSaveBid(JMBid jmBid, JMJob jmJob) {
        // Update Job Statistics
        jmJob.setTotalBids(jmJob.getTotalBids() != null ? jmJob.getTotalBids() + 1 : 1);
        jmJob.setCurMinBid(jmBid.getAmount());
        jmJob.setCurMinBidUserId(jmBid.getBidByUserId());
        this.jmJobRepo.save(jmJob);

        return this.jmBidRepo.save(jmBid);
    }
}
