package com.example.javawebdemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**","/login/oauth2/code/**","/js/**","/images/**", "/api/v1/auth/**", "/login/**", "oauth_login*", "/oauth2/**")
                .permitAll()
                .anyRequest().authenticated()
                )
          .csrf(csrf -> csrf.disable())


                .oauth2Login(oauth2 -> {
                            oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOauth2UserService));
                        }
                );

        return http.build();
    }

}
