package com.dynamicrbac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dynamicrbac.model.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRoleId(Long roleId);
}
