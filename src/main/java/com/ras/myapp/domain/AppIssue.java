package com.ras.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppIssue.
 */
@Entity
@Table(name = "app_issue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AppIssue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "severities")
    private String severities;

    @Column(name = "status_issue")
    private String statusIssue;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "location_issue")
    private String locationIssue;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    @Column(name = "last_found")
    private ZonedDateTime lastFound;

    @Column(name = "calling_method")
    private String callingMethod;

    @Column(name = "is_new_in_scope")
    private Boolean isNewInScope;

    @Column(name = "library_name")
    private String libraryName;

    @Column(name = "library_version")
    private String libraryVersion;

    @Column(name = "sca_technology")
    private String scaTechnology;

    @Column(name = "f_g_status")
    private String fGStatus;

    @Column(name = "application_id")
    private String applicationId;

    @Column(name = "fix_group_id")
    private String fixGroupId;

    @Column(name = "api_issue")
    private String apiIssue;

    @Column(name = "source_issue")
    private String sourceIssue;

    @Column(name = "context_issue")
    private String contextIssue;

    @Column(name = "appscan_vuln_id")
    private String appscanVulnId;

    @Column(name = "calling_line")
    private String callingLine;

    @Column(name = "class_issue")
    private String classIssue;

    @Column(name = "cve_issue")
    private String cveIssue;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppIssue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return this.appId;
    }

    public AppIssue appId(String appId) {
        this.setAppId(appId);
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProgrammingLanguage() {
        return this.programmingLanguage;
    }

    public AppIssue programmingLanguage(String programmingLanguage) {
        this.setProgrammingLanguage(programmingLanguage);
        return this;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getSeverities() {
        return this.severities;
    }

    public AppIssue severities(String severities) {
        this.setSeverities(severities);
        return this;
    }

    public void setSeverities(String severities) {
        this.severities = severities;
    }

    public String getStatusIssue() {
        return this.statusIssue;
    }

    public AppIssue statusIssue(String statusIssue) {
        this.setStatusIssue(statusIssue);
        return this;
    }

    public void setStatusIssue(String statusIssue) {
        this.statusIssue = statusIssue;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public AppIssue issueType(String issueType) {
        this.setIssueType(issueType);
        return this;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getLocationIssue() {
        return this.locationIssue;
    }

    public AppIssue locationIssue(String locationIssue) {
        this.setLocationIssue(locationIssue);
        return this;
    }

    public void setLocationIssue(String locationIssue) {
        this.locationIssue = locationIssue;
    }

    public ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public AppIssue dateCreated(ZonedDateTime dateCreated) {
        this.setDateCreated(dateCreated);
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public AppIssue lastUpdated(ZonedDateTime lastUpdated) {
        this.setLastUpdated(lastUpdated);
        return this;
    }

    public void setLastUpdated(ZonedDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ZonedDateTime getLastFound() {
        return this.lastFound;
    }

    public AppIssue lastFound(ZonedDateTime lastFound) {
        this.setLastFound(lastFound);
        return this;
    }

    public void setLastFound(ZonedDateTime lastFound) {
        this.lastFound = lastFound;
    }

    public String getCallingMethod() {
        return this.callingMethod;
    }

    public AppIssue callingMethod(String callingMethod) {
        this.setCallingMethod(callingMethod);
        return this;
    }

    public void setCallingMethod(String callingMethod) {
        this.callingMethod = callingMethod;
    }

    public Boolean getIsNewInScope() {
        return this.isNewInScope;
    }

    public AppIssue isNewInScope(Boolean isNewInScope) {
        this.setIsNewInScope(isNewInScope);
        return this;
    }

    public void setIsNewInScope(Boolean isNewInScope) {
        this.isNewInScope = isNewInScope;
    }

    public String getLibraryName() {
        return this.libraryName;
    }

    public AppIssue libraryName(String libraryName) {
        this.setLibraryName(libraryName);
        return this;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryVersion() {
        return this.libraryVersion;
    }

    public AppIssue libraryVersion(String libraryVersion) {
        this.setLibraryVersion(libraryVersion);
        return this;
    }

    public void setLibraryVersion(String libraryVersion) {
        this.libraryVersion = libraryVersion;
    }

    public String getScaTechnology() {
        return this.scaTechnology;
    }

    public AppIssue scaTechnology(String scaTechnology) {
        this.setScaTechnology(scaTechnology);
        return this;
    }

    public void setScaTechnology(String scaTechnology) {
        this.scaTechnology = scaTechnology;
    }

    public String getfGStatus() {
        return this.fGStatus;
    }

    public AppIssue fGStatus(String fGStatus) {
        this.setfGStatus(fGStatus);
        return this;
    }

    public void setfGStatus(String fGStatus) {
        this.fGStatus = fGStatus;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public AppIssue applicationId(String applicationId) {
        this.setApplicationId(applicationId);
        return this;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getFixGroupId() {
        return this.fixGroupId;
    }

    public AppIssue fixGroupId(String fixGroupId) {
        this.setFixGroupId(fixGroupId);
        return this;
    }

    public void setFixGroupId(String fixGroupId) {
        this.fixGroupId = fixGroupId;
    }

    public String getApiIssue() {
        return this.apiIssue;
    }

    public AppIssue apiIssue(String apiIssue) {
        this.setApiIssue(apiIssue);
        return this;
    }

    public void setApiIssue(String apiIssue) {
        this.apiIssue = apiIssue;
    }

    public String getSourceIssue() {
        return this.sourceIssue;
    }

    public AppIssue sourceIssue(String sourceIssue) {
        this.setSourceIssue(sourceIssue);
        return this;
    }

    public void setSourceIssue(String sourceIssue) {
        this.sourceIssue = sourceIssue;
    }

    public String getContextIssue() {
        return this.contextIssue;
    }

    public AppIssue contextIssue(String contextIssue) {
        this.setContextIssue(contextIssue);
        return this;
    }

    public void setContextIssue(String contextIssue) {
        this.contextIssue = contextIssue;
    }

    public String getAppscanVulnId() {
        return this.appscanVulnId;
    }

    public AppIssue appscanVulnId(String appscanVulnId) {
        this.setAppscanVulnId(appscanVulnId);
        return this;
    }

    public void setAppscanVulnId(String appscanVulnId) {
        this.appscanVulnId = appscanVulnId;
    }

    public String getCallingLine() {
        return this.callingLine;
    }

    public AppIssue callingLine(String callingLine) {
        this.setCallingLine(callingLine);
        return this;
    }

    public void setCallingLine(String callingLine) {
        this.callingLine = callingLine;
    }

    public String getClassIssue() {
        return this.classIssue;
    }

    public AppIssue classIssue(String classIssue) {
        this.setClassIssue(classIssue);
        return this;
    }

    public void setClassIssue(String classIssue) {
        this.classIssue = classIssue;
    }

    public String getCveIssue() {
        return this.cveIssue;
    }

    public AppIssue cveIssue(String cveIssue) {
        this.setCveIssue(cveIssue);
        return this;
    }

    public void setCveIssue(String cveIssue) {
        this.cveIssue = cveIssue;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppIssue)) {
            return false;
        }
        return getId() != null && getId().equals(((AppIssue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppIssue{" +
            "id=" + getId() +
            ", appId='" + getAppId() + "'" +
            ", programmingLanguage='" + getProgrammingLanguage() + "'" +
            ", severities='" + getSeverities() + "'" +
            ", statusIssue='" + getStatusIssue() + "'" +
            ", issueType='" + getIssueType() + "'" +
            ", locationIssue='" + getLocationIssue() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", lastFound='" + getLastFound() + "'" +
            ", callingMethod='" + getCallingMethod() + "'" +
            ", isNewInScope='" + getIsNewInScope() + "'" +
            ", libraryName='" + getLibraryName() + "'" +
            ", libraryVersion='" + getLibraryVersion() + "'" +
            ", scaTechnology='" + getScaTechnology() + "'" +
            ", fGStatus='" + getfGStatus() + "'" +
            ", applicationId='" + getApplicationId() + "'" +
            ", fixGroupId='" + getFixGroupId() + "'" +
            ", apiIssue='" + getApiIssue() + "'" +
            ", sourceIssue='" + getSourceIssue() + "'" +
            ", contextIssue='" + getContextIssue() + "'" +
            ", appscanVulnId='" + getAppscanVulnId() + "'" +
            ", callingLine='" + getCallingLine() + "'" +
            ", classIssue='" + getClassIssue() + "'" +
            ", cveIssue='" + getCveIssue() + "'" +
            "}";
    }
}
