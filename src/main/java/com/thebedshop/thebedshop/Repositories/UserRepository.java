package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String username);
    List<User> findByEmailAndPassword(String text, String textAgain);
    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);
}
