package com.NewsTok.User.Services;

import com.NewsTok.User.Models.AdminRequest;
import com.NewsTok.User.Models.AdminResponse;
import com.NewsTok.User.Models.DashBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AdminRequestService {
    @Autowired
    private AdminLoginService adminLoginService;

    @Value("${UserToAdminAuthentication.email}")
    private String email;

    @Value("${UserToAdminAuthentication.password}")
    private String password;

    private final RestTemplate restTemplate;



    public AdminRequestService() {

        this.restTemplate = new RestTemplate();
    }

    public DashBoard getAdminDashboard(){
         return new DashBoard();
    }
}
