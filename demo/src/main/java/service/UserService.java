package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? new UserDTO(user) : null;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }
}
