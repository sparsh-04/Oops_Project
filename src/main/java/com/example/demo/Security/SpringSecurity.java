package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // configure SecurityFilterChain - who is allowed for which sites
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeHttpRequests().requestMatchers("/signup").permitAll().requestMatchers("/signup/**").permitAll().requestMatchers("/signin").permitAll().requestMatchers("/signin/**").permitAll().anyRequest().authenticated().and()
        .formLogin(
            form -> form
            .loginPage("/signin")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/login/success")
            .permitAll())
        .logout(
        logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/signin")
            .permitAll());
        return http.build();
        }
}
