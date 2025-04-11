//package org.springboot.blog.agencyy.service;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springboot.blog.agencyy.auth.AuthenticationRequest;
//import org.springboot.blog.agencyy.auth.AuthenticationResponse;
//import org.springboot.blog.agencyy.auth.RegisterRequest;
//import org.springboot.blog.agencyy.entity.Role;
//import org.springboot.blog.agencyy.entity.User;
//import org.springboot.blog.agencyy.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService{
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private AuthenticationManager authenticationManager;
//
//    public AuthenticationServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public AuthenticationResponse register(RegisterRequest request) {
//        Set<Role> roles = new HashSet<>();
//        User user = new User();
//        user.setUsername(request.getEmail());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRoles(roles);
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return new AuthenticationResponse(jwtToken);
//
//    }
//
//    @Override
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//            var user = repository.findByEmail(request.getEmail())
//                    .orElseThrow();
//            var jwtToken = jwtService.generateToken(user);
//            return new AuthenticationResponse(jwtToken);
//    }
//}
