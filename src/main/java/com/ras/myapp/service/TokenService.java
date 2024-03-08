package com.ras.myapp.service;

import com.ras.myapp.domain.Client;
import com.ras.myapp.repository.ClientRepository;
import jakarta.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private ClientRepository clientRepository;

    @Value("${API_KEY_ID}")
    private String apiKeyId;

    @Value("${API_KEY_SECRET}")
    private String apiKeySecret;

    //private final String apiKeyId = "44b9bc24-7d42-b8f9-c9cd-4b0aa157592f";

    //private final String apiKeySecret = "P+IpyjCdabBBmR12gyexVjdQkcbn+X87xWwnT94wHb5y";

    //private String apiKeyId = "";
    //private String apiKeySecret = "";
    private final String apiUrl = "https://cloud.appscan.com/api/V2/Account/ApiKeyLogin";

    @PostConstruct
    public void init() {
        Client client = clientRepository.findTopByOrderByIdDesc();
        if (client != null) {
            this.apiKeyId = client.getKeyId();
            this.apiKeySecret = client.getKeySecret();
        }
    }

    public String getBearerToken() {
        try {
            // Construct the token request
            HttpClient client = HttpClient.newHttpClient();

            String json = "{\"KeyId\":\"" + apiKeyId + "\", \"KeySecret\":\"" + apiKeySecret + "\"}";

            //String json = "{\"KeyId\":\"44b9bc24-7d42-b8f9-c9cd-4b0aa157592f\", \"KeySecret\":\"P+IpyjCdabBBmR12gyexVjdQkcbn+X87xWwnT94wHb5y\"}";

            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(this.apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            // Send the token request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse and return the token from the response
            if (response.statusCode() == 200) {
                // Extract token from JSON response directly
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
