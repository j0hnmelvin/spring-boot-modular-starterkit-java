package com.jms.jobmarketplace.repo;

import com.jms.jobmarketplace.constant.JMUserType;
import com.jms.jobmarketplace.domain.JMUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JMUserRepo extends JpaRepository<JMUser, String> {
    Optional<JMUser> findById(String id);
    Optional<JMUser> findByIdAndUserType(String id, JMUserType jmUserType);
    boolean existsByEmail(String email);
    // Spring Data JPA automatically translates this method name into a query that checks for the existence of a user based on the email field.
    // The underlying query will likely be something like SELECT EXISTS(SELECT 1 FROM User u WHERE u.email = :email)
}
