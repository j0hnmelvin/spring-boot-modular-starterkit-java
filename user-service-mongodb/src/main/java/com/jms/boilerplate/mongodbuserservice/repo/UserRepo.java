package com.jms.boilerplate.mongodbuserservice.repo;

import com.jms.boilerplate.mongodbuserservice.domain.UserImpl;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserImpl, Long> {
    Optional<UserImpl> findById(String id);

    boolean existsByEmail(String email);
}
