package com.promesa.promesa.common.config;

import com.promesa.promesa.common.application.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2Service oAuth2Service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/actuator/**",
                                "/", "/login", "/signup",
                                "/brand-info", "/categories/**", "exhibitions/**","/review-images/**","/inquiries/**","/artists/**",
                                "/index.html", "/static/**", "/favicon.ico",
                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/h2-console/**"
                        ).permitAll()                        // 위 경로는 인증 없이 접근 허용
                        .requestMatchers("/oauth/loginInfo").authenticated() // 이건 명시적으로 인증 필요
                        .anyRequest().authenticated()       // 나머지는 모두 인증 필요
                )

                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://13.209.202.120:3000/home", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2Service)
                        )
                );

        return http.build();
    }
}