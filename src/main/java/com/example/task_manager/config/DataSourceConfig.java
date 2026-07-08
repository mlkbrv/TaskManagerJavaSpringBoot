//package com.example.task_manager.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    @Profile("dev")
//    public DataSourceInfo devDataSource() {
//        return new DataSourceInfo("H2 In-Memory Database", "Fast, for development");
//    }
//
//    @Bean
//    @Profile("prod")
//    public DataSourceInfo prodDataSource() {
//        return new DataSourceInfo("PostgreSQL", "Production-grade database");
//    }
//}