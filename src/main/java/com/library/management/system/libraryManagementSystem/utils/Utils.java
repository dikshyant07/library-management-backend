package com.library.management.system.libraryManagementSystem.utils;

import com.library.management.system.libraryManagementSystem.enums.Gender;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "utils")
@Data
@Getter
@Component
public class Utils {
    private Jwt jwt;
    private Admin admin;

    @Data
    @Getter
    public static class Jwt {
        private String secret;
        private int expiry;
    }

    @Data
    @Getter
    public static class Admin {
        private String name;
        private int age;
        private Gender gender;
        private String email;
        private String password;
    }

}
