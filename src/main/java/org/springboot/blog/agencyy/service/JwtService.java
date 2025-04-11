package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.User;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(User user);
}
