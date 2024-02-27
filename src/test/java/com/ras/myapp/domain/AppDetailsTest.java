package com.ras.myapp.domain;

import static com.ras.myapp.domain.AppDetailsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.ras.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AppDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppDetails.class);
        AppDetails appDetails1 = getAppDetailsSample1();
        AppDetails appDetails2 = new AppDetails();
        assertThat(appDetails1).isNotEqualTo(appDetails2);

        appDetails2.setId(appDetails1.getId());
        assertThat(appDetails1).isEqualTo(appDetails2);

        appDetails2 = getAppDetailsSample2();
        assertThat(appDetails1).isNotEqualTo(appDetails2);
    }
}
