package com.NewsTok.User.Controllers;


import com.NewsTok.User.Models.DashBoard;
import com.NewsTok.User.Services.AdminRequestService;
import com.NewsTok.User.Services.testAdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AdminRequestController {
    @Autowired
    private AdminRequestService adminRequestService;

    @GetMapping("/getDashboard")
    public DashBoard getDashboard() {

        return adminRequestService.getAdminDashboard();

    }

}
