package com.ras.myapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ras.myapp.domain.AllApps;
import com.ras.myapp.repository.ClientRepository;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AppScanService {

    private final TokenService tokenService;
    private final String baseUrl = "https://cloud.appscan.com";

    public AppScanService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // General method for making GET requests
    private String makeGetRequest(String endpoint) {
        try {
            String token = tokenService.getBearerToken();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.baseUrl + endpoint, HttpMethod.GET, entity, String.class);

            if (response.getStatusCodeValue() == 200) {
                return response.getBody();
            } else {
                System.out.println("API call failed. Response Code: " + response.getStatusCodeValue());
                return "{}";
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public String getToken() {
        return tokenService.getBearerToken();
    }

    public String getAllApplications() {
        String endpoint = "/api/v4/Apps?%24top=100&%24count=false";
        return makeGetRequest(endpoint);
    }

    public String getDetailsByApp(String appId) {
        String response = getAllApplications();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            // Navigate to the "Items" array
            JsonNode itemsNode = rootNode.get("Items");

            if (itemsNode.isArray()) {
                // Iterate through the array to find the application with the specified appId
                for (JsonNode appNode : itemsNode) {
                    String currentAppId = appNode.get("Id").asText();

                    // Check if the current application has the target appId
                    if (currentAppId.equals(appId)) {
                        // Format the JSON string with indentation and line breaks
                        String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(appNode);
                        return formattedJson;
                    }
                }
            }

            // Return an appropriate message if the application with the specified appId is not found
            return "Application with ID " + appId + " not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }

    public String getApplicationScans() {
        String endpoint = "/api/v4/Scans?%24top=100&%24count=false";
        return makeGetRequest(endpoint);
    }

    public String getScansByApp(String appId) {
        String response = getApplicationScans();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            // Navigate to the "Items" array
            JsonNode itemsNode = rootNode.get("Items");

            if (itemsNode.isArray()) {
                // Iterate through the array to find the application with the specified appId
                for (JsonNode appNode : itemsNode) {
                    String currentAppId = appNode.get("AppId").asText();

                    // Check if the current application has the target appId
                    if (currentAppId.equals(appId)) {
                        // Format the JSON string with indentation and line breaks
                        String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(appNode);
                        return formattedJson;
                    }
                }
            }

            // Return an appropriate message if the application with the specified appId is not found
            return "Application with ID " + appId + " not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }

    public String getApplicationIssue(String appId) {
        //String appId = "521b5339-7461-4f5d-804a-6027ff441dc1";
        String endpoint = "/api/v4/Issues/Application/" + appId + "?applyPolicies=None&%24top=100&%24count=false";
        return makeGetRequest(endpoint);
    }
}
