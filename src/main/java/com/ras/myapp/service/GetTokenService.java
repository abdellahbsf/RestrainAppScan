package com.ras.myapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ras.myapp.service.dto.ApiKeyRequest;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GetTokenService {

    public String getBearerToken(String apiUrl, ApiKeyRequest apiKeyRequest) {
        try {
            // Construct the token request
            HttpClient client = HttpClient.newHttpClient();

            // Convert ApiKeyRequest to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(apiKeyRequest);

            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            // Send the token request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse and return the token from the response
            if (response.statusCode() == 200) {
                // Extract token from JSON response
                // Adjust this part based on the actual JSON structure of the response
                String jsonResponse = response.body();
                String token = jsonResponse.split("\"Token\":")[1].split(",")[0].replaceAll("\"", "").trim();
                return token;
            } else {
                System.out.println("Failed to obtain token. Status code: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
