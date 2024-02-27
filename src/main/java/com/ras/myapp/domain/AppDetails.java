package com.ras.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppDetails.
 */
@Entity
@Table(name = "app_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AppDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "risk_rating")
    private String riskRating;

    @Column(name = "critical_issues")
    private Integer criticalIssues;

    @Column(name = "high_issues")
    private Integer highIssues;

    @Column(name = "medium_issues")
    private Integer mediumIssues;

    @Column(name = "low_issues")
    private Integer lowIssues;

    @Column(name = "informational_issues")
    private Integer informationalIssues;

    @Column(name = "issues_in_progress")
    private Integer issuesInProgress;

    @Column(name = "max_severity")
    private String maxSeverity;

    @Column(name = "correlation_state")
    private String correlationState;

    @Column(name = "r_r_max_severity")
    private Integer rRMaxSeverity;

    @Column(name = "asset_group_id")
    private String assetGroupId;

    @Column(name = "business_impact")
    private String businessImpact;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "business_unit")
    private String businessUnit;

    @Column(name = "business_unit_id")
    private String businessUnitId;

    @Column(name = "types")
    private String types;

    @Column(name = "technology")
    private String technology;

    @Column(name = "testing_status")
    private String testingStatus;

    @Column(name = "app_hosts")
    private String appHosts;

    @Column(name = "collateral_damage_potential")
    private String collateralDamagePotential;

    @Column(name = "target_distribution")
    private String targetDistribution;

    @Column(name = "confidentiality_requirement")
    private String confidentialityRequirement;

    @Column(name = "integrity_requirement")
    private String integrityRequirement;

    @Column(name = "availability_requirement")
    private String availabilityRequirement;

    @Column(name = "tester")
    private String tester;

    @Column(name = "business_owner")
    private String businessOwner;

    @Column(name = "development_contact")
    private String developmentContact;

    @Column(name = "preferred_offering_type")
    private String preferredOfferingType;

    @Column(name = "asset_group_name")
    private String assetGroupName;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    @Column(name = "last_comment")
    private String lastComment;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "new_issues")
    private Integer newIssues;

    @Column(name = "open_issues")
    private Integer openIssues;

    @Column(name = "total_issues")
    private Integer totalIssues;

    @Column(name = "overall_compliance")
    private Boolean overallCompliance;

    @Column(name = "can_be_deleted")
    private Boolean canBeDeleted;

    @Column(name = "locked_to_subscription")
    private Boolean lockedToSubscription;

    @Column(name = "total_scans")
    private Integer totalScans;

    @Column(name = "n_scan_executions")
    private Integer nScanExecutions;

    @Column(name = "has_exceeding_issues_number")
    private Boolean hasExceedingIssuesNumber;

    @Column(name = "has_exceeding_scans_number")
    private Boolean hasExceedingScansNumber;

    @Column(name = "auto_delete_exceeded_scans")
    private Boolean autoDeleteExceededScans;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return this.appId;
    }

    public AppDetails appId(String appId) {
        this.setAppId(appId);
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRiskRating() {
        return this.riskRating;
    }

    public AppDetails riskRating(String riskRating) {
        this.setRiskRating(riskRating);
        return this;
    }

    public void setRiskRating(String riskRating) {
        this.riskRating = riskRating;
    }

    public Integer getCriticalIssues() {
        return this.criticalIssues;
    }

    public AppDetails criticalIssues(Integer criticalIssues) {
        this.setCriticalIssues(criticalIssues);
        return this;
    }

    public void setCriticalIssues(Integer criticalIssues) {
        this.criticalIssues = criticalIssues;
    }

    public Integer getHighIssues() {
        return this.highIssues;
    }

    public AppDetails highIssues(Integer highIssues) {
        this.setHighIssues(highIssues);
        return this;
    }

    public void setHighIssues(Integer highIssues) {
        this.highIssues = highIssues;
    }

    public Integer getMediumIssues() {
        return this.mediumIssues;
    }

    public AppDetails mediumIssues(Integer mediumIssues) {
        this.setMediumIssues(mediumIssues);
        return this;
    }

    public void setMediumIssues(Integer mediumIssues) {
        this.mediumIssues = mediumIssues;
    }

    public Integer getLowIssues() {
        return this.lowIssues;
    }

    public AppDetails lowIssues(Integer lowIssues) {
        this.setLowIssues(lowIssues);
        return this;
    }

    public void setLowIssues(Integer lowIssues) {
        this.lowIssues = lowIssues;
    }

    public Integer getInformationalIssues() {
        return this.informationalIssues;
    }

    public AppDetails informationalIssues(Integer informationalIssues) {
        this.setInformationalIssues(informationalIssues);
        return this;
    }

    public void setInformationalIssues(Integer informationalIssues) {
        this.informationalIssues = informationalIssues;
    }

    public Integer getIssuesInProgress() {
        return this.issuesInProgress;
    }

    public AppDetails issuesInProgress(Integer issuesInProgress) {
        this.setIssuesInProgress(issuesInProgress);
        return this;
    }

    public void setIssuesInProgress(Integer issuesInProgress) {
        this.issuesInProgress = issuesInProgress;
    }

    public String getMaxSeverity() {
        return this.maxSeverity;
    }

    public AppDetails maxSeverity(String maxSeverity) {
        this.setMaxSeverity(maxSeverity);
        return this;
    }

    public void setMaxSeverity(String maxSeverity) {
        this.maxSeverity = maxSeverity;
    }

    public String getCorrelationState() {
        return this.correlationState;
    }

    public AppDetails correlationState(String correlationState) {
        this.setCorrelationState(correlationState);
        return this;
    }

    public void setCorrelationState(String correlationState) {
        this.correlationState = correlationState;
    }

    public Integer getrRMaxSeverity() {
        return this.rRMaxSeverity;
    }

    public AppDetails rRMaxSeverity(Integer rRMaxSeverity) {
        this.setrRMaxSeverity(rRMaxSeverity);
        return this;
    }

    public void setrRMaxSeverity(Integer rRMaxSeverity) {
        this.rRMaxSeverity = rRMaxSeverity;
    }

    public String getAssetGroupId() {
        return this.assetGroupId;
    }

    public AppDetails assetGroupId(String assetGroupId) {
        this.setAssetGroupId(assetGroupId);
        return this;
    }

    public void setAssetGroupId(String assetGroupId) {
        this.assetGroupId = assetGroupId;
    }

    public String getBusinessImpact() {
        return this.businessImpact;
    }

    public AppDetails businessImpact(String businessImpact) {
        this.setBusinessImpact(businessImpact);
        return this;
    }

    public void setBusinessImpact(String businessImpact) {
        this.businessImpact = businessImpact;
    }

    public String getUrl() {
        return this.url;
    }

    public AppDetails url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public AppDetails description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessUnit() {
        return this.businessUnit;
    }

    public AppDetails businessUnit(String businessUnit) {
        this.setBusinessUnit(businessUnit);
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getBusinessUnitId() {
        return this.businessUnitId;
    }

    public AppDetails businessUnitId(String businessUnitId) {
        this.setBusinessUnitId(businessUnitId);
        return this;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getTypes() {
        return this.types;
    }

    public AppDetails types(String types) {
        this.setTypes(types);
        return this;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTechnology() {
        return this.technology;
    }

    public AppDetails technology(String technology) {
        this.setTechnology(technology);
        return this;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTestingStatus() {
        return this.testingStatus;
    }

    public AppDetails testingStatus(String testingStatus) {
        this.setTestingStatus(testingStatus);
        return this;
    }

    public void setTestingStatus(String testingStatus) {
        this.testingStatus = testingStatus;
    }

    public String getAppHosts() {
        return this.appHosts;
    }

    public AppDetails appHosts(String appHosts) {
        this.setAppHosts(appHosts);
        return this;
    }

    public void setAppHosts(String appHosts) {
        this.appHosts = appHosts;
    }

    public String getCollateralDamagePotential() {
        return this.collateralDamagePotential;
    }

    public AppDetails collateralDamagePotential(String collateralDamagePotential) {
        this.setCollateralDamagePotential(collateralDamagePotential);
        return this;
    }

    public void setCollateralDamagePotential(String collateralDamagePotential) {
        this.collateralDamagePotential = collateralDamagePotential;
    }

    public String getTargetDistribution() {
        return this.targetDistribution;
    }

    public AppDetails targetDistribution(String targetDistribution) {
        this.setTargetDistribution(targetDistribution);
        return this;
    }

    public void setTargetDistribution(String targetDistribution) {
        this.targetDistribution = targetDistribution;
    }

    public String getConfidentialityRequirement() {
        return this.confidentialityRequirement;
    }

    public AppDetails confidentialityRequirement(String confidentialityRequirement) {
        this.setConfidentialityRequirement(confidentialityRequirement);
        return this;
    }

    public void setConfidentialityRequirement(String confidentialityRequirement) {
        this.confidentialityRequirement = confidentialityRequirement;
    }

    public String getIntegrityRequirement() {
        return this.integrityRequirement;
    }

    public AppDetails integrityRequirement(String integrityRequirement) {
        this.setIntegrityRequirement(integrityRequirement);
        return this;
    }

    public void setIntegrityRequirement(String integrityRequirement) {
        this.integrityRequirement = integrityRequirement;
    }

    public String getAvailabilityRequirement() {
        return this.availabilityRequirement;
    }

    public AppDetails availabilityRequirement(String availabilityRequirement) {
        this.setAvailabilityRequirement(availabilityRequirement);
        return this;
    }

    public void setAvailabilityRequirement(String availabilityRequirement) {
        this.availabilityRequirement = availabilityRequirement;
    }

    public String getTester() {
        return this.tester;
    }

    public AppDetails tester(String tester) {
        this.setTester(tester);
        return this;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getBusinessOwner() {
        return this.businessOwner;
    }

    public AppDetails businessOwner(String businessOwner) {
        this.setBusinessOwner(businessOwner);
        return this;
    }

    public void setBusinessOwner(String businessOwner) {
        this.businessOwner = businessOwner;
    }

    public String getDevelopmentContact() {
        return this.developmentContact;
    }

    public AppDetails developmentContact(String developmentContact) {
        this.setDevelopmentContact(developmentContact);
        return this;
    }

    public void setDevelopmentContact(String developmentContact) {
        this.developmentContact = developmentContact;
    }

    public String getPreferredOfferingType() {
        return this.preferredOfferingType;
    }

    public AppDetails preferredOfferingType(String preferredOfferingType) {
        this.setPreferredOfferingType(preferredOfferingType);
        return this;
    }

    public void setPreferredOfferingType(String preferredOfferingType) {
        this.preferredOfferingType = preferredOfferingType;
    }

    public String getAssetGroupName() {
        return this.assetGroupName;
    }

    public AppDetails assetGroupName(String assetGroupName) {
        this.setAssetGroupName(assetGroupName);
        return this;
    }

    public void setAssetGroupName(String assetGroupName) {
        this.assetGroupName = assetGroupName;
    }

    public ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public AppDetails dateCreated(ZonedDateTime dateCreated) {
        this.setDateCreated(dateCreated);
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public AppDetails lastUpdated(ZonedDateTime lastUpdated) {
        this.setLastUpdated(lastUpdated);
        return this;
    }

    public void setLastUpdated(ZonedDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastComment() {
        return this.lastComment;
    }

    public AppDetails lastComment(String lastComment) {
        this.setLastComment(lastComment);
        return this;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AppDetails createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getNewIssues() {
        return this.newIssues;
    }

    public AppDetails newIssues(Integer newIssues) {
        this.setNewIssues(newIssues);
        return this;
    }

    public void setNewIssues(Integer newIssues) {
        this.newIssues = newIssues;
    }

    public Integer getOpenIssues() {
        return this.openIssues;
    }

    public AppDetails openIssues(Integer openIssues) {
        this.setOpenIssues(openIssues);
        return this;
    }

    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    public Integer getTotalIssues() {
        return this.totalIssues;
    }

    public AppDetails totalIssues(Integer totalIssues) {
        this.setTotalIssues(totalIssues);
        return this;
    }

    public void setTotalIssues(Integer totalIssues) {
        this.totalIssues = totalIssues;
    }

    public Boolean getOverallCompliance() {
        return this.overallCompliance;
    }

    public AppDetails overallCompliance(Boolean overallCompliance) {
        this.setOverallCompliance(overallCompliance);
        return this;
    }

    public void setOverallCompliance(Boolean overallCompliance) {
        this.overallCompliance = overallCompliance;
    }

    public Boolean getCanBeDeleted() {
        return this.canBeDeleted;
    }

    public AppDetails canBeDeleted(Boolean canBeDeleted) {
        this.setCanBeDeleted(canBeDeleted);
        return this;
    }

    public void setCanBeDeleted(Boolean canBeDeleted) {
        this.canBeDeleted = canBeDeleted;
    }

    public Boolean getLockedToSubscription() {
        return this.lockedToSubscription;
    }

    public AppDetails lockedToSubscription(Boolean lockedToSubscription) {
        this.setLockedToSubscription(lockedToSubscription);
        return this;
    }

    public void setLockedToSubscription(Boolean lockedToSubscription) {
        this.lockedToSubscription = lockedToSubscription;
    }

    public Integer getTotalScans() {
        return this.totalScans;
    }

    public AppDetails totalScans(Integer totalScans) {
        this.setTotalScans(totalScans);
        return this;
    }

    public void setTotalScans(Integer totalScans) {
        this.totalScans = totalScans;
    }

    public Integer getnScanExecutions() {
        return this.nScanExecutions;
    }

    public AppDetails nScanExecutions(Integer nScanExecutions) {
        this.setnScanExecutions(nScanExecutions);
        return this;
    }

    public void setnScanExecutions(Integer nScanExecutions) {
        this.nScanExecutions = nScanExecutions;
    }

    public Boolean getHasExceedingIssuesNumber() {
        return this.hasExceedingIssuesNumber;
    }

    public AppDetails hasExceedingIssuesNumber(Boolean hasExceedingIssuesNumber) {
        this.setHasExceedingIssuesNumber(hasExceedingIssuesNumber);
        return this;
    }

    public void setHasExceedingIssuesNumber(Boolean hasExceedingIssuesNumber) {
        this.hasExceedingIssuesNumber = hasExceedingIssuesNumber;
    }

    public Boolean getHasExceedingScansNumber() {
        return this.hasExceedingScansNumber;
    }

    public AppDetails hasExceedingScansNumber(Boolean hasExceedingScansNumber) {
        this.setHasExceedingScansNumber(hasExceedingScansNumber);
        return this;
    }

    public void setHasExceedingScansNumber(Boolean hasExceedingScansNumber) {
        this.hasExceedingScansNumber = hasExceedingScansNumber;
    }

    public Boolean getAutoDeleteExceededScans() {
        return this.autoDeleteExceededScans;
    }

    public AppDetails autoDeleteExceededScans(Boolean autoDeleteExceededScans) {
        this.setAutoDeleteExceededScans(autoDeleteExceededScans);
        return this;
    }

    public void setAutoDeleteExceededScans(Boolean autoDeleteExceededScans) {
        this.autoDeleteExceededScans = autoDeleteExceededScans;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((AppDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppDetails{" +
            "id=" + getId() +
            ", appId='" + getAppId() + "'" +
            ", riskRating='" + getRiskRating() + "'" +
            ", criticalIssues=" + getCriticalIssues() +
            ", highIssues=" + getHighIssues() +
            ", mediumIssues=" + getMediumIssues() +
            ", lowIssues=" + getLowIssues() +
            ", informationalIssues=" + getInformationalIssues() +
            ", issuesInProgress=" + getIssuesInProgress() +
            ", maxSeverity='" + getMaxSeverity() + "'" +
            ", correlationState='" + getCorrelationState() + "'" +
            ", rRMaxSeverity=" + getrRMaxSeverity() +
            ", assetGroupId='" + getAssetGroupId() + "'" +
            ", businessImpact='" + getBusinessImpact() + "'" +
            ", url='" + getUrl() + "'" +
            ", description='" + getDescription() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", businessUnitId='" + getBusinessUnitId() + "'" +
            ", types='" + getTypes() + "'" +
            ", technology='" + getTechnology() + "'" +
            ", testingStatus='" + getTestingStatus() + "'" +
            ", appHosts='" + getAppHosts() + "'" +
            ", collateralDamagePotential='" + getCollateralDamagePotential() + "'" +
            ", targetDistribution='" + getTargetDistribution() + "'" +
            ", confidentialityRequirement='" + getConfidentialityRequirement() + "'" +
            ", integrityRequirement='" + getIntegrityRequirement() + "'" +
            ", availabilityRequirement='" + getAvailabilityRequirement() + "'" +
            ", tester='" + getTester() + "'" +
            ", businessOwner='" + getBusinessOwner() + "'" +
            ", developmentContact='" + getDevelopmentContact() + "'" +
            ", preferredOfferingType='" + getPreferredOfferingType() + "'" +
            ", assetGroupName='" + getAssetGroupName() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", lastComment='" + getLastComment() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", newIssues=" + getNewIssues() +
            ", openIssues=" + getOpenIssues() +
            ", totalIssues=" + getTotalIssues() +
            ", overallCompliance='" + getOverallCompliance() + "'" +
            ", canBeDeleted='" + getCanBeDeleted() + "'" +
            ", lockedToSubscription='" + getLockedToSubscription() + "'" +
            ", totalScans=" + getTotalScans() +
            ", nScanExecutions=" + getnScanExecutions() +
            ", hasExceedingIssuesNumber='" + getHasExceedingIssuesNumber() + "'" +
            ", hasExceedingScansNumber='" + getHasExceedingScansNumber() + "'" +
            ", autoDeleteExceededScans='" + getAutoDeleteExceededScans() + "'" +
            "}";
    }
}
