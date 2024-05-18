package com.spe.workwize.service.init;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddAdminImpl implements AddAdmin{


    @Override
    public void postToApi() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8086/users/registerAdmin";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username","testAdmin");
        requestBody.put("password","password");
        requestBody.put("email","testAdmin@admin.org");
        requestBody.put("name","Test Admin");
        requestBody.put("phone","1234567890");
        requestBody.put("businessTitle","Admin");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try{
            restTemplate.postForEntity(apiUrl, requestEntity, String.class);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
