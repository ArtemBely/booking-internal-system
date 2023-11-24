package com.example.bookingmodel;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@EnableJpaRepositories("com.example.bookingmain.repositories")
@Configuration
public class ConfigModel {

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
