package com.ucstu.guangbt.djzhaopin.entity.account;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AccountInformation {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID accountInformationId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonProperty(value = "userInformationId", access = JsonProperty.Access.READ_ONLY)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = PropertyGenerator.class, property = "userInformationId")
    private UserInformation userInformation;

    @JsonProperty(value = "hrInformationId", access = JsonProperty.Access.READ_ONLY)
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = PropertyGenerator.class, property = "hrInformationId")
    private HrInformation hrInformation;

    // {1:用户,2:HR}
    private Integer accountType;

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Set<AccountAuthority> authorities;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Set<AccountGroup> groups;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Boolean expired = false;

    @JsonIgnore
    private Boolean locked = false;

    @JsonIgnore
    private Boolean enabled = true;
}
