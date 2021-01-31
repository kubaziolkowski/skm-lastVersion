package pl.edu.pjwstk.simulator.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
import static java.security.KeyRep.Type.SECRET;


public class AuthorizationCheck extends BasicAuthenticationFilter {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";


    public AuthorizationCheck(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC512(String.valueOf(SECRET)))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            String user = decoded.getSubject();
            //
            List< SimpleGrantedAuthority > priviledges = decoded.getClaim("priviledges").asList(SimpleGrantedAuthority.class);
            //
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, user,
                        // List.of(() -> "ROLE_ADMIN")
                        priviledges );
            }
            return null;
        }

        return null;
    }
}