package EventManager.emanuele.security;

import EventManager.emanuele.entities.User;
import EventManager.emanuele.exceptions.UnauthorizedException;
import EventManager.emanuele.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // authHeader --> Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjk5ODczNTI3LCJleHAiOjE3MDA0NzgzMjd9.bCJaensC-bddAiDfU6Jt6JNN8Wooo6lEzypQkylEnUY
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
        } else {
            String token = authHeader.substring(7);
            System.out.println("TOKEN -> " + token);
            jwtTools.verifyToken(token);
            String id = jwtTools.extractIdFromToken(token);
            User currentUser = usersService.findById(Integer.parseInt(id));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(currentUser, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
