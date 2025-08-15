package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.BatchSize;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
public class Topics
{
    public enum TOPIC_TYPE {
        INFORMATION,
        TOPICS,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity =  UserProfile.class)
    @JoinColumn(name = "thread_creator")
    private UserProfile creator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topics", targetEntity = Thread.class)
    @BatchSize(size = 20)
    private List<Thread> threads;

    @Column(nullable = false, columnDefinition = "VARCHAR(256)", name = "topics_name")
    private String topicsName;

    @Column(nullable = false, columnDefinition = "DATE", name = "created")
    private LocalDateTime created;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)", name = "description")
    private String description;

    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED", name = "thread_count")
    private int threadCount;

    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED", name = "post_count")
    private int postCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('INFORMATION', 'GENERAL')", name = "topic_type")
    private TOPIC_TYPE topicType;
}
