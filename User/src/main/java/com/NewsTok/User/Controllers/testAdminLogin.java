package com.NewsTok.User.Controllers;


import com.NewsTok.User.Models.AdminRequest;
import com.NewsTok.User.Services.AdminLoginService;
import com.NewsTok.User.Services.testAdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class testAdminLogin {

     @Autowired
     private testAdminLoginService testAdminLoginService;

    @GetMapping("/testAdminLogin")
    public String testAdminLogin() {

        return  testAdminLoginService.testAdminlogin();

    }
}
