package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.StaffInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffInfoRepo extends JpaRepository<StaffInfo,Long> {


    List<StaffInfo> findByUsernameAndPassword(String username, String password);
}
