package com.movieaccess.rest.dao;

import com.movieaccess.rest.controller.AuthenticationController;
import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return Optional.ofNullable(this.jdbcTemplate.queryForObject("select * from roles where name = ?",
                new Object[]{roleName.name()},
                (rs, i) -> new Role(
                        rs.getLong("id"),
                        RoleName.valueOf(rs.getString("name"))
                )));
    }

    @Override
    public Set<Role> findRolesByUserId(long userId) {

        logger.info("inside findRolesByUserId, userId = "+userId);

        return this.jdbcTemplate.query("select u.id, u.username, r.name from users u inner join user_roles_map ur on ur.userid = u.id and ur.active = true inner join roles r on r.id = ur.roleid",
                new Object[]{},
                (rs) -> {

                    logger.info("inside map lambda");

                    Set<Role> roles = new HashSet<Role>();

                    logger.info(roles.toString());

                    while (rs.next()) {
                        roles.add(new Role(RoleName.valueOf(rs.getString("name"))));
                        logger.info("adding roles");
                    }

                    logger.info(roles.toString());

                    return roles;
                });
    }
}
