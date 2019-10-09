package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.RoleName;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleDao {
    Optional<Role> findByName(RoleName roleName);
    Set<Role> findRolesByUserId(long userId);
    void saveRoles(Set<Role> roles, int userId);
}
