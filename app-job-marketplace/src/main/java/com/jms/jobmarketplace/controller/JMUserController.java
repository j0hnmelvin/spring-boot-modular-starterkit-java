package com.jms.jobmarketplace.controller;

import com.jms.jobmarketplace.domain.JMUser;
import com.jms.jobmarketplace.repo.JMUserRepo;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Allow all origins for development
@RequestMapping("/job-marketplace/api/users")
public class JMUserController {

    private final JMUserRepo jmUserRepo;

    public JMUserController(JMUserRepo jmUserRepo) {
        this.jmUserRepo = jmUserRepo;
    }

    // GET /users
    @GetMapping("")
    @ResponseBody
    public List<JMUser> getUsers() {
        return this.jmUserRepo.findAll();
    }

    // GET /users/:id
    @GetMapping("/{id}")
    @ResponseBody
    public JMUser getUser(
            @PathVariable
            String id
    ) {
        return this.jmUserRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // POST /users
    @PostMapping("")
    @ResponseBody
    public JMUser createUser(
            @Valid
            @RequestBody
            JMUser jmUser
    ) {
        return this.jmUserRepo.save(jmUser);
    }

    // PUT /users/:id
    @PutMapping("/{id}")
    @ResponseBody
    public JMUser updateUser(
            @PathVariable
            String id,
            @Valid
            @RequestBody
            JMUser jmUser
    ) {
        return this.jmUserRepo.findById(id).map(x -> {
            return this.jmUserRepo.save(x);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // DELETE /users/:id
    @DeleteMapping("/{id}")
    @ResponseBody
    public JMUser deleteUser(
            @PathVariable
            String id
    ) {
        return this.jmUserRepo.findById(id).map(x -> {
            this.jmUserRepo.delete(x);
            return x;
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
