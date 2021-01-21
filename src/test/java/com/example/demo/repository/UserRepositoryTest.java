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

    @Autowired // Dependency Injection(DI)을 활용해서 new를 사용해 인스턴스를 생성하지 않아도 저절로 해당 객체를 생성해줌.
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = INSERT INTO user (%s, %s, %d) VALUE (account, email, age);
        User user = new User();
//        user.setId(); // Workbench를 통해 id 컬럼을 AutoIncrement로 설정했기 때문에 이 코드가 필요 없다.
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);

    }

    @Test
    @Transactional
    public void read() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                System.out.println(detail.getItem());
            });
        });
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
