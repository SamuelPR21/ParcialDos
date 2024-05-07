package com.exercise.parcial.parcial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{
        http
                //.csrf(AbstractHttpConfigurer::disable)
               //Paratodo requiere autorizacion
                .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PATCH, "/api/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                }
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
        }

        @Bean
        public UserDetailsService usariosMemoria(){
            UserDetails rector = User.builder()
                    .username("rector")
                    .password(passwordEncoder().encode("rector123"))
                    .roles("ADMIN")
                    .build();
            UserDetails estudiante = User.builder()
                    .username("estudiante")
                    .password(passwordEncoder().encode("estudiante123"))
                    .roles("CUSTOMER")
                    .build();
        return new InMemoryUserDetailsManager(rector, estudiante);
        }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

