package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.Date;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInspectionRecordRepository extends JpaRepository<UserInspectionRecord, UUID>,
                JpaSpecificationExecutor<UserInspectionRecord> {

        Page<UserInspectionRecord> findByUserInformation(UserInformation userInformation, Pageable pageable);

        Page<UserInspectionRecord> findByCompanyInformationAndCreatedAtBetween(CompanyInformation companyInformation,
                        Date startDate, Date endDate, Pageable pageable);

        Integer countByCompanyInformationAndCreatedAtBetween(
                        CompanyInformation companyInformation,
                        Date createdAt,
                        Date nextDate);
}
