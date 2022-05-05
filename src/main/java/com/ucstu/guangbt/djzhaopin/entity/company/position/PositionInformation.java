package com.ucstu.guangbt.djzhaopin.entity.company.position;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucstu.guangbt.djzhaopin.entity.Place;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
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
public class PositionInformation {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID positionInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @NotBlank
    private String positionName;

    @NotNull
    @Range(min = 1, max = 6)
    private Integer workingYears;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer education;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> directionTags;

    @NotNull
    private Integer startingSalary;

    @NotNull
    private Integer ceilingSalary;

    private String workCityName;

    private String workAreaName;

    @NotNull
    @Type(type = "uuid-char")
    private UUID companyInformationId;

    @NotNull
    @Type(type = "uuid-char")
    private UUID hrInformationId;

    @NotNull
    @Range(min = 1, max = 3)
    private Integer positionType;

    private String departmentName;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> highlights;

    @NotBlank
    private String description;

    @Range(min = 1, max = 3)
    private Integer weekendReleaseTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date workTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private Date overTime;

    @NotNull
    @JsonProperty("interviewInfo")
    @OneToOne(cascade = { CascadeType.ALL })
    private PositionInterviewInfo positionInterviewInfo;

    @NotNull
    @JsonProperty("workingPlace")
    @OneToOne(cascade = { CascadeType.ALL })
    private Place place;

}
