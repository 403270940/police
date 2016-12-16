package com.police;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by liyy on 16/10/18.
 */
@SpringBootApplication
@ComponentScan({"com.police.service","com.police.controller","com.police.global"})
@EntityScan("com.police.model")
@EnableJpaRepositories({"com.police.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
