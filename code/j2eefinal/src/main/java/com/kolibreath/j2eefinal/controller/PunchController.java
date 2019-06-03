package com.kolibreath.j2eefinal.controller;

import com.kolibreath.j2eefinal.CalUtils;
import com.kolibreath.j2eefinal.Common;
import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.entity.Staff;
import com.kolibreath.j2eefinal.entity.User;
import com.kolibreath.j2eefinal.repo.DepartmentRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffInfoRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
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
    @RequestMapping("/show_record")
    public ModelAndView showRecords(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Common.USER_INFO);
        int staffId =user.getUserId();
        List<PunchCard> punchCards = punchCardRepo.findByStaffId(staffId);
//        punchCards = punchCards.subList(punchCards.size() - 3,punchCards.size());

        List<Record> records = new LinkedList<>();

        Staff staff = staffRepo.findByStaffId(staffId);
        String staffName = staff.getName();

        for(int i = 0;i< punchCards.size();i++){
            records.add(new Record(staffName,punchCards.get(i).getSignInStamp(),punchCards.get(i).getSignOutStamp()
            ,punchCards.get(i).getStatus(),punchCards.get(i).getPunchCardId(),staffId));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/show_record");
        modelAndView.addObject("records",records);
        return modelAndView;
    }

    @RequestMapping("/punch_card_signin")
    public String punchCardSignIn(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Common.USER_INFO);
        PunchCard punchCard = new PunchCard();
        punchCard.setStaffId(user.getUserId());
        punchCard.setSignInStamp(System.currentTimeMillis());
        punchCard.setDate(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        punchCard.setSignOutStamp(0);
        punchCard.setStatus(0);

        punchCardRepo.save(punchCard);
        return "success";
    }

    @RequestMapping("/punch_card_signout")
    public String punchCardSignOut(HttpSession session){
        User user = (User) session.getAttribute(Common.USER_INFO);
        PunchCard punchCard = punchCardRepo.findByDateAndStaffId(new SimpleDateFormat("yyyy-MM-dd")
                .format(System.currentTimeMillis()),user.getUserId());
        if (punchCard == null)
            return "error";
        punchCard.setSignOutStamp(System.currentTimeMillis());
        punchCardRepo.save(punchCard);
        return "success";
    }
    class Record{
        private String staffName;
        private String signInTime;
        private String signOutTime;
        private String status;
        private int applicantId;
        private int recordId;


        public Record(String staffName,long signInTime,long signOutTime,
                      int status,int recordId,int applicantId){
            this.staffName = staffName;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            this.signInTime = format.format(signInTime);
            this.signOutTime = format.format(signOutTime);
            this.status = CalUtils.getStatus(status);
            this.recordId = recordId;
            this.applicantId = applicantId;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getSignInTime() {
            return signInTime;
        }

        public void setSignInTime(String signInTime) {
            this.signInTime = signInTime;
        }

        public String getSignOutTime() {
            return signOutTime;
        }

        public void setSignOutTime(String signOutTime) {
            this.signOutTime = signOutTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
