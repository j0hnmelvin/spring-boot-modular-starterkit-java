package com.jms.jobmarketplace.repo;

import com.jms.jobmarketplace.constant.JMJobStatus;
import com.jms.jobmarketplace.domain.JMJob;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JMJobRepo extends JpaRepository<JMJob, String> {
    List<JMJob> findAllByOrderByCreatedAtDesc(Limit limit);

    @Query(
        "SELECT j " +
        "FROM JMJob j " +
        "LEFT JOIN JMBid b " +
        "ON j.id = b.jobId " +
        "WHERE j.id = b.jobId AND j.status = :status " +
        "GROUP BY j.id " +
        "ORDER BY COUNT(b) DESC " +
        "LIMIT :limit"
    )
    List<JMJob> findTopXMostActiveJobs(
        @Param("status") JMJobStatus status,
        @Param("limit") int limit
    );

    List<JMJob> findByExpirationDateBefore(Instant expirationDate);
}
