package com.NewsTok.User.Services;

import com.NewsTok.User.Models.AdminRequest;
import com.NewsTok.User.Models.AdminResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class testAdminLoginService {

    @Autowired
    private AdminLoginService adminLoginService;

    @Value("${UserToAdminAuthentication.email}")
    private String email;

    @Value("${UserToAdminAuthentication.password}")
    private String password;

    private final RestTemplate restTemplate;



    public testAdminLoginService() {

        this.restTemplate = new RestTemplate();
    }

    public String testAdminlogin(){

        AdminRequest adminRequest=new AdminRequest();
        adminRequest.setEmail(this.email);
        adminRequest.setPassword(this.password);

       AdminResponse adminResponse =adminLoginService.loginUser(adminRequest);


        String url = "http://localhost:8080/admin/testToken";
        System.out.println();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + adminResponse.getToken());

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);



        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return responseEntity.getBody();



    }
}
