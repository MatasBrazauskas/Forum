package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "comments", indexes = {})
@Data
@NoArgsConstructor
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserProfile.class)
    @JoinColumn(name = "commentator_profile")
    private UserProfile commentatorProfile;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Thread.class)
    @JoinColumn(name = "thread")
    private Thread thread;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Comment.class)
    @JoinColumn(name = "reply_id")
    private Comment reply;

    @Column(nullable = false, name = "comment", columnDefinition = "VARCHAR(256)")
    private String comment;

    @Column(nullable = false, name = "date_of_comment")
    private LocalDate dateOfComment;
}
