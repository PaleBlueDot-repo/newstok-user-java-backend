package com.NewsTok.User.Services;

import com.NewsTok.User.Models.AdminRequest;
import com.NewsTok.User.Models.AdminResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AdminLoginService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Constructor-based dependency injection
    public AdminLoginService() {
        this.restTemplate = new RestTemplate(); // Initialize RestTemplate here
    }

    public AdminResponse loginUser(AdminRequest userRequest) {
        String url = "http://localhost:8080/admin/login";

        // Convert UserRequest to JSON
        String userRequestJson = convertUserRequestToJson(userRequest);

        // Set the Content-Type header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create an HttpEntity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(userRequestJson, headers);

        // Send POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String responseJson = responseEntity.getBody();
        System.out.println("Received JSON Response: " + responseJson);

        try {
            return objectMapper.readValue(responseJson, AdminResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error as needed
            return null;
        }
    }

    private String convertUserRequestToJson(AdminRequest userRequest) {
        try {
            return objectMapper.writeValueAsString(userRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // Return empty JSON object in case of error
        }
    }

}
