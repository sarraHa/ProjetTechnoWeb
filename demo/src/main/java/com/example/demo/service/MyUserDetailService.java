package com.example.demo.service;


import java.util.Optional;

import com.example.demo.Entity.User;
import com.example.demo.Entity.MyUserDetails;

import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user =  utilisateurRepository.findByLogin(login);
        System.out.println("************************************");

        user.orElseThrow(() -> new UsernameNotFoundException("Not found : " + login));

        System.out.println("************************************");

        System.out.println(user.orElseThrow().getLogin());
        System.out.println(user.orElseThrow().getMdp());

        return user.map(MyUserDetails::new).get(); 
    }
}
