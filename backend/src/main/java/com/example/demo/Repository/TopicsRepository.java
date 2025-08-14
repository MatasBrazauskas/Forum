package com.example.demo.Repository;

import com.example.demo.Entities.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topics, Long>
{

}
