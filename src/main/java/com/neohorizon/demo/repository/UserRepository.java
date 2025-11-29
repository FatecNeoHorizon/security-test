package com.neohorizon.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.neohorizon.demo.model.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
}
