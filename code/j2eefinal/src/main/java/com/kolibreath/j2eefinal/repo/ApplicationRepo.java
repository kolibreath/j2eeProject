package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.Appli;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Appli,Long> {

    List<Appli> findByHandled(boolean handled);
}
