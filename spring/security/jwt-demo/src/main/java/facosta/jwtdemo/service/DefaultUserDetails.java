package facosta.jwtdemo.service;

import facosta.jwtdemo.models.User;
import facosta.jwtdemo.utils.RoleEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("DefaultUserDetails")
public class DefaultUserDetails implements UserDetailsService
{
    private UserManagementService userService;

    @Autowired
    public DefaultUserDetails(UserManagementService userService)
    {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Optional<User> user = userService.findByUsername(s);
        if (!user.isPresent())
            throw new UsernameNotFoundException("Usuario no encontrado");

        User foundUser = user.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(foundUser.getUsername())
                .authorities(RoleEntityConverter.toSimpleGrantedAuthority(foundUser.getRoles()))
                .password(foundUser.getPassword())
                .build();
    }
}
