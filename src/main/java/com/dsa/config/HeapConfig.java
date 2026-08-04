package com.dsa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tree.general.Heap;

@Configuration
public class HeapConfig {

    @Bean
    @Scope("singleton")
    public Heap heap() {
        return new Heap();
    }
}
