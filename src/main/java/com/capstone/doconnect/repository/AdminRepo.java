package com.capstone.doconnect.repository;

import com.capstone.doconnect.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    public Admin findByEmail(String email);
}
