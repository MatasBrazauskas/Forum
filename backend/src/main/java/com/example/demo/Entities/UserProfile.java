package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class UserProfile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, columnDefinition = "NVARCHAR(32)")
    private String username;

    @Column(name = "data_of_creation", nullable = false, columnDefinition = "DATE")
    private LocalDate dataOfCreation;

    @Column(name = "last_online", nullable = false, columnDefinition = "DATE")
    private LocalDate lastOnline;

    @Column(name = "post_count", nullable = false)
    private int post_count;

    @Column(name = "reputation", nullable = false)
    private int reputation;
}
