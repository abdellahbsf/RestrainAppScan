package com.ras.myapp.web.rest;


import com.ras.myapp.service.GetTokenService;
import com.ras.myapp.service.dto.ApiKeyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppScanRessource {

    private final GetTokenService getTokenService;

    public AppScanRessource(GetTokenService getTokenService) {
        this.getTokenService = getTokenService;
    }

    @PostMapping("/processApiKeys")
    public ResponseEntity<String> processApiKeys(@RequestBody ApiKeyRequest apiKeyRequest) {
        // Extract apiKeyId and apiKeySecret from apiKeyRequest
        //String apiKeyId = apiKeyRequest.getApiKeyId();
        //String apiKeySecret = apiKeyRequest.getApiKeySecret();
        String apiUrl = "https://cloud.appscan.com/api/V2/Account/ApiKeyLogin";

        String token = getTokenService.getBearerToken(apiUrl, apiKeyRequest);

        // Rest of your processing logic here...

        return ResponseEntity.ok("Api keys received successfully" + token);
    }
}
