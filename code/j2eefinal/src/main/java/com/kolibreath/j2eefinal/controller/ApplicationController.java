package com.kolibreath.j2eefinal.controller;


import com.kolibreath.j2eefinal.Common;
import com.kolibreath.j2eefinal.entity.Application;
import com.kolibreath.j2eefinal.repo.ApplicationRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationController {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private ApplicationRepo applicationRepo;


    @RequestMapping("/application")
    public String handleApplication(HttpServletRequest request){
        int punchCardRecordId = Integer.parseInt(request.getParameter("recordId"));
        String reason = request.getParameter("reason");

        Application application = new Application();

        application.setHandlerId(0);
        application.setApplicantId(Common.currentStaffId);
        application.setRecordId(punchCardRecordId);
        application.setReason(reason);

        applicationRepo.save(application);

        return "success";
    }

}
