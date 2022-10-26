package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.UserDao;
import com.ideas2it.employee.config.CustomUserDetails;
import com.ideas2it.employee.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> user = userDao.findByUserName(username);
        if (user.isPresent()) {
            User retrievedUser = user.get();
            CharSequence encodedPassword = encoder.encode(retrievedUser.getPassword());
            retrievedUser.setPassword(encodedPassword.toString());
            return new CustomUserDetails(retrievedUser);
        }
        throw new UsernameNotFoundException("Username Not Found");
    }
}
