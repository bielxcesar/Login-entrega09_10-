package com.example.LoginGenerico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configuracaoDeSeguranca(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(autorizacao -> autorizacao
                        // Deixa os arquivos de enfeite (imagens, cores) passarem livremente
                        .requestMatchers("/css/**", "/js/**", "/imagens/**").permitAll()

                        // Todos podem ver a tela de login e a de criar conta
                        .requestMatchers("/login", "/cadastro").permitAll()

                        // Só o Administrador do JurisHome entra na área de administração
                        .requestMatchers("/admin/**").hasAuthority("ADMINISTRADOR")

                        // Área de escrever matérias liberada para Jornalistas e Administradores
                        .requestMatchers("/redacao/**").hasAnyAuthority("ADMINISTRADOR", "JORNALISTA")

                        // Qualquer outra página exige que a pessoa esteja logada
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login") // Qual é a nossa tela de login?
                        .defaultSuccessUrl("/home", true) // Para qual tela vai depois de acertar a senha?
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/sair")
                        .logoutSuccessUrl("/login?logout") // Para onde volta depois de fechar o sistema?
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder embaralhadorDeSenhas() {
        // A mesma ferramenta que usamos no serviço para esconder a senha real no banco
        return new BCryptPasswordEncoder();
    }
}