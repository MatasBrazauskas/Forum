package com.example.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users", indexes = {@Index(unique = true, name="index_username", columnList = "username")})
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    public UserProfile(String username, String email, LocalDate date, LocalDate date1, int i, int i1, Role role)
    {
        this.username = username;
        this.email = email;
        this.dateOfCreation = LocalDate.now();
        this.lastOnline = date1;
        this.postCount = i;
        this.reputation = i1;
        this.role = role;
    }

    public enum Role {
        ADMIN,
        USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(32)", unique = true)
    private String username;

    @Column(name = "email", nullable = false, columnDefinition = "NVARCHAR(256)", unique = true)
    private String email;

    @Column(name = "date_of_creation", nullable = false, columnDefinition = "DATE")
    private LocalDate dateOfCreation;

    @Column(name = "last_online", nullable = false, columnDefinition = "DATE")
    private LocalDate lastOnline;

    @Column(name = "post_count", nullable = false, columnDefinition = "INT UNSIGNED")
    private int postCount;

    @Column(name = "reputation", nullable = false, columnDefinition = "INT UNSIGNED")
    private int reputation;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "ENUM('ADMIN', 'USER', 'GUEST')")
    private Role role;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<Topics> topics = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<Thread> threads = new ArrayList<>();

    @OneToMany(mappedBy = "commentatorProfile",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<Comment> comments = new ArrayList<>();
}
