package com.ras.myapp.web.rest;

import com.ras.myapp.domain.AllApps;
import com.ras.myapp.repository.AllAppsRepository;
import com.ras.myapp.service.AppScanService;
import com.ras.myapp.service.ParseData;
import com.ras.myapp.service.TokenService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppScanController {

    private final ParseData parseData;
    private final AllAppsRepository allAppsRepository;

    @Autowired
    private AppScanService appScanService;

    private TokenService tokenService;

    @Autowired
    public AppScanController(ParseData parseData, AllAppsRepository allAppsRepository) {
        this.parseData = parseData;
        this.allAppsRepository = allAppsRepository;
    }

    @GetMapping("/token")
    public String getToken() {
        try {
            return appScanService.getToken();
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
            return "Error occurred";
        }
    }

    @GetMapping("/all/applications")
    public ResponseEntity<String> getAllApplications() {
        try {
            String applicationsJson = appScanService.getAllApplications();
            return ResponseEntity.ok(applicationsJson);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }

    /*
    @GetMapping("/all/applications")
    public String getAllApplications() {
        try {
            String response = appScanService.getAllApplications();

            parseData.listOfApplications(response);

            return appScanService.getAllApplications();
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
            return "Error occurred";
        }
    }

 */

    @GetMapping("/application/details/{appId}")
    public String getDetailsByApp(@PathVariable String appId) {
        try {
            return appScanService.getDetailsByApp(appId);
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
            return "Error occurred";
        }
    }

    @GetMapping("/application-scans")
    public String getApplicationScans() {
        try {
            return appScanService.getApplicationScans();
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
            return "Error occurred";
        }
    }

    @GetMapping("/application-scans/{appId}")
    public String getApplicationScansByApp(@PathVariable String appId) {
        try {
            return appScanService.getScansByApp(appId);
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
            return "Error occurred";
        }
    }

    @GetMapping("/application-issues/{appId}")
    public String getApplicationIssues(@PathVariable String appId) {
        try {
            return appScanService.getApplicationIssue(appId);
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
            return "Error occurred";
        }
    }
    /*
    @PostMapping("/all/applications")
    public ResponseEntity<List<AllApps>> postAllApplications(@RequestBody String jsonResponse) {
        try {
            if (jsonResponse != null) {
                List<AllApps> appList = parseData.listOfApplication(jsonResponse);

                // Save all entities in a batch
                allAppsRepository.saveAll(appList);

                // Print successful processing message to the console
                System.out.println("Successfully processed " + appList.size() + " applications.");

                return new ResponseEntity<>(appList, HttpStatus.OK);
            } else {
                // Print invalid input message to the console
                System.out.println("Received null JSON response.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Print the exception to the console
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     */
}
