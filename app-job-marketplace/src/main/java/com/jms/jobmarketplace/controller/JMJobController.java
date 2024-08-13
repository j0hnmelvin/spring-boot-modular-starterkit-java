package com.jms.jobmarketplace.controller;

import com.jms.jobmarketplace.constant.JMJobStatus;
import com.jms.jobmarketplace.constant.JMUserType;
import com.jms.jobmarketplace.domain.JMJob;
import com.jms.jobmarketplace.repo.JMJobRepo;
import com.jms.jobmarketplace.repo.JMUserRepo;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job-marketplace/api/jobs")

public class JMJobController {

    private final JMJobRepo jmJobRepo;
    private final JMUserRepo jmUserRepo;

    public JMJobController(JMJobRepo jmJobRepo, JMUserRepo jmUserRepo) {
        this.jmJobRepo = jmJobRepo;
        this.jmUserRepo = jmUserRepo;
    }

    @GetMapping("")
    @ResponseBody
    public List<JMJob> getJobs() {
        return this.jmJobRepo.findAll();
    }

    @GetMapping("/recent")
    @ResponseBody
    public List<JMJob> getRecentJobs(
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit
    ) {
        return this.jmJobRepo.findAllByOrderByCreatedAtDesc(Limit.of(limit));
    }

    @GetMapping("/active")
    @ResponseBody
    public List<JMJob> getActiveJobs(
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit
    ) {
        return this.jmJobRepo.findTopXMostActiveJobs(JMJobStatus.OPEN, limit);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public JMJob getJob(
            @PathVariable
            String id
    ) {
        return this.jmJobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID# " + id));
    }

    @PostMapping("")
    public JMJob createJob(
            @Valid
            @RequestBody JMJob jmJob
    ) {
        return this.jmUserRepo.findByIdAndUserType(jmJob.getPostedByUserId(), JMUserType.POSTER).map(x -> {
            return this.jmJobRepo.save(jmJob);
        }).orElseThrow(() -> new RuntimeException("User not found with ID# " + jmJob.getPostedByUserId() + " and type POSTER"));
    }
}
