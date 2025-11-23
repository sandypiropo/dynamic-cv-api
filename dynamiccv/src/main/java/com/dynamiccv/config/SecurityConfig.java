package com.dynamiccv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // rotas públicas
                        .requestMatchers(
                                "/",
                                "/about",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html"
                        ).permitAll()
                        // /home exige autenticação
                        .requestMatchers("/home").authenticated()
                        // qualquer outra rota exige autenticação
                        .anyRequest().authenticated()
                )
                // configuração do login OAuth2
                .oauth2Login(oauth2 -> oauth2
                        // login automático via Google
                        .loginPage("/oauth2/authorization/google") // Spring usa essa rota para iniciar OAuth2
                        .defaultSuccessUrl("/me", true) // rota após login bem-sucedido
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }
}
