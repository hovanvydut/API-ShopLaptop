package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.security.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hovanvydut
 * Created on 6/8/21
 */

@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password) {
        try {
            Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = this.jwtTokenUtil.generateAccessToken(userDetails);

            return ResponseEntity.ok().header(
                        HttpHeaders.AUTHORIZATION,
                        token
                    ).body(token);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/test")
    public String testAuthen() {
        return "abc";
    }

    @GetMapping("/check-healthy")
    public String checkHealthy() {
        LOGGER.info("Checked healthy");
        return "Fine!";
    }

}
