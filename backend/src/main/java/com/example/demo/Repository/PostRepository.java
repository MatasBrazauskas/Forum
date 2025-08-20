package com.example.demo.Repository;

import com.example.demo.Controller.PostController;
import com.example.demo.Entities.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Comment ,Long>
{
    @Transactional
    public Comment save(Comment comment);
}
