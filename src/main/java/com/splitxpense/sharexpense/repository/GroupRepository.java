package com.splitxpense.sharexpense.repository;

import com.splitxpense.sharexpense.models.Group;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
  Group save(Group group);

  @Override
  Optional<Group> findById(Long id);
}
