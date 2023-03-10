package com.youcode.gameyou;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class Config {
    // ModelMapper is a Java library that helps with the mapping of objects between Java beans,
    // which can save developers time and reduce the amount of boilerplate code needed to perform these mappings.
    // It provides an easy-to-use API for mapping objects between different Java classes and can handle complex mapping scenarios.
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // BCryptPasswordEncoder is a password encoder
    // that uses the BCrypt strong hashing function.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}