package com.example.demo.Repository;

import com.example.demo.Entities.Topics;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long>
{
    @Transactional
    Optional<Topics> findByTopicsName(String topicsName);
}
