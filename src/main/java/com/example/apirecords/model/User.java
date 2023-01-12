package com.example.apirecords.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
public @Data class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String medicalId;

    private String email;

    private String password;

    private String login;

    private String birthday;

    private String gestationDate;

    @OneToMany(mappedBy = "user")
    @Nullable
    private List<RecordEntry> records = new java.util.ArrayList<>();

}
