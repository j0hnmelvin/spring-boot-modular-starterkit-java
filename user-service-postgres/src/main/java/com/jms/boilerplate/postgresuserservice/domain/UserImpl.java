package com.jms.boilerplate.postgresuserservice.domain;

import com.jms.boilerplate.userservice.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
@Table(name = "Users")
public class UserImpl implements User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    String id;
    @Id
    String id;

    String name;

    @Column(unique = true)
    String email;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    Instant updatedAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();

        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
