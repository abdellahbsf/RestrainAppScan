package com.ras.myapp.domain;

import static com.ras.myapp.domain.AppIssueTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.ras.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AppIssueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppIssue.class);
        AppIssue appIssue1 = getAppIssueSample1();
        AppIssue appIssue2 = new AppIssue();
        assertThat(appIssue1).isNotEqualTo(appIssue2);

        appIssue2.setId(appIssue1.getId());
        assertThat(appIssue1).isEqualTo(appIssue2);

        appIssue2 = getAppIssueSample2();
        assertThat(appIssue1).isNotEqualTo(appIssue2);
    }
}
