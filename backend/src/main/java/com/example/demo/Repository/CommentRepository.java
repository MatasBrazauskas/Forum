package com.example.demo.Repository;

import com.example.demo.Entities.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment ,Long>
{
    @Transactional
    public Optional<Comment> findById(Long id);
}
