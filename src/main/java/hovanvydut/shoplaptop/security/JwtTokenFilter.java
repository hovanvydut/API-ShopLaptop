package hovanvydut.shoplaptop.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/8/21
 */

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final MyUserDetailsService myUserDetailsService;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil,
                          MyUserDetailsService myUserDetailsService) {

        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailsService = myUserDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // get authorization header and validate
        final String bearerHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerHeader == null || bearerHeader.isEmpty() || !bearerHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // get jwt token and validate
        final String token = bearerHeader.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on spring security context
        UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(jwtTokenUtil.getEmail(token));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ? List.of() : userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }


}
