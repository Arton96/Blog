//package org.springboot.blog.agencyy.rest;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springboot.blog.agencyy.auth.AuthenticationRequest;
//import org.springboot.blog.agencyy.auth.AuthenticationResponse;
//import org.springboot.blog.agencyy.auth.RegisterRequest;
//import org.springboot.blog.agencyy.service.AuthenticationService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthenticationController {
//
//    private final AuthenticationService service;
//
//    public AuthenticationController(AuthenticationService service) {
//        this.service = service;
//    }
//
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse>register(
//            @RequestBody RegisterRequest request
//    ){
//        return ResponseEntity.ok(service.register(request));
//    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse>authenticate(
//            @RequestBody AuthenticationRequest request
//    ){
//        return ResponseEntity.ok(service.authenticate(request));
//
//    }
//}
