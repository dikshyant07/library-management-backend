package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.models.User;
import com.library.management.system.libraryManagementSystem.repositories.UserRepository;
import com.library.management.system.libraryManagementSystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.library.management.system.libraryManagementSystem.enums.Role.ADMIN;

@Service
public class AdminsCreatorService implements CommandLineRunner {
    private final Utils utils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminsCreatorService(Utils utils, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.utils = utils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail(utils.getAdmin().getEmail())) {
            User admin = User.builder()
                    .name(utils.getAdmin().getName())
                    .age(utils.getAdmin().getAge())
                    .gender(utils.getAdmin().getGender())
                    .email(utils.getAdmin().getEmail())
                    .password(passwordEncoder.encode(utils.getAdmin().getPassword()))
                    .role(ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }
}
