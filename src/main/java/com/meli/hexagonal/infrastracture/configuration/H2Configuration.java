package com.meli.hexagonal.infrastracture.configuration;

import com.meli.hexagonal.infrastracture.repository.h2.order.SpringDataH2OrderRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataH2OrderRepository.class)
public class H2Configuration {
}
