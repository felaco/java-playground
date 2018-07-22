package facosta.jwtdemo.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
    public JWTAuthorizationFilter(
            AuthenticationManager authenticationManager)
    {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
                                                                                                                 IOException,
                                                                                                                 ServletException
    {
        String token_header = request.getHeader(JWTService.HEADER_STRING);
        if (!requiresAuthentication(token_header)) {
        chain.doFilter(request, response);
        return;
    }

        JWTService.getClaims(token_header)
                  .ifPresent(userDetails -> {
                      UsernamePasswordAuthenticationToken authentication;
                      authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                                                               null,
                                                                               userDetails.getAuthorities());

                      SecurityContextHolder.getContext().setAuthentication(authentication);
                  });
        chain.doFilter(request, response);
    }

    protected boolean requiresAuthentication(String header)
    {

        return header != null && header.startsWith(JWTService.TOKEN_PREFIX);
    }
}
