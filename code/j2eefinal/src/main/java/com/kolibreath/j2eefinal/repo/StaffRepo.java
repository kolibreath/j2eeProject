package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff,Long> {

    Staff findByStaffId(int staffId);
}
