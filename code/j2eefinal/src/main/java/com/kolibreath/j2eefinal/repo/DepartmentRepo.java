package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {

}
