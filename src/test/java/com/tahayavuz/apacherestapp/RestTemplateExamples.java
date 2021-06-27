package com.tahayavuz.apacherestapp;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class RestTemplateExamples {

    public static final String API_ROOT = "https://api.github.com";

    @Test
    public void getRepositories() throws Exception {
        String apiUrl = API_ROOT + "/orgs/apache/repos";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }
}