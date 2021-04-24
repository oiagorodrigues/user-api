package dev.iagorodrigues.userapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.iagorodrigues.userapi.dto.UserDTO;

@RestController
public class UserController {

    public static List<UserDTO> users = new ArrayList<>();

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO user) {
        user.setRegisterDate(new Date().toInstant());
        users.add(user);
        return user;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return users;
    }

    @GetMapping("/users/{socialSecurityNumber}")
    public UserDTO getUser(@PathVariable String socialSecurityNumber) {
        for (UserDTO user : users) {
            if (user.getSocialSecurityNumber().equals(socialSecurityNumber)) {
                return user;
            }
        }
        return null;
    }

    @DeleteMapping("/users/{socialSecurityNumber}")
    public boolean deleteUser(@PathVariable String socialSecurityNumber) {
        for (UserDTO user : users) {
            if (user.getSocialSecurityNumber().equals(socialSecurityNumber)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void initiateList() {
        UserDTO	userDTO	= new UserDTO();
        userDTO.setName("Eduardo");
        userDTO.setSocialSecurityNumber("123");
        userDTO.setAddress("Rua A");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setPhone("1234-5678");
        userDTO.setRegisterDate(new Date().toInstant());

        UserDTO	userDTO2 = new UserDTO();
        userDTO2.setName("Luiz");
        userDTO2.setSocialSecurityNumber("456");
        userDTO2.setAddress("Rua B");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setPhone("1234-3454");
        userDTO2.setRegisterDate(new Date().toInstant());

        UserDTO	userDTO3 = new UserDTO();
        userDTO3.setName("Bruna");
        userDTO3.setSocialSecurityNumber("789");
        userDTO3.setAddress("Rua C");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setPhone("5435-3214");
        userDTO3.setRegisterDate(new Date().toInstant());

        users.add(userDTO);
        users.add(userDTO2);
        users.add(userDTO3);
    }

}
