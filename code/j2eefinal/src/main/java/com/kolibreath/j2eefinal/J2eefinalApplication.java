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
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;


@Controller
@SpringBootApplication
@ServletComponentScan("com.kolibreath.j2eefinal.filter")
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

	private CronTrigger buildCronTrigger(String name,String group, String expression){
		CronTrigger startTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity(name, group)
				.withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
		return startTrigger;
	}


	private void startJobs(){
		JobDetail startPunchDetail  = JobBuilder.newJob(StartPunchJob.class)
				.withDescription("开始签到")
				.build();

		CronTrigger startTrigger = buildCronTrigger("startTrigger","group","\"0 7 0 ? * MON-FRI\"");


		JobDetail stopPunchDetail = JobBuilder.newJob(StopPunchJob.class)
				.withDescription("停止签到")
				.build();

		CronTrigger stopTrigger = buildCronTrigger("stopTrigger","group1","\"0 19 00 ? * MON-FRI\"");

		JobDetail collectPunchDetail = JobBuilder.newJob(CollectionPunchJob.class)
				.withDescription("开始收集签到情况")
				.build();

	     CronTrigger collectionTrigger = buildCronTrigger("collectTrigger","group2","\"0 0 0 ? * MON-FRI\"");

		try {
			if(!GetSchedual.getScheduler().checkExists(startTrigger.getKey()))
				GetSchedual.getScheduler().scheduleJob(startPunchDetail, startTrigger);
			if(!GetSchedual.getScheduler().checkExists(stopTrigger.getKey()))
				GetSchedual.getScheduler().scheduleJob(stopPunchDetail,stopTrigger);
			if(!GetSchedual.getScheduler().checkExists(collectionTrigger.getKey()))
				GetSchedual.getScheduler().scheduleJob(collectPunchDetail,collectionTrigger);

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
		department.setSignoutStartTime(21);
		departmentRepo.save(department);

		Department department1 = new Department();
		department1.setDepartmentName("设计部门");
		department1.setSiginStartTime(9);
		department1.setSignoutStartTime(18);
		departmentRepo.save(department1);

		new Department();
		Department department2;
		department2 = new Department();
		department2.setDepartmentName("主管部门");
		department2.setSiginStartTime(9);
		department2.setSignoutStartTime(17);
		departmentRepo.save(department2);
	}

	private void initData2() {
		//生成打卡记录
		String date = "2019-04-";
		for (int i = 1; i <= 30; i++) {
			String signIn = "";
			String signOut = "";
			String curDate = date;

			int signInHour = 0;
			int signOutHour = 0;
			String day = "";
			if (i / 10 == 0) {
				day = "0" + i;
			} else {
				day = String.valueOf(i);
			}
			signInHour = ((int) ((new Random().nextDouble())*10)%5) + 5;
			signOutHour = ((int) ((new Random().nextDouble())*10)%7) + 12;;

			curDate = curDate + day;
			//签到时间 hour
			String signInAppend = signInHour / 10 == 0 ? "0" + signInHour : signInHour + "";
			String signOutAppend = signOutHour / 10 == 0 ? "0" + signOutHour : signOutHour + "";

			signIn = date + day + "-" + signInAppend + "-" + randomSixty() + "-" + randomSixty();
			signOut = date + day + "-" + signOutAppend + "-" + randomSixty() + "-" + randomSixty();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			long sigintime = 0;
			long signouttime = 0;
			try {
				sigintime = simpleDateFormat.parse(signIn).getTime();
				signouttime = simpleDateFormat.parse(signOut).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
			PunchCard card = new PunchCard();
			card.setSignInStamp(sigintime);
			card.setSignOutStamp(signouttime);
			card.setStaffId(326);
			card.setDate(curDate);

			punchCardRepo.save(card);
		}
	}

	@RequestMapping("/index")
	public String index(){
		startJobs();
		collect();
		initData();
//		initData2();
		return "index";
	}



	@RequestMapping("/showFunctions")
	public String showFunctions(){
		return "function";
	}


	private  void collect(){
		CalUtils.collectionPunchCard(staffRepo,punchCardRepo,departmentRepo);
	}
	public static int randomSixty(){
		return Math.abs(new Random().nextInt()*100 +new Random().nextInt()*10) %60;
	}

	public static void main(String[] args) throws ParseException {

		SpringApplication.run(J2eefinalApplication.class, args);
	}

}


