package facosta.jwtdemo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Base64Utils;

import java.util.*;

public class JWTService
{
    public static final String SECRET = Base64Utils.encodeToString("ClaveSecreta".getBytes());
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 4; //  4 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String create(Authentication auth)
    {
        String username = auth.getName();

        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

        Claims claims = Jwts.claims();
        claims.put("authorities", roles.stream()
                                       .map(GrantedAuthority::getAuthority)
                                       .toArray());

        String token = Jwts.builder()
                           .setClaims(claims)
                           .setSubject(username)
                           .setIssuedAt(new Date())
                           .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                           .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                           .compact();

        return token;
    }

    public static Optional<UserDetails> getClaims(String token)
    {
        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(SECRET.getBytes())
                                .parseClaimsJws(hasPrefix(token))
                                .getBody();


            List roles = claims.get("authorities", ArrayList.class);
            String[] roles_array = new String[roles.size()];

            for (int i = 0; i < roles.size(); i++)
                roles_array[i] = roles.get(i).toString();

            UserDetails user = User.withUsername(claims.getSubject())
                                   .authorities(roles_array)
                                   .password("")
                                   .build();

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static String hasPrefix(String token)
    {
        if (token != null && token.startsWith(TOKEN_PREFIX))
            return token.replace(TOKEN_PREFIX, "");

        return null;
    }
}