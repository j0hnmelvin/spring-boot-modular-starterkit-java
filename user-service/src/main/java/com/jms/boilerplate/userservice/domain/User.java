package com.jms.boilerplate.userservice.domain;

import java.time.Instant;

public interface User {
    String getId();
    void setId(String id);

    String getName();
    void setName(String name);

    String getEmail();
    void setEmail(String email);

    Instant getCreatedAt();
    void setCreatedAt(Instant createdAt);

    Instant getUpdatedAt();
    void setUpdatedAt(Instant updatedAt);
}
