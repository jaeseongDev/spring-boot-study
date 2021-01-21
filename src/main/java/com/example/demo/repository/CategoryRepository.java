package com.example.demo.repository;

import com.example.demo.model.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // select * from category where type = 'COMPUTER'
    Optional<Category> findByType(String type);

}
