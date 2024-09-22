package com.NewsTok.User.Runner;

import com.NewsTok.User.Models.User;
import com.NewsTok.User.Repositories.UserRepository;
import com.NewsTok.User.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class StartupRunner implements CommandLineRunner {

    @Value("${AdminToUserAuthentication.email}")
    private String adminEmail;
    @Value("${AdminToUserAuthentication.password}")
    private String adminPassword;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;




    @Override
    public void run(String... args) throws Exception {
        User appUser = new User();

        appUser.setEmail(adminEmail);
        appUser.setCreatedAt(new Date());
        var bcryptEncoder = new BCryptPasswordEncoder();
        appUser.setPassword(bcryptEncoder.encode(adminPassword));

        try {

            var otherUser = userRepository.findByEmail(adminEmail);
            if (otherUser != null) {
                System.out.println("already exist\n");
            }
            else {

                userRepository.save(appUser);
                String JwtToken = jwtService.createToken(appUser);
                var response = new HashMap<String, Object>();
                response.put("token", JwtToken);
                response.put("user", appUser);
                System.out.println("User account created\n");
            }

        }
        catch (Exception ex) {
            System.out.println("There is an Exception: "+ ex.getMessage());
        }

    }


}
