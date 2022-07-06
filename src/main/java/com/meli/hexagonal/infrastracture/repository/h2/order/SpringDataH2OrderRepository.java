package com.meli.hexagonal.infrastracture.repository.h2.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataH2OrderRepository extends JpaRepository<OrderEntity, Long> {
}
