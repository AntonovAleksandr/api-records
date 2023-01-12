package com.example.apirecords.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Record")
public @Data class RecordEntry {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String date;
    @Column
    private String time;
    @Column
    private String status;
    @Column
    private String result;
    @Column
    private String data;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getUserId() {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

}
