package com.movieaccess.rest.service;

import com.movieaccess.rest.dao.RoleDao;
import com.movieaccess.rest.dao.UserDao;
import com.movieaccess.rest.model.Role;
import com.movieaccess.rest.model.User;
import com.movieaccess.rest.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);


    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + s));
        getRolesByUser(user);
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(long id) {
        User user = userDao.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        getRolesByUser(user);
        return UserPrincipal.create(user);
    }

    @Transactional
    public void getRolesByUser(User user){
        Set<Role> roles;
        roles = this.roleDao.findRolesByUserId(user.getId());
        user.setRoles(roles);
        logger.info(user.toString());
    }

    @Transactional
    public User save(User user){
        User newUser = userDao.save(user);
        roleDao.saveRoles(user.getRoles(), newUser.getId());
        return newUser;
    }
}
