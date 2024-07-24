package com.examen.movimiento.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
