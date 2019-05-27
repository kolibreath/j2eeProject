package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff,Long> {
}
