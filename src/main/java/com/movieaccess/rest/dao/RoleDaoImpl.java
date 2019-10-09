package com.movieaccess.rest.dao;

import com.movieaccess.rest.controller.AuthenticationController;
import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

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

                    while (rs.next()) {
                        roles.add(new Role(RoleName.valueOf(rs.getString("name"))));
                    }

                    return roles;
                });
    }

    @Override
    public void saveRoles(Set<Role> roles, int userId) {
        try{
            List<Object[]> batch = new ArrayList<Object[]>();
            for(Role role : roles){
                Object[] values = {userId, role.getId()};
                batch.add(values);
            }

            jdbcTemplate.batchUpdate("insert into user_roles_map (userid, roleid) values (?, ?)", batch);
        } catch (RuntimeException e){
            logger.error("Error batch inserting roles", e);
        }
    }


}
