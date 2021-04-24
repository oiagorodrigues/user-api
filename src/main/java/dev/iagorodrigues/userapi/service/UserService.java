package dev.iagorodrigues.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.iagorodrigues.userapi.model.User;
import dev.iagorodrigues.userapi.repository.UserRepository;
import dev.iagorodrigues.userapi.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users
            .stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO findById (long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserDTO::convert).orElse(null);
    }

    public UserDTO findBySocialSecurityName (String socialSecurityName) {
        User user = userRepository.findBySocialSecurityNumber(socialSecurityName);
        return user != null ? UserDTO.convert(user) : null;
    }

    public List<UserDTO> queryByName (String name) {
        List<User> users = userRepository.queryByNameLike(name);
        return users
            .stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
    }

    public UserDTO save (UserDTO userDTO) {
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(_user -> userRepository.delete(_user));
        return null;
    }

}
