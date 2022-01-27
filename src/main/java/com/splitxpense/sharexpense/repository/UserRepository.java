package com.splitxpense.sharexpense.repository;

import com.splitxpense.sharexpense.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User save(User user);

  Optional<User> findById(Long id);
}
