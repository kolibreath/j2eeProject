package com.kolibreath.j2eefinal.controller;


import com.kolibreath.j2eefinal.CalUtils;
import com.kolibreath.j2eefinal.Common;
import com.kolibreath.j2eefinal.entity.Appli;
import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.repo.ApplicationRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffInfoRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private StaffInfoRepo staffInfoRepo;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private PunchCardRepo punchCardRepo;


    @RequestMapping("/application")
    public String handInApplication(HttpServletRequest request){
        int punchCardRecordId = Integer.parseInt(request.getParameter("recordId"));
        String reason = request.getParameter("reason");

        Appli application = new Appli();

        application.setHandled(false);
        application.setHandlerId(0);
        application.setApplicantId(Common.currentStaffId);
        application.setRecordId(punchCardRecordId);
        application.setReason(reason);

        applicationRepo.save(application);

        return "success";
    }

    @RequestMapping("/show_application")
    public ModelAndView showApplication(){
        List<Appli> applicationList;
        applicationList  = applicationRepo.findByHandled(false);

        if(Common.currentStaffType == Common.EMPLOYEE) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/handle_appli");
        modelAndView.addObject("applis",applicationList);

        return modelAndView;
    }

    @RequestMapping("/handle_appli")
    public String handleApplication(HttpServletRequest request){
        //员工需要改成的状态
        String intendedStatus  =  request.getParameter("intended_status");
        int status = CalUtils.getStatus(intendedStatus);

        int punchCardRecordId = Integer.parseInt(request.getParameter("punch_card_record_id"));
        PunchCard punchCard = punchCardRepo.findById((int) punchCardRecordId).get();

        punchCard.setStatus(status);
        punchCardRepo.save(punchCard);

        int applicationId = Integer.parseInt(request.getParameter("application_id"));
        Appli appli = applicationRepo.findById((long) applicationId).get();
        appli.setHandled(true);
        applicationRepo.save(appli);

        return "success";
    }

    //返回给html 需要展示的 数据结构
    public class ApplicationName{

        private String staffName;
        private int applicantId;
        private String reason;
        private int applicationId;
        private int recordId;


        public ApplicationName(int applicantId,String reason, int applicationId,int recordId){
            this.reason= reason;
            this.staffName = staffInfoRepo.findById((long) applicantId).get().getUsername();
            this.applicationId = applicationId;
            this.recordId = recordId;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public int getApplicantId() {
            return applicantId;
        }

        public void setApplicantId(int applicantId) {
            this.applicantId = applicantId;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(int applicationId) {
            this.applicationId = applicationId;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }
    }

}
