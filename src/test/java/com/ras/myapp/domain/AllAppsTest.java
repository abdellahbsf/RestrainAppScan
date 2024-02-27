package com.ras.myapp.domain;

import static com.ras.myapp.domain.AllAppsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.ras.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AllAppsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllApps.class);
        AllApps allApps1 = getAllAppsSample1();
        AllApps allApps2 = new AllApps();
        assertThat(allApps1).isNotEqualTo(allApps2);

        allApps2.setId(allApps1.getId());
        assertThat(allApps1).isEqualTo(allApps2);

        allApps2 = getAllAppsSample2();
        assertThat(allApps1).isNotEqualTo(allApps2);
    }
}
