package com.jms.boilerplate.mongodbuserservice.domain;

import com.jms.boilerplate.userservice.domain.User;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data // or individual annotations
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserImpl implements User {
    @MongoId
    String id;

    String name;

    String email;

    /*
        The @EnableMongoAuditing annotation we defined for the MongoConfig class
        will enable the @CreatedDate, @LastModifiedDate annotations to be activated for us.
        In this way, when each new record is made, these two features will be automatically saved in our Mongo database.
    */
    @CreatedDate
    Instant createdAt;

    @LastModifiedDate
    Instant updatedAt;
}
