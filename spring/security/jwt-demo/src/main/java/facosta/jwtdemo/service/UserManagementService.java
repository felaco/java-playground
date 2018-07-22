package facosta.jwtdemo.service;

import facosta.jwtdemo.exceptions.InvalidRequestException;
import facosta.jwtdemo.models.User;
import facosta.jwtdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService
{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserManagementService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(Long id)
    {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public User addNewUser(String username, String nonEncodedPassword)
    {
        Optional exists = userRepository.findByUsername(username);
        if (exists.isPresent())
            throw new InvalidRequestException("El usuario ya existe");

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(nonEncodedPassword));

        return userRepository.save(user);
    }
}
