//package org.springboot.blog.agencyy.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class BlogSecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager.setUsersByUsernameQuery(
//                "SELECT username, password, enabled FROM users WHERE username = ?"
//        );
//
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//                "SELECT u.username, r.name AS authority " +
//                        "FROM users u " +
//                        "JOIN user_roles ur ON u.id = ur.user_id " +
//                        "JOIN roles r ON ur.role_id = r.id " +
//                        "WHERE u.username = ?"
//        );
//
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAnyRole("AUTHOR", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasAnyRole("AUTHOR", "ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/comments/**").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(basic -> {});
//
//        return http.build();
//    }
//}
