package com.jms.jobmarketplace.domain;

import com.jms.jobmarketplace.constant.JMJobStatus;
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
import org.springframework.data.annotation.LastModifiedDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data // or individual annotations
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "JM_Jobs")
public class JMJob {
    @Id
    String id;

//    @ManyToOne
//    @JoinColumn(name = "poster_id")
//    JMUser poster;
    @Column(nullable = false)
    String postedByUserId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    String requirements;

    @Column(nullable = false)
    Double minBid;

    @Column(nullable = false)
    Instant expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    JMJobStatus status;

    // For Statistics
    Integer totalBids;
    Double curMinBid;
    String curMinBidUserId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    Instant updatedAt;

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString();

        this.status = JMJobStatus.OPEN;

        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Instant.now();
    }

}
