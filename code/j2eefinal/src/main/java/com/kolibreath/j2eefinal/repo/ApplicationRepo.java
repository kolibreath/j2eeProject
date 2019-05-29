package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepo extends JpaRepository<Application,Long> {
}
