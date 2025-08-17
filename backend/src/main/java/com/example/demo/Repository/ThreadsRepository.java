package com.example.demo.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entities.Thread;

import java.util.Optional;

@Repository
public interface ThreadsRepository extends JpaRepository<Thread, Long>
{
    @Transactional
    public Optional<Thread> findThreadByTitle(String title);
}
