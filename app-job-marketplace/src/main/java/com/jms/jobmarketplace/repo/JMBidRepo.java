package com.jms.jobmarketplace.repo;

import com.jms.jobmarketplace.domain.JMBid;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JMBidRepo extends JpaRepository<JMBid, String> {
    Optional<JMBid> findOneByJobIdOrderByAmountAsc(String jobId);
}
