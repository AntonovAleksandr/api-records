package com.example.apirecords;

import com.example.apirecords.db.RecordRepository;
import com.example.apirecords.db.UserRepository;
import com.example.apirecords.dto.AddRecordDto;
import com.example.apirecords.dto.UserDto;
import com.example.apirecords.dto.UserLoginDto;
import com.example.apirecords.model.RecordEntry;
import com.example.apirecords.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@RestController
public class ApiRecordsApplication {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApiRecordsApplication.class, args);
    }

    @GetMapping("/users/rnd")
    public User addRndRecord() {
        User user = new User();
        user.setBirthday(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());
        user.setMedicalId(UUID.randomUUID().toString());
        user.setGestationDate(UUID.randomUUID().toString());
        user.setLogin("doodoo");
        user.setPassword("12345");

        userRepository.save(user);
        User entity = userRepository.findAll().get(0);
        return entity;
    }

    @GetMapping("/records/{userId}")
    public Iterable<RecordEntry> getAllRecords(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return recordRepository.findByUser(user.get());
        }
        throw new RuntimeException("user not found or something");
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/records/add")
    public RecordEntry addRecord(@RequestBody AddRecordDto record) {
        Optional<User> user = userRepository.findById(record.getUserId());
        if (user.isPresent()) {
            RecordEntry entry = new RecordEntry();
            entry.setUser(user.get());
            entry.setData(record.getRecord().getData());
            entry.setDate(record.getRecord().getDate());
            entry.setResult(record.getRecord().getResult());
            entry.setStatus(record.getRecord().getStatus());
            entry.setTime(record.getRecord().getTime());
            return recordRepository.save(entry);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @DeleteMapping("/records/remove/{id}")
    public void deleteRecord(@PathVariable Integer id) {
        recordRepository.deleteById(id);
    }

    @PostMapping("/user/login")
    public User userLogin(@RequestBody UserLoginDto userLoginDto) {
        User user = userRepository.findByLogin(userLoginDto.getLogin());
        if (user != null) {
            if (!user.getPassword().equals(userLoginDto.getPassword())) {
                throw new RuntimeException("wrong password");
            }
            return user;
        }
        throw new RuntimeException("user not found");
    }

    @PostMapping("/user/registration")
    public Integer registerUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setMedicalId(userDto.getMedicalId());
        user.setGestationDate(userDto.getGestationDate());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        return userRepository.save(user).getId();
    }

}
