package facosta.jwtdemo;

import facosta.jwtdemo.models.RoleEntity;
import facosta.jwtdemo.models.User;
import facosta.jwtdemo.repository.UserRepository;
import facosta.jwtdemo.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner
{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        RoleEntity userRole = new RoleEntity("ROLE_USER");
        RoleEntity adminRole = new RoleEntity("ROLE_ADMIN");

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123456"));
        user.getRoles().add(userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.getRoles().add(userRole);
        admin.getRoles().add(adminRole);

        userRepository.saveAll(Arrays.asList(admin, user));
    }
}
