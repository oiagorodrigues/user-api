package dev.iagorodrigues.userapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import dev.iagorodrigues.userapi.dto.UserDTO;
import dev.iagorodrigues.userapi.service.UserService;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        userDTO.setRegisterDate(new Date().toInstant());
        return userService.save(userDTO);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users/{socialSecurityNumber}")
    public UserDTO getUser(@PathVariable String socialSecurityNumber) {
        return userService.findBySocialSecurityName(socialSecurityNumber);
    }

    @GetMapping("/users/search")
    public List<UserDTO> searchUser(@RequestParam String name) {
        return userService.queryByName(name);
    }

    @DeleteMapping("/users/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

}
