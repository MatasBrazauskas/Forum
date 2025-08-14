package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "threads", indexes = {@Index(name = "index_title", columnList = "title")})
@Data
@NoArgsConstructor
public class Thread
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserProfile.class)
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Topics.class)
    @JoinColumn(name = "threads")
    private Topics topics;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "thread", targetEntity = Comment.class)
    @BatchSize(size = 20)
    private List<Comment> comments = new ArrayList<>();

    @Column(unique = true, nullable = false, name = "title", columnDefinition = "VARCHAR(128)")
    private String title;

    @Column(nullable = false, name = "date_of_creation", columnDefinition = "DATE")
    private LocalDate dateOfCreation;

    @Column(nullable = false, name = "comment_count", columnDefinition = "INT UNSIGNED")
    private int commentCount;

    @Column(nullable = false, name = "upvote_count", columnDefinition = "INT")
    private int upvoteCount;
}
