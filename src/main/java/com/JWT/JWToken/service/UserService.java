package com.JWT.JWToken.service;

import com.JWT.JWToken.model.JWTRequest;
import com.JWT.JWToken.repository.JPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private JPARepository jpaRepository;

    @Autowired
    public UserService(JPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public JWTRequest saveDeoartment(JWTRequest department) {

        return jpaRepository.save(department);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database

        return new User("admin","password",new ArrayList<>());
    }
}
