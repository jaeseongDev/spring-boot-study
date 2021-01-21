package com.example.demo.repository;


import com.example.demo.DemoApplicationTests;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryTest extends DemoApplicationTests { // 상속 해주어야 한다!

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {


    }

    @Test
    @Transactional
    public void read() {

    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(4L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(4L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(4L);

        Assertions.assertFalse(deleteUser.isPresent());
    }
}
