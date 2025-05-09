//package org.springboot.blog.agencyy.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springboot.blog.agencyy.service.JwtService;
//import org.springboot.blog.agencyy.service.JwtServiceImpl;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtServiceImpl jwtServiceImpl;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthenticationFilter(JwtService jwtService, JwtServiceImpl jwtServiceImpl, UserDetailsService userDetailsService) {
//        this.jwtServiceImpl = jwtServiceImpl;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//                         @NonNull HttpServletRequest request,
//                         @NonNull HttpServletResponse response,
//                         @NonNull FilterChain filterChain
//                        ) throws ServletException, IOException {
//            final String authHeader = request.getHeader("Authorization");
//            final String jwt;
//            final String userEmail;
//            if(authHeader == null || !authHeader.startsWith("Bearer ")){
//                filterChain.doFilter(request,response);
//                return;
//            }
//            jwt = authHeader.substring(7);
//            userEmail = jwtServiceImpl.extractUsername(jwt);
//            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//                if(jwtServiceImpl.isTokenValid(jwt, userDetails)){
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    authToken.setDetails(
//                            new WebAuthenticationDetailsSource().buildDetails(request)
//                    );
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//            filterChain.doFilter(request,response);
//    }
//}
