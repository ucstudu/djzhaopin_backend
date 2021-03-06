package com.ucstu.guangbt.djzhaopin.entity.user;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)

public class UserInformation {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID userInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date updatedAt;

    private String avatarUrl;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String sex;

    private String cityName;

    @Email
    private String email;

    @Range(min = 1, max = 6)
    private Integer workingYears;

    @Type(type = "text")
    private String personalAdvantage;

    private String socialHomepage;

    // {1:????????????,2:2????????????,3:1????????????}
    @Range(min = 1, max = 3)
    private Integer jobStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> pictureWorks;

    // {1:??????,2:??????}
    @NotNull
    @Range(min = 1, max = 2)
    private Integer privacySettings;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<JobExpectation> jobExpectations;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<EducationExperience> educationExperiences;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<WorkExperience> workExperiences;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<ProjectExperience> projectExperiences;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<DeliveryRecord> deliveryRecords;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<AttentionRecord> attentionRecords;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<UserInspectionRecord> userInspectionRecords;

    @JsonIgnore
    @JoinColumn(name = "user_information_id")
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<GarnerRecord> garnerRecords;

    @JsonGetter("age")
    public Integer getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return (int) ((new Date().getTime() - dateOfBirth.getTime()) / (1000 * 60 *
                60 * 24) / 365);
    }

    @JsonGetter("education")
    public Integer getEducation() {
        Integer maxEducation = 0;
        if (educationExperiences != null) {
            for (EducationExperience educationExperience : educationExperiences) {
                if (educationExperience.getEducation() > maxEducation) {
                    maxEducation = educationExperience.getEducation();
                }
            }
        }
        return maxEducation;
    }

}
