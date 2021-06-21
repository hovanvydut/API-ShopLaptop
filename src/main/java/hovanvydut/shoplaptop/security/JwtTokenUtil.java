package hovanvydut.shoplaptop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author hovanvydut
 * Created on 6/8/21
 */

@Component
public class JwtTokenUtil {
    // TODO: not enough length to HASH 256
    private final String jwtSecret = "hovanvyduthovanvyduthovanvydutfdasfasfdasfdsadfasfdas";
    private final String jwtIssuer = "hovanvydut";

    public String generateAccessToken(UserDetails userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtSecret));
//        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer(this.jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(key)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(this.jwtSecret).build().parseClaimsJws(token);

            Claims body = claims.getBody();

            // check token is expire
            Date date = body.getExpiration();
            if (date.before(new Date())) {
                return false;
            }

            return true;
        } catch(JwtException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public String getEmail(String token) {
        Jws<Claims> jws;

        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(this.jwtSecret)
                    .build()
                    .parseClaimsJws(token);

            Claims body = jws.getBody();

            return body.getSubject();
        } catch (JwtException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

            return null;
        }
    }

    public Date getExpirationDate(String token) {
        Jws<Claims> jws;

        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(this.jwtSecret)
                    .build()
                    .parseClaimsJws(token);

            Claims body = jws.getBody();

            return body.getExpiration();

        } catch (JwtException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
