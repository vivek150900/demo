package com.dynamicrbac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dynamicrbac.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserUsername(String username);
}
