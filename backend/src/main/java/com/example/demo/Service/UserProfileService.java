package com.example.demo.Service;

import com.example.demo.Entities.UserProfile;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Repository.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService
{
    private final UserProfileRepository userProfileRepo;

    public UserProfileService(UserProfileRepository userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }

    public UserProfile getUser(final String email){
        return userProfileRepo.findByEmail(email).orElseThrow(() -> new CustomExceptions.UserProfileNotFound(email));
    }

    public void saveUser(final UserProfile userProfile){
        userProfileRepo.save(userProfile);
    }

    public void incrementPosts(final String email)
    {
        final var user = this.getUser(email);
        user.setPostCount(user.getPostCount() + 1);
        userProfileRepo.save(user);
    }
}
