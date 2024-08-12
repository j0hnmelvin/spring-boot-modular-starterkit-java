package com.jms.jobmarketplace.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data // or individual annotations
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "JM_Bids")
public class JMBid {
    @Id
    String id;

//    @ManyToOne
//    @JoinColumn(name = "job_id")
//    JMJob job;
    @Column(nullable = false)
    String jobId;

//    @ManyToOne
//    @JoinColumn(name = "bidder_id")
//    JMUser bidder;
    @Column(nullable = false)
    String bidByUserId;

    @Column(nullable = false)
    Double amount;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdAt;

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString();

        this.createdAt = Instant.now();
    }

}