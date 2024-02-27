package com.ras.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AppScansTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static AppScans getAppScansSample1() {
        return new AppScans()
            .id(1L)
            .appId("appId1")
            .nameScan("nameScan1")
            .technology("technology1")
            .iastAgentType("iastAgentType1")
            .iastAgentStatus("iastAgentStatus1")
            .urlScan("urlScan1")
            .appName("appName1")
            .testOptimizationLevel("testOptimizationLevel1")
            .numberOfExecutions(1);
    }

    public static AppScans getAppScansSample2() {
        return new AppScans()
            .id(2L)
            .appId("appId2")
            .nameScan("nameScan2")
            .technology("technology2")
            .iastAgentType("iastAgentType2")
            .iastAgentStatus("iastAgentStatus2")
            .urlScan("urlScan2")
            .appName("appName2")
            .testOptimizationLevel("testOptimizationLevel2")
            .numberOfExecutions(2);
    }

    public static AppScans getAppScansRandomSampleGenerator() {
        return new AppScans()
            .id(longCount.incrementAndGet())
            .appId(UUID.randomUUID().toString())
            .nameScan(UUID.randomUUID().toString())
            .technology(UUID.randomUUID().toString())
            .iastAgentType(UUID.randomUUID().toString())
            .iastAgentStatus(UUID.randomUUID().toString())
            .urlScan(UUID.randomUUID().toString())
            .appName(UUID.randomUUID().toString())
            .testOptimizationLevel(UUID.randomUUID().toString())
            .numberOfExecutions(intCount.incrementAndGet());
    }
}
