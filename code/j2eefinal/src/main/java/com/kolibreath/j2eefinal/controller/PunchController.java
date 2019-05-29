package com.kolibreath.j2eefinal.controller;

import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.repo.DepartmentRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffInfoRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PunchController {
    //AutoWired
    @Autowired
    StaffInfoRepo staffInfoRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    StaffRepo staffRepo;
    @Autowired
    PunchCardRepo punchCardRepo;



    //todo 注意处理传来的数据
    @RequestMapping("show_record")
    public ModelAndView showRecords(){
        int staffId = 14;
        List<PunchCard> punchCards = punchCardRepo.findByStaffId(staffId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/show_record");
        modelAndView.addObject("punchCardRecords",punchCards);
        return modelAndView;
    }
}
