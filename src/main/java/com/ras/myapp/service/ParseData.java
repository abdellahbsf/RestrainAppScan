package com.ras.myapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ras.myapp.domain.AllApps;
import com.ras.myapp.domain.AppDetails;
import com.ras.myapp.domain.AppIssue;
import com.ras.myapp.domain.AppScans;
import com.ras.myapp.repository.AllAppsRepository;
import com.ras.myapp.repository.AppDetailsRepository;
import com.ras.myapp.repository.AppIssueRepository;
import com.ras.myapp.repository.AppScansRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParseData {

    private final AllAppsRepository allAppsRepository;
    private final AppDetailsRepository appDetailsRepository;
    private final AppScansRepository appScansRepository;
    private final AppIssueRepository appIssueRepository;

    @Autowired
    public ParseData(
        AllAppsRepository allAppsRepository,
        AppDetailsRepository appDetailsRepository,
        AppScansRepository appScansRepository,
        AppIssueRepository appIssueRepository
    ) {
        this.allAppsRepository = allAppsRepository;
        this.appDetailsRepository = appDetailsRepository;
        this.appScansRepository = appScansRepository;
        this.appIssueRepository = appIssueRepository;
    }

    public void listOfApplications(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Assuming "Items" is an array
            JsonNode itemsArray = rootNode.get("Items");

            if (itemsArray != null && itemsArray.isArray()) {
                for (JsonNode item : itemsArray) {
                    System.out.println("Processing item:");

                    AllApps allApps = new AllApps();

                    // Create a map to store mappings between JSON keys and corresponding setters
                    Map<String, Consumer<String>> setterMap = new HashMap<>();
                    setterMap.put("Id", allApps::setAppId);
                    setterMap.put("Name", allApps::setName);

                    // Iterate through the fields of each item
                    item
                        .fields()
                        .forEachRemaining(entry -> {
                            String key = entry.getKey();
                            JsonNode value = entry.getValue();

                            // Match keys with setters using the map
                            //AppScans appScans = new AppScans();
                            setterMap.getOrDefault(key, v -> {}).accept(value.asText());

                            allAppsRepository.save(allApps);
                        });
                }
            } else {
                System.out.println("No 'Items' array found in the JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applicationDetails(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            AppDetails appDetails = new AppDetails();

            // Adjust the following lines based on your actual class and setter methods
            appDetails.setAppId(rootNode.get("Id").asText());
            appDetails.setRiskRating(rootNode.get("RiskRating").asText());
            appDetails.setCriticalIssues(rootNode.get("CriticalIssues").asInt());
            appDetails.setHighIssues(rootNode.get("HighIssues").asInt());
            appDetails.setMediumIssues(rootNode.get("MediumIssues").asInt());
            appDetails.setLowIssues(rootNode.get("LowIssues").asInt());
            appDetails.setInformationalIssues(rootNode.get("InformationalIssues").asInt());
            appDetails.setIssuesInProgress(rootNode.get("IssuesInProgress").asInt());
            appDetails.setMaxSeverity(rootNode.get("MaxSeverity").asText());
            appDetails.setCorrelationState(rootNode.get("CorrelationState").asText());
            appDetails.setrRMaxSeverity(rootNode.get("RR_MaxSeverity").asInt());
            appDetails.setAssetGroupId(rootNode.get("AssetGroupId").asText());
            appDetails.setBusinessImpact(rootNode.get("BusinessImpact").asText());
            appDetails.setUrl(rootNode.get("Url").asText());
            appDetails.setDescription(rootNode.has("Description") ? rootNode.get("Description").asText() : null);
            appDetails.setBusinessUnit(rootNode.has("BusinessUnit") ? rootNode.get("BusinessUnit").asText() : null);
            appDetails.setBusinessUnitId(rootNode.has("BusinessUnitId") ? rootNode.get("BusinessUnitId").asText() : null);
            appDetails.setTypes(rootNode.has("Type") ? rootNode.get("Type").asText() : null);
            appDetails.setTechnology(rootNode.has("Technology") ? rootNode.get("Technology").asText() : null);
            appDetails.setTestingStatus(rootNode.has("TestingStatus") ? rootNode.get("TestingStatus").asText() : null);
            appDetails.setAppHosts(rootNode.has("Hosts") ? rootNode.get("Hosts").asText() : null);
            appDetails.setCollateralDamagePotential(
                rootNode.has("CollateralDamagePotential") ? rootNode.get("CollateralDamagePotential").asText() : null
            );
            appDetails.setTargetDistribution(rootNode.has("TargetDistribution") ? rootNode.get("TargetDistribution").asText() : null);
            appDetails.setConfidentialityRequirement(
                rootNode.has("ConfidentialityRequirement") ? rootNode.get("ConfidentialityRequirement").asText() : null
            );
            appDetails.setIntegrityRequirement(rootNode.has("IntegrityRequirement") ? rootNode.get("IntegrityRequirement").asText() : null);
            appDetails.setAvailabilityRequirement(
                rootNode.has("AvailabilityRequirement") ? rootNode.get("AvailabilityRequirement").asText() : null
            );
            appDetails.setTester(rootNode.has("Tester") ? rootNode.get("Tester").asText() : null);
            appDetails.setBusinessOwner(rootNode.has("BusinessOwner") ? rootNode.get("BusinessOwner").asText() : null);
            appDetails.setDevelopmentContact(rootNode.has("DevelopmentContact") ? rootNode.get("DevelopmentContact").asText() : null);
            appDetails.setPreferredOfferingType(
                rootNode.has("PreferredOfferingType") ? rootNode.get("PreferredOfferingType").asText() : null
            );
            appDetails.setAssetGroupName(rootNode.has("AssetGroupName") ? rootNode.get("AssetGroupName").asText() : null);
            //appDetails.setDateCreated(rootNode.has("DateCreated") ? LocalDateTime.parse(rootNode.get("DateCreated").asText()) : null);
            //appDetails.setLastUpdated(rootNode.has("LastUpdated") ? LocalDateTime.parse(rootNode.get("LastUpdated").asText()) : null);
            appDetails.setLastComment(rootNode.has("LastComment") ? rootNode.get("LastComment").asText() : null);
            appDetails.setCreatedBy(rootNode.has("CreatedBy") ? rootNode.get("CreatedBy").asText() : null);
            appDetails.setNewIssues(rootNode.has("NewIssues") ? rootNode.get("NewIssues").asInt() : 0);
            appDetails.setOpenIssues(rootNode.has("OpenIssues") ? rootNode.get("OpenIssues").asInt() : 0);
            appDetails.setTotalIssues(rootNode.has("TotalIssues") ? rootNode.get("TotalIssues").asInt() : 0);
            appDetails.setOverallCompliance(rootNode.has("OverallCompliance") ? rootNode.get("OverallCompliance").asBoolean() : false);
            appDetails.setCanBeDeleted(rootNode.has("CanBeDeleted") ? rootNode.get("CanBeDeleted").asBoolean() : false);
            appDetails.setLockedToSubscription(
                rootNode.has("LockedToSubscription") ? rootNode.get("LockedToSubscription").asBoolean() : false
            );
            appDetails.setTotalScans(rootNode.has("TotalScans") ? rootNode.get("TotalScans").asInt() : 0);

            // Add more properties as needed

            // Save appDetails to the database or perform other operations
            appDetailsRepository.save(appDetails);
            /*
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Assuming "Items" is an array
            JsonNode itemsArray = rootNode.get("Items");

            if (itemsArray != null && itemsArray.isArray()) {
                for (JsonNode item : itemsArray) {
                    System.out.println("Processing item:");

                    AppDetails appDetails = new AppDetails();

                    // Create a map to store mappings between JSON keys and corresponding setters
                    Map<String, Consumer<String>> setterMap = new HashMap<>();
                    setterMap.put("Id", value -> appDetails.setAppId(value));
                    setterMap.put("RiskRating", value -> appDetails.setRiskRating(value));
                    setterMap.put("CriticalIssues", value -> appDetails.setCriticalIssues(Integer.valueOf(value)));
                    setterMap.put("HighIssues", value -> appDetails.setHighIssues(Integer.valueOf(value)));
                    setterMap.put("MediumIssues", value -> appDetails.setMediumIssues(Integer.valueOf(value)));
                    setterMap.put("LowIssues", value -> appDetails.setLowIssues(Integer.valueOf(value)));
                    setterMap.put("InformationalIssues", value -> appDetails.setInformationalIssues(Integer.parseInt(value)));
                    setterMap.put("IssuesInProgress", value -> appDetails.setIssuesInProgress(Integer.parseInt(value)));
                    setterMap.put("MaxSeverity", appDetails::setMaxSeverity);
                    setterMap.put("CorrelationState", appDetails::setCorrelationState);
                    setterMap.put("RR_MaxSeverity", value -> appDetails.setrRMaxSeverity(Integer.parseInt(value)));
                    setterMap.put("AssetGroupId", appDetails::setAssetGroupId);
                    setterMap.put("BusinessImpact", appDetails::setBusinessImpact);
                    setterMap.put("Url", appDetails::setUrl);
                    setterMap.put("Description", appDetails::setDescription);
                    setterMap.put("BusinessUnit", appDetails::setBusinessUnit);
                    setterMap.put("BusinessUnitId", appDetails::setBusinessUnitId);
                    setterMap.put("Type", appDetails::setTypes);
                    setterMap.put("Technology", appDetails::setTechnology);
                    setterMap.put("TestingStatus", appDetails::setTestingStatus);
                    setterMap.put("Hosts", appDetails::setAppHosts);
                    setterMap.put("CollateralDamagePotential", appDetails::setCollateralDamagePotential);
                    setterMap.put("TargetDistribution", appDetails::setTargetDistribution);
                    setterMap.put("ConfidentialityRequirement", appDetails::setConfidentialityRequirement);
                    setterMap.put("IntegrityRequirement", appDetails::setIntegrityRequirement);
                    setterMap.put("AvailabilityRequirement", appDetails::setAvailabilityRequirement);
                    setterMap.put("Tester", appDetails::setTester);
                    setterMap.put("BusinessOwner", appDetails::setBusinessOwner);
                    setterMap.put("DevelopmentContact", appDetails::setDevelopmentContact);
                    setterMap.put("PreferredOfferingType", appDetails::setPreferredOfferingType);
                    setterMap.put("AssetGroupName", appDetails::setAssetGroupName);
                    //setterMap.put("DateCreated", appDetails::setDateCreated);
                    //setterMap.put("LastUpdated", appDetails::setLastUpdated);
                    setterMap.put("LastComment", appDetails::setLastComment);
                    setterMap.put("CreatedBy", appDetails::setCreatedBy);
                    setterMap.put("NewIssues", value -> appDetails.setNewIssues(Integer.parseInt(value)));
                    setterMap.put("OpenIssues", value -> appDetails.setOpenIssues(Integer.parseInt(value)));
                    setterMap.put("TotalIssues", value -> appDetails.setTotalIssues(Integer.parseInt(value)));
                    setterMap.put("OverallCompliance", value -> appDetails.setOverallCompliance(Boolean.parseBoolean(value)));
                    setterMap.put("CanBeDeleted", value -> appDetails.setCanBeDeleted(Boolean.parseBoolean(value)));
                    setterMap.put("LockedToSubscription", value -> appDetails.setLockedToSubscription(Boolean.parseBoolean(value)));
                    setterMap.put("TotalScans", value -> appDetails.setTotalScans(Integer.parseInt(value)));

                    // Iterate through the fields of each item
                    item
                        .fields()
                        .forEachRemaining(entry -> {
                            String key = entry.getKey();
                            JsonNode value = entry.getValue();

                            // Match keys with setters using the map
                            //AppScans appScans = new AppScans();
                            setterMap.getOrDefault(key, v -> {}).accept(value.asText());

                            appDetailsRepository.save(appDetails);
                        });
                }
            } else {
                System.out.println("No 'Items' array found in the JSON.");
            }

             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applicationScans(String jsonScans) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonScans);

            AppScans applicationScans = new AppScans();

            applicationScans.setAppId(rootNode.get("appId").asText());
            applicationScans.setNameScan(rootNode.get("Name").asText());
            applicationScans.setTechnology(rootNode.get("Technology").asText());
            applicationScans.setIastAgentType(rootNode.has("IastAgentType") ? rootNode.get("IastAgentType").asText() : null);
            applicationScans.setIastAgentStatus(rootNode.get("IastAgentStatus").asText());
            applicationScans.setUrlScan(rootNode.has("Url") ? rootNode.get("Url").asText() : null);
            applicationScans.setAppName(rootNode.get("AppName").asText());
            applicationScans.setTestOptimizationLevel(rootNode.get("TestOptimizationLevel").asText());
            applicationScans.setNumberOfExecutions(rootNode.get("NumberOfExecutions").asInt());

            // Save applicationScans to the database or perform other operations
            appScansRepository.save(applicationScans);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    public void applicationIssues(String jsonIssues) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonIssues);

            // Assuming "Items" is an array
            JsonNode itemsArray = rootNode.get("Items");

            if (itemsArray != null && itemsArray.isArray()) {
                for (JsonNode item : itemsArray) {
                    System.out.println("Processing item:");

                    AppIssue issues = new AppIssue();

                    // Create a map to store mappings between JSON keys and corresponding setters
                    Map<String, Consumer<String>> setterMap = new HashMap<>();
                    // Continue adding entries to the setterMap for each field
                    setterMap.put("Id", value -> issues.setAppId(value));
                    setterMap.put("Severity", value -> issues.setSeverities(value));
                    setterMap.put("Language", value -> issues.setProgrammingLanguage(value));
                    setterMap.put("Status", value -> issues.setStatusIssue(value));
                    setterMap.put("IssueType", value -> issues.setIssueType(value));
                    setterMap.put("Location", value -> issues.setLocationIssue(value));
                    setterMap.put("DateCreated", value -> issues.setDateCreated(ZonedDateTime.from(Instant.parse(value))));
                    setterMap.put("LastUpdated", value -> issues.setLastUpdated(ZonedDateTime.from(Instant.parse(value))));
                    setterMap.put("LastFound", value -> issues.setLastFound(ZonedDateTime.from(Instant.parse(value))));
                    setterMap.put("CallingMethod", value -> issues.setCallingMethod(value));
                    setterMap.put("IsNewInScope", value -> issues.setIsNewInScope(Boolean.parseBoolean(value)));
                    setterMap.put("LibraryName", value -> issues.setLibraryName(value));
                    setterMap.put("LibraryVersion", value -> issues.setLibraryVersion(value));
                    setterMap.put("ScaTechnology", value -> issues.setScaTechnology(value));
                    setterMap.put("FGStatus", value -> issues.setfGStatus(value));
                    setterMap.put("ApplicationId", value -> issues.setApplicationId(value));
                    setterMap.put("FixGroupId", value -> issues.setFixGroupId(value));
                    setterMap.put("Api", value -> issues.setApiIssue(value));
                    setterMap.put("Source", value -> issues.setSourceIssue(value));
                    setterMap.put("Context", value -> issues.setContextIssue(value));
                    setterMap.put("AppscanVulnId", value -> issues.setAppscanVulnId(value));
                    setterMap.put("CallingLine", value -> issues.setCallingLine(value));
                    setterMap.put("Class", value -> issues.setClassIssue(value));
                    setterMap.put("Cve", value -> issues.setCveIssue(value));

                    // Iterate through the fields of each item
                    item
                        .fields()
                        .forEachRemaining(entry -> {
                            String key = entry.getKey();
                            JsonNode value = entry.getValue();

                            // Match keys with setters using the map
                            //AppScans appScans = new AppScans();
                            setterMap.getOrDefault(key, v -> {}).accept(value.asText());

                            appIssueRepository.save(issues);
                            //createdByService.save(createdByUser);
                            System.out.println(key + ": " + value);
                        });
                    System.out.println("\n");
                }
            } else {
                System.out.println("No 'Items' array found in the JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
