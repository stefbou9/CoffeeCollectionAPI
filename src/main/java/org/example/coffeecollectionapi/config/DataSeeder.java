package org.example.coffeecollectionapi.config;

import org.example.coffeecollectionapi.model.User;
import org.example.coffeecollectionapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner demoData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User admin = userRepository.findByUsername("admin").orElse(new User());
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println("Admin user updated/created: username=admin, password=admin");
        };
    }
}
