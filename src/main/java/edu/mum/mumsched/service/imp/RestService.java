package edu.mum.mumsched.service.imp;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSON() {
        String url = "http://localhost:8001/api/section";
        System.out.println(this.restTemplate.getForObject(url, String.class));
        return this.restTemplate.getForObject(url, String.class);
    }
}