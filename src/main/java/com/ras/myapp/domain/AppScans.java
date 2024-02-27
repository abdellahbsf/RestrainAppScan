package com.ras.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppScans.
 */
@Entity
@Table(name = "app_scans")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AppScans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "name_scan")
    private String nameScan;

    @Column(name = "technology")
    private String technology;

    @Column(name = "iast_agent_type")
    private String iastAgentType;

    @Column(name = "iast_agent_status")
    private String iastAgentStatus;

    @Column(name = "url_scan")
    private String urlScan;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "test_optimization_level")
    private String testOptimizationLevel;

    @Column(name = "number_of_executions")
    private Integer numberOfExecutions;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppScans id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return this.appId;
    }

    public AppScans appId(String appId) {
        this.setAppId(appId);
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNameScan() {
        return this.nameScan;
    }

    public AppScans nameScan(String nameScan) {
        this.setNameScan(nameScan);
        return this;
    }

    public void setNameScan(String nameScan) {
        this.nameScan = nameScan;
    }

    public String getTechnology() {
        return this.technology;
    }

    public AppScans technology(String technology) {
        this.setTechnology(technology);
        return this;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getIastAgentType() {
        return this.iastAgentType;
    }

    public AppScans iastAgentType(String iastAgentType) {
        this.setIastAgentType(iastAgentType);
        return this;
    }

    public void setIastAgentType(String iastAgentType) {
        this.iastAgentType = iastAgentType;
    }

    public String getIastAgentStatus() {
        return this.iastAgentStatus;
    }

    public AppScans iastAgentStatus(String iastAgentStatus) {
        this.setIastAgentStatus(iastAgentStatus);
        return this;
    }

    public void setIastAgentStatus(String iastAgentStatus) {
        this.iastAgentStatus = iastAgentStatus;
    }

    public String getUrlScan() {
        return this.urlScan;
    }

    public AppScans urlScan(String urlScan) {
        this.setUrlScan(urlScan);
        return this;
    }

    public void setUrlScan(String urlScan) {
        this.urlScan = urlScan;
    }

    public String getAppName() {
        return this.appName;
    }

    public AppScans appName(String appName) {
        this.setAppName(appName);
        return this;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTestOptimizationLevel() {
        return this.testOptimizationLevel;
    }

    public AppScans testOptimizationLevel(String testOptimizationLevel) {
        this.setTestOptimizationLevel(testOptimizationLevel);
        return this;
    }

    public void setTestOptimizationLevel(String testOptimizationLevel) {
        this.testOptimizationLevel = testOptimizationLevel;
    }

    public Integer getNumberOfExecutions() {
        return this.numberOfExecutions;
    }

    public AppScans numberOfExecutions(Integer numberOfExecutions) {
        this.setNumberOfExecutions(numberOfExecutions);
        return this;
    }

    public void setNumberOfExecutions(Integer numberOfExecutions) {
        this.numberOfExecutions = numberOfExecutions;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public AppScans createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getLastModified() {
        return this.lastModified;
    }

    public AppScans lastModified(ZonedDateTime lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppScans)) {
            return false;
        }
        return getId() != null && getId().equals(((AppScans) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppScans{" +
            "id=" + getId() +
            ", appId='" + getAppId() + "'" +
            ", nameScan='" + getNameScan() + "'" +
            ", technology='" + getTechnology() + "'" +
            ", iastAgentType='" + getIastAgentType() + "'" +
            ", iastAgentStatus='" + getIastAgentStatus() + "'" +
            ", urlScan='" + getUrlScan() + "'" +
            ", appName='" + getAppName() + "'" +
            ", testOptimizationLevel='" + getTestOptimizationLevel() + "'" +
            ", numberOfExecutions=" + getNumberOfExecutions() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
