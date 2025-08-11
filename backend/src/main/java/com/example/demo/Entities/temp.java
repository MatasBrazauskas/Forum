package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "IDK")
public class temp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
