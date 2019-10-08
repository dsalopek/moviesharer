package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(this.jdbcTemplate.queryForObject("select * from users where username = ?",
                new Object[]{username},
                (rs, i) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        null,
                        rs.getBoolean("active")
                )));
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[] {id},
                (rs, i) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        null,
                        rs.getBoolean("active")
                )));
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean exists;
        try{
            exists = !findByUsername(username).isPresent();
            return exists;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean existsByEmail(String email) {
        return !Optional.ofNullable(this.jdbcTemplate.queryForObject("select * from users where email = ?",
                new Object[]{email},
                (rs, i) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        null,
                        rs.getBoolean("active")
                ))).isPresent();
    }



    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement("insert into users (username, email, active, password) " +
                            "values (?, ?, ?, ?)", new String[] {"id"});
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getEmail());
                    ps.setBoolean(3, user.isActive());
                    ps.setString(4, user.getPassword());

                    return ps;
                },
                keyHolder);

        user.setId((int)keyHolder.getKey());
        return user;
    }
}
