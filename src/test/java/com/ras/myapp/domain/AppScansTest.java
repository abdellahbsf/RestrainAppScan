package com.ras.myapp.domain;

import static com.ras.myapp.domain.AppScansTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.ras.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AppScansTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppScans.class);
        AppScans appScans1 = getAppScansSample1();
        AppScans appScans2 = new AppScans();
        assertThat(appScans1).isNotEqualTo(appScans2);

        appScans2.setId(appScans1.getId());
        assertThat(appScans1).isEqualTo(appScans2);

        appScans2 = getAppScansSample2();
        assertThat(appScans1).isNotEqualTo(appScans2);
    }
}
