package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttentionRecordRepository extends JpaRepository<AttentionRecord, UUID> {

}
