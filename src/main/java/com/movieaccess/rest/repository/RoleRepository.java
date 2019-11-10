package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from Role r where r.name = :roleName", nativeQuery = true)
    Optional<Role> findByName(@Param("roleName") RoleName roleName);

    @Query(value = "select * from Role r", nativeQuery = true)
    List<Role> findAll();
}