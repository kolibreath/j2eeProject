package com.kolibreath.j2eefinal.repo;

import com.kolibreath.j2eefinal.entity.PunchCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PunchCardRepo extends JpaRepository<PunchCard,Long> {

    //根据今天的日期和员工的ID进行查找记录
    PunchCard findByDateAndStaffId(String date, int staffId);

    //查找员工这几天的记录
    List<PunchCard> findByStaffId(int staffId);

}
