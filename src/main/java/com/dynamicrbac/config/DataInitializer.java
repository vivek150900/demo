package com.dynamicrbac.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dynamicrbac.model.*;
import com.dynamicrbac.repository.*;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            UserRoleRepository userRoleRepository,
            RolePermissionRepository rolePermissionRepository) {

        return args -> {

            // User
            User admin = userRepository.findByUsername("admin")
                    .orElseGet(() -> {
                        User u = new User();
                        u.setUsername("admin");
                        u.setPassword("admin");
                        return userRepository.save(u);
                    });

            User user = userRepository.findByUsername("user")
                    .orElseGet(() -> {
                        User u = new User();
                        u.setUsername("user");
                        u.setPassword("user");
                        return userRepository.save(u);
                    });

            // Role
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ADMIN");
                        return roleRepository.save(r);
                    });

            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("USER");
                        return roleRepository.save(r);
                    });

            // permission
            Permission createRole =
                    createPermission(permissionRepository, "CREATE_ROLE");

            Permission createPermission =
                    createPermission(permissionRepository, "CREATE_PERMISSION");

            Permission assignRole =
                    createPermission(permissionRepository, "ASSIGN_ROLE");

            Permission readSecureData =
                    createPermission(permissionRepository, "READ_SECURE_DATA");
            
            Permission assignPermission =
                    createPermission(permissionRepository, "ASSIGN_PERMISSION");

            // RolePemission
            assignPermission(rolePermissionRepository, adminRole, createRole);
            assignPermission(rolePermissionRepository, adminRole, createPermission);
            assignPermission(rolePermissionRepository, adminRole, assignRole);
            assignPermission(rolePermissionRepository, adminRole, readSecureData);
            assignPermission(rolePermissionRepository, adminRole, assignPermission);

            assignPermission(rolePermissionRepository, userRole, readSecureData);

            // UserRole
            assignRole(userRoleRepository, admin, adminRole);
            assignRole(userRoleRepository, user, userRole);
        };
    }

    // HelperMethos

    private Permission createPermission(PermissionRepository repo, String name) {
        return repo.findByName(name)
                .orElseGet(() -> {
                    Permission p = new Permission();
                    p.setName(name);
                    return repo.save(p);
                });
    }

    private void assignPermission(RolePermissionRepository repo,
                                  Role role,
                                  Permission permission) {

        boolean exists = repo.findByRoleId(role.getId()).stream()
                .anyMatch(rp ->
                        rp.getPermission().getName().equals(permission.getName()));

        if (!exists) {
            RolePermission rp = new RolePermission();
            rp.setRole(role);
            rp.setPermission(permission);
            repo.save(rp);
        }
    }

    private void assignRole(UserRoleRepository repo,
                            User user,
                            Role role) {

        boolean exists = repo.findByUserUsername(user.getUsername()).stream()
                .anyMatch(ur ->
                        ur.getRole().getName().equals(role.getName()));

        if (!exists) {
            UserRole ur = new UserRole();
            ur.setUser(user);
            ur.setRole(role);
            repo.save(ur);
        }
    }
}
