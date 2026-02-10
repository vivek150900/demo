package com.dynamicrbac.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dynamicrbac.model.RolePermission;
import com.dynamicrbac.model.UserRole;
import com.dynamicrbac.repository.RolePermissionRepository;
import com.dynamicrbac.repository.UserRoleRepository;

import java.io.Serializable;
import java.util.List;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public CustomPermissionEvaluator(UserRoleRepository userRoleRepository,
                                     RolePermissionRepository rolePermissionRepository) {
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {

        String username = authentication.getName();
        String requiredPermission = permission.toString();

        List<UserRole> userRoles = userRoleRepository.findByUserUsername(username);

        for (UserRole ur : userRoles) {
            List<RolePermission> rolePermissions =
                    rolePermissionRepository.findByRoleId(ur.getRole().getId());

            for (RolePermission rp : rolePermissions) {
                if (rp.getPermission().getName().equals(requiredPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
