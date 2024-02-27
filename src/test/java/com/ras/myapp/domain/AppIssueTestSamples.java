package com.ras.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AppIssueTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AppIssue getAppIssueSample1() {
        return new AppIssue()
            .id(1L)
            .appId("appId1")
            .programmingLanguage("programmingLanguage1")
            .severities("severities1")
            .statusIssue("statusIssue1")
            .issueType("issueType1")
            .locationIssue("locationIssue1")
            .callingMethod("callingMethod1")
            .libraryName("libraryName1")
            .libraryVersion("libraryVersion1")
            .scaTechnology("scaTechnology1")
            .fGStatus("fGStatus1")
            .applicationId("applicationId1")
            .fixGroupId("fixGroupId1")
            .apiIssue("apiIssue1")
            .sourceIssue("sourceIssue1")
            .contextIssue("contextIssue1")
            .appscanVulnId("appscanVulnId1")
            .callingLine("callingLine1")
            .classIssue("classIssue1")
            .cveIssue("cveIssue1");
    }

    public static AppIssue getAppIssueSample2() {
        return new AppIssue()
            .id(2L)
            .appId("appId2")
            .programmingLanguage("programmingLanguage2")
            .severities("severities2")
            .statusIssue("statusIssue2")
            .issueType("issueType2")
            .locationIssue("locationIssue2")
            .callingMethod("callingMethod2")
            .libraryName("libraryName2")
            .libraryVersion("libraryVersion2")
            .scaTechnology("scaTechnology2")
            .fGStatus("fGStatus2")
            .applicationId("applicationId2")
            .fixGroupId("fixGroupId2")
            .apiIssue("apiIssue2")
            .sourceIssue("sourceIssue2")
            .contextIssue("contextIssue2")
            .appscanVulnId("appscanVulnId2")
            .callingLine("callingLine2")
            .classIssue("classIssue2")
            .cveIssue("cveIssue2");
    }

    public static AppIssue getAppIssueRandomSampleGenerator() {
        return new AppIssue()
            .id(longCount.incrementAndGet())
            .appId(UUID.randomUUID().toString())
            .programmingLanguage(UUID.randomUUID().toString())
            .severities(UUID.randomUUID().toString())
            .statusIssue(UUID.randomUUID().toString())
            .issueType(UUID.randomUUID().toString())
            .locationIssue(UUID.randomUUID().toString())
            .callingMethod(UUID.randomUUID().toString())
            .libraryName(UUID.randomUUID().toString())
            .libraryVersion(UUID.randomUUID().toString())
            .scaTechnology(UUID.randomUUID().toString())
            .fGStatus(UUID.randomUUID().toString())
            .applicationId(UUID.randomUUID().toString())
            .fixGroupId(UUID.randomUUID().toString())
            .apiIssue(UUID.randomUUID().toString())
            .sourceIssue(UUID.randomUUID().toString())
            .contextIssue(UUID.randomUUID().toString())
            .appscanVulnId(UUID.randomUUID().toString())
            .callingLine(UUID.randomUUID().toString())
            .classIssue(UUID.randomUUID().toString())
            .cveIssue(UUID.randomUUID().toString());
    }
}
