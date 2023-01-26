package com.teak.core;

import com.teak.core.pojo.EmailPogo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/16
 */
@SpringBootApplication
@EnableConfigurationProperties(EmailPogo.class)
public class TeakApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeakApplication.class, args);
    }
}
