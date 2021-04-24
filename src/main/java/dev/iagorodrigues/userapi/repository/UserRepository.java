package dev.iagorodrigues.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.iagorodrigues.userapi.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySocialSecurityNumber(String socialSecurityNumber);
    List<User> queryByNameLike(String name);

}
