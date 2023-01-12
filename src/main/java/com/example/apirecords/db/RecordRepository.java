package com.example.apirecords.db;

import com.example.apirecords.model.RecordEntry;
import com.example.apirecords.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntry, Integer> {
    public Iterable<RecordEntry> findByUser(User user);
}
