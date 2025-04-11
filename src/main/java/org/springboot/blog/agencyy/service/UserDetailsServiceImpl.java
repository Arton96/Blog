////package org.springboot.blog.agencyy.service;
////
////import org.springboot.blog.agencyy.repository.UserRepository;
////import org.springframework.context.annotation.Bean;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////
////@Service
////public class UserDetailsServiceImpl implements UserDetailsService {
////
////    private final UserRepository repository;
////    private final PasswordEncoder passwordEncoder;
////
////    public UserDetailsServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
////        this.repository = repository;
////        this.passwordEncoder = passwordEncoder;
////    }
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return repository.findByEmail(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
////    }
////
////    @Bean
////    public AuthenticationProvider authenticationProvider(){
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(this);
////        authProvider.setPasswordEncoder(passwordEncoder);
////        return authProvider;
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
////        return config.getAuthenticationManager();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////}
//
//
//
//
//package org.springboot.blog.agencyy.service;
//
//import org.springboot.blog.agencyy.repository.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserDetailsServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return repository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(this);
//        authProvider.setPasswordEncoder(passwordEncoder); // PasswordEncoder i injektuar nga SecurityConfig
//        return authProvider;
//    }
//
//    // AuthenticationManager do të merret nga konfigurimi i Spring Security
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
