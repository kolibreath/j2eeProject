package com.kolibreath.j2eefinal;

import com.kolibreath.j2eefinal.entity.Department;
import com.kolibreath.j2eefinal.entity.PunchCard;
import com.kolibreath.j2eefinal.repo.DepartmentRepo;
import com.kolibreath.j2eefinal.repo.PunchCardRepo;
import com.kolibreath.j2eefinal.repo.StaffInfoRepo;
import com.kolibreath.j2eefinal.repo.StaffRepo;
import com.kolibreath.j2eefinal.scheduler.CollectionPunchJob;
import com.kolibreath.j2eefinal.scheduler.GetSchedual;
import com.kolibreath.j2eefinal.scheduler.StartPunchJob;
import com.kolibreath.j2eefinal.scheduler.StopPunchJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@SpringBootApplication
@ComponentScan (basePackages = {"com.kolibreath.j2eefinal.repo","com.kolibreath.j2eefinal.controller"})
public class J2eefinalApplication {

	//AutoWired
	@Autowired
	StaffInfoRepo staffInfoRepo;
	@Autowired
	DepartmentRepo departmentRepo;
	@Autowired
	StaffRepo staffRepo;
	@Autowired
	PunchCardRepo punchCardRepo;

	private void startJobs(){
		JobDetail startPunchDetail  = JobBuilder.newJob(StartPunchJob.class)
				.withDescription("开始签到")
				.build();
		DailyTimeIntervalTrigger startTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("startTrigger", "group")
				.withSchedule(
						DailyTimeIntervalScheduleBuilder
								.dailyTimeIntervalSchedule()
								.startingDailyAt(TimeOfDay.hourAndMinuteOfDay(8,0))
								.withInterval(24, DateBuilder.IntervalUnit.HOUR)
				).build();


		JobDetail stopPunchDetail = JobBuilder.newJob(StopPunchJob.class)
				.withDescription("停止签到")
				.build();

		DailyTimeIntervalTrigger stopTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("stopTrigger", "group")
				.withSchedule(
						DailyTimeIntervalScheduleBuilder
								.dailyTimeIntervalSchedule()
								.startingDailyAt(TimeOfDay.hourAndMinuteOfDay(22,0))
								.withInterval(24, DateBuilder.IntervalUnit.HOUR))
				.build();

		JobDetail collectPunchDetail = JobBuilder.newJob(CollectionPunchJob.class)
				.withDescription("开始收集签到情况")
				.build();

		DailyTimeIntervalTrigger collectionTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("collectionTrigger", "group")
				.withSchedule(
						DailyTimeIntervalScheduleBuilder
								.dailyTimeIntervalSchedule()
								.startingDailyAt(TimeOfDay.hourAndMinuteOfDay(22,0))
								.withInterval(24, DateBuilder.IntervalUnit.HOUR))
				.build();

		try {
			if(!GetSchedual.getScheduler().checkExists(startTrigger.getKey()))
				GetSchedual.getScheduler().scheduleJob(startPunchDetail, startTrigger);
			if(!GetSchedual.getScheduler().checkExists(stopTrigger.getKey()))
				GetSchedual.getScheduler().scheduleJob(stopPunchDetail,stopTrigger);
			if(!GetSchedual.getScheduler().checkExists(collectionTrigger.getKey()))
				GetSchedual.getScheduler().scheduleJob(collectPunchDetail,stopTrigger);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private void initData(){
		List<Department> list = departmentRepo.findAll();
		if(list.size() != 0)
			return;
		Department department = new Department();
		department.setDepartmentName("研发部门");
		department.setSiginStartTime(9);
		department.setSignoutStartTime(9);
		departmentRepo.save(department);

		Department department1 = new Department();
		department1.setDepartmentName("设计部门");
		department1.setSiginStartTime(9);
		department1.setSignoutStartTime(6);
		departmentRepo.save(department1);

		Department department2 = new Department();
		department2 = new Department();
		department2.setDepartmentName("主管部门");
		department2.setSiginStartTime(9);
		department2.setSignoutStartTime(4);
		departmentRepo.save(department2);
	}

	@RequestMapping("/index")
	public String index(){
		startJobs();
		initData();
		return "index";
	}



	@RequestMapping("/showFunctions")
	public String showFunctions(){
		return "function";
	}



	public static void main(String[] args) {
		SpringApplication.run(J2eefinalApplication.class, args);

	}

}


