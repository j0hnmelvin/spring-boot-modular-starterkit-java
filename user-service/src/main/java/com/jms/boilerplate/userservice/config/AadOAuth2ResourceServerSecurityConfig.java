//package com.jms.boilerplate.userservice.config;
//
//import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadResourceServerHttpSecurityConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class AadOAuth2ResourceServerSecurityConfig {
//
//    // TODO: Revisit Spring Security
//    // https://www.baeldung.com/spring-security-method-security
//
//
//    /**
//     * Add configuration logic as needed.
//     */
//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http.apply(AadResourceServerHttpSecurityConfigurer.aadResourceServer())
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET).anonymous()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//}
