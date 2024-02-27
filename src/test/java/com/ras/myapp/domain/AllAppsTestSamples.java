package com.ras.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AllAppsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AllApps getAllAppsSample1() {
        return new AllApps().id(1L).appId("appId1").name("name1");
    }

    public static AllApps getAllAppsSample2() {
        return new AllApps().id(2L).appId("appId2").name("name2");
    }

    public static AllApps getAllAppsRandomSampleGenerator() {
        return new AllApps().id(longCount.incrementAndGet()).appId(UUID.randomUUID().toString()).name(UUID.randomUUID().toString());
    }
}
