package com.example.demo.repository;

import com.example.demo.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
}
