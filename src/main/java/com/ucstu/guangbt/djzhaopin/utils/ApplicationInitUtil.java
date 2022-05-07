package com.ucstu.guangbt.djzhaopin.utils;

import java.util.HashSet;
import java.util.Set;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.repository.account.AccountAuthorityRepository;
import com.ucstu.guangbt.djzhaopin.repository.account.AccountGroupRepository;
import com.ucstu.guangbt.djzhaopin.repository.account.AccountInformationRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class ApplicationInitUtil {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AccountAuthorityRepository accountAuthorityRepository;

    @Resource
    private AccountGroupRepository accountGroupRepository;

    @Resource
    private AccountInformationRepository accountInformationRepository;

    public void init() {
        if (accountGroupRepository.count() == 0) {
            AccountGroup accountGroup1 = new AccountGroup();
            accountGroup1.setGroupName("ADMIN");
            accountGroup1.setAuthorities(new HashSet<>());
            AccountGroup accountGroup2 = new AccountGroup();
            accountGroup2.setGroupName("USER");
            accountGroup2.setAuthorities(new HashSet<>());
            AccountGroup accountGroup3 = new AccountGroup();
            accountGroup3.setGroupName("HR");
            accountGroup3.setAuthorities(new HashSet<>());
            AccountAuthority accountAuthority1 = new AccountAuthority();
            accountAuthority1.setAuthorityName("ACCOUNT_MANAGE");
            accountAuthority1 = accountAuthorityRepository.save(accountAuthority1);
            accountGroup1.getAuthorities().add(accountAuthority1);
            AccountAuthority accountAuthority2 = new AccountAuthority();
            accountAuthority2.setAuthorityName("ACCOUNT_GROUP_MANAGE");
            accountAuthority2 = accountAuthorityRepository.save(accountAuthority2);
            accountGroup1.getAuthorities().add(accountAuthority2);
            AccountAuthority accountAuthority3 = new AccountAuthority();
            accountAuthority3.setAuthorityName("ACCOUNT_AUTHORITY_MANAGE");
            accountAuthority3 = accountAuthorityRepository.save(accountAuthority3);
            accountGroup1.getAuthorities().add(accountAuthority3);
            AccountAuthority accountAuthority4 = new AccountAuthority();
            accountAuthority4.setAuthorityName("COMPANY_INFO_MANAGE");
            accountAuthority4 = accountAuthorityRepository.save(accountAuthority4);
            accountGroup1.getAuthorities().add(accountAuthority4);
            accountGroup3.getAuthorities().add(accountAuthority4);
            AccountAuthority accountAuthority5 = new AccountAuthority();
            accountAuthority5.setAuthorityName("POSITION_INFO_MANAGE");
            accountAuthority5 = accountAuthorityRepository.save(accountAuthority5);
            accountGroup1.getAuthorities().add(accountAuthority5);
            accountGroup3.getAuthorities().add(accountAuthority5);
            AccountAuthority accountAuthority6 = new AccountAuthority();
            accountAuthority6.setAuthorityName("DELIVERY_RECORD_MANAGE");
            accountAuthority6 = accountAuthorityRepository.save(accountAuthority6);
            accountGroup1.getAuthorities().add(accountAuthority6);
            accountGroup2.getAuthorities().add(accountAuthority6);
            accountGroup3.getAuthorities().add(accountAuthority6);
            AccountAuthority accountAuthority7 = new AccountAuthority();
            accountAuthority7.setAuthorityName("USER_INFO_MANAGE");
            accountAuthority7 = accountAuthorityRepository.save(accountAuthority7);
            accountGroup1.getAuthorities().add(accountAuthority7);
            accountGroup2.getAuthorities().add(accountAuthority7);
            AccountAuthority accountAuthority8 = new AccountAuthority();
            accountAuthority8.setAuthorityName("HR_INFO_MANAGE");
            accountAuthority8 = accountAuthorityRepository.save(accountAuthority8);
            accountGroup1.getAuthorities().add(accountAuthority8);
            accountGroup3.getAuthorities().add(accountAuthority8);
            accountGroup1 = accountGroupRepository.save(accountGroup1);
            accountGroup2 = accountGroupRepository.save(accountGroup2);
            accountGroup3 = accountGroupRepository.save(accountGroup3);
            AccountInformation accountInformation1 = new AccountInformation();
            accountInformation1.setUserName("admin");
            accountInformation1.setPassword(passwordEncoder.encode("admin"));
            accountInformation1.setAccountType(0);
            Set<AccountGroup> accountGroups1 = new HashSet<>();
            accountGroups1.add(accountGroup1);
            accountInformation1.setGroups(accountGroups1);
        }
    }

}
