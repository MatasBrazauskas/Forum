package com.example.demo.Repository;

import com.example.demo.Entities.UserProfile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>
{
    @Transactional
    public UserProfile findByUsername(String username);

    @Transactional
    public UserProfile findByEmail(String email);
}
