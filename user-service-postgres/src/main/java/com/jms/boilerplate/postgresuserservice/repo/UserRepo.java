package com.jms.boilerplate.postgresuserservice.repo;

import com.jms.boilerplate.postgresuserservice.domain.UserImpl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserImpl, Long> {
    Optional<UserImpl> findById(String id);
    boolean existsByEmail(String email);
    // Spring Data JPA automatically translates this method name into a query that checks for the existence of a user based on the email field.
    // The underlying query will likely be something like SELECT EXISTS(SELECT 1 FROM User u WHERE u.email = :email)
}
