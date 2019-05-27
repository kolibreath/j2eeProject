package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {

    List<Department> findByDepartmentName(String name);
}
