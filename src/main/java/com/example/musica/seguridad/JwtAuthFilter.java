package com.example.musica.seguridad;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.musica.infraestructura.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        // 🔥 1. Permitir endpoints públicos sin JWT
        String path = request.getServletPath();
        if (path.startsWith("/auth") || path.startsWith("/h2-console")) {
            chain.doFilter(request, response);
            return;
        }

        // 🔥 2. Leer encabezado Authorization
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        // 🔥 3. Validar token
        if (!jwtUtil.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }

        // 🔥 4. Obtener username del token
        String username = jwtUtil.getUsernameFromToken(token);

        // 🔥 5. Si ya hay autenticación, continuar
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(request, response);
            return;
        }

        // 🔥 6. Buscar el usuario real en BD
        var userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            var user = userOpt.get();

            var authorities = user.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            // 🔥 7. Setear autenticación en el contexto
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
