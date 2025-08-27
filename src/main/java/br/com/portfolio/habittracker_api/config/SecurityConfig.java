package br.com.portfolio.habittracker_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity // Habilita a configuração de segurança web do Spring
public class SecurityConfig {

    // Este Bean de PasswordEncoder que já tínhamos criado continua aqui
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilitar CSRF (Cross-Site Request Forgery)
                // Essencial para APIs stateless (que usam tokens)
                .csrf(csrf -> csrf.disable())

                // 2. Configurar o gerenciamento de sessão para ser stateless
                // A API não vai guardar estado de sessão, toda requisição será validada pelo token
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Configurar as regras de autorização para os endpoints
                .authorizeHttpRequests(authorize -> authorize
                        // AQUI ESTÁ A CORREÇÃO: Permitir acesso público a todos os endpoints em /api/auth/
                        // Isso libera tanto o /register quanto o /login que faremos depois.
                        .requestMatchers("/api/auth/**").permitAll()
                        // Para todos os outros endpoints, a autenticação é necessária
                        .anyRequest().authenticated()
                );

        // A configuração de CORS é importante e já vamos deixar pronta
        // para quando o front-end for se comunicar com a API.
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite requisições do seu front-end React que roda na porta 5173
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        // Permite os métodos HTTP que vamos usar
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permite todos os cabeçalhos nas requisições
        configuration.setAllowedHeaders(List.of("*"));
        // Permite o envio de credenciais (cookies, tokens, etc)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuração de CORS a todas as rotas da sua API ("/**")
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}