package com.crg.enterprisews.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.crg.enterprisews.repository")
public class DatabaseConfig {
}
