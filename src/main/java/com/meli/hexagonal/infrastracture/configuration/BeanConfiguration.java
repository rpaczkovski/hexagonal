package com.meli.hexagonal.infrastracture.configuration;


import com.meli.hexagonal.HexagonalApplication;
import com.meli.hexagonal.domain.ports.repository.OrderRepositoryPort;
import com.meli.hexagonal.domain.ports.service.OrderServicePort;
import com.meli.hexagonal.domain.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = HexagonalApplication.class)
public class BeanConfiguration {

    @Bean
    OrderServicePort orderService(OrderRepositoryPort orderRepositoryPort) {
        return new OrderServiceImpl(orderRepositoryPort);
    }

}
