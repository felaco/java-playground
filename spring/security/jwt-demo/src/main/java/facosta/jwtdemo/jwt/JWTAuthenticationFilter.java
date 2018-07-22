package facosta.jwtdemo.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import facosta.jwtdemo.dto.LoginUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
                                                                                                          AuthenticationException
    {
        LoginUserDto user = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(),
                                                LoginUserDto.class);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            throw new InsufficientAuthenticationException(e.getMessage());
        }

        UsernamePasswordAuthenticationToken authToken;
        authToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
                                                            user.getPassword());

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws
                                                                       IOException,
                                                                       ServletException
    {
        String token = JWTService.create(authResult);
        response.addHeader(JWTService.HEADER_STRING, JWTService.TOKEN_PREFIX + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
    }
}
