package com.NewsTok.User.Services;

import com.NewsTok.User.Models.User;
import com.NewsTok.User.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User appUser = repo.findByEmail(email);

        if (appUser != null) {
            UserDetails springUser= org.springframework.security.core.userdetails.User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword())
                    .build();
            return springUser;

        }

        return null;
    }

}
