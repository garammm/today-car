package com.todaycar;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.todaycar.board.repository")
@EntityScan(basePackages = "com.todaycar.board.domain")
public class TodayCarBackendApplication {

    // @PostConstruct
    // public void startH2Server() throws SQLException {
    //     Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
    // }

    public static void main(String[] args) {
        SpringApplication.run(TodayCarBackendApplication.class, args);
    }
}
