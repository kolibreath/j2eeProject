package com.kolibreath.j2eefinal.controller;


import com.kolibreath.j2eefinal.CalUtils;
import com.kolibreath.j2eefinal.Common;
import com.kolibreath.j2eefinal.entity.Department;
import com.kolibreath.j2eefinal.entity.Staff;
import com.kolibreath.j2eefinal.entity.StaffInfo;
import com.kolibreath.j2eefinal.entity.User;
import com.kolibreath.j2eefinal.repo.DepartmentRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffInfoRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthController {

    //AutoWired
    @Autowired
    StaffInfoRepo staffInfoRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    StaffRepo staffRepo;
    @Autowired
    PunchCardRepo punchCardRepo;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        List<Department> departments = departmentRepo.findAll();
        ModelAndView  model = new ModelAndView();
        model.setViewName("/register");
        model.addObject("departments",departments);
        return model;
    }

    @RequestMapping("/dologin")
    public String login(HttpServletRequest request, HttpSession httpSession){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        int curStaff= staffInfoRepo.findByUsernameAndPassword(username,password).get(0).getId();
        int staffType = staffRepo.findByStaffId(curStaff).getStaffType();


        User  user = new User(username,curStaff,staffType);
        httpSession.setAttribute(Common.USER_INFO,user);
        return "function";
    }

    @RequestMapping("/doregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String a[] = request.getParameterValues("部门名称");
        int departmentId = departmentRepo.findByDepartmentName(a[0]).get(0).getDepartId();

        StaffInfo info = new StaffInfo();
        info.setUsername(username);
        info.setPassword(password);
        staffInfoRepo.save(info);

        int staffInfoId = staffInfoRepo.findByUsernameAndPassword(username,password).get(0)
                .getId();
        Staff staff  = new Staff();

        staff.setStaffId(staffInfoId);
        staff.setDepartmentId(departmentId);
        staff.setName(username);
        staff.setSalary(CalUtils.randomSalary());
        staff.setStaffType(CalUtils.randomType());
        staff.setTitle(CalUtils.randomTitle(departmentId));

        staffRepo.save(staff);
        return "login";
    }

}
