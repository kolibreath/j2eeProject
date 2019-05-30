package com.kolibreath.j2eefinal.controller

import com.kolibreath.j2eefinal.controller.SalaryController.PunchCard.signOutStamp
import me.liuwj.ktorm.dsl.notEq
import me.liuwj.ktorm.dsl.select
import me.liuwj.ktorm.dsl.where
import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.varchar
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.text.SimpleDateFormat
import java.util.*


@Controller
class SalaryController {

    @RequestMapping("/salary")
    fun lastMonthSalary():ModelAndView{
        me.liuwj.ktorm.database.Database.connect("jdbc:mysql://localhost:3306/staff_management",driver="com.mysql.jdbc.Driver",
                user = "kolibreath", password = "szypride")
        val cards = PunchCard
                .select()
                .where { (PunchCard.status notEq  0) }

        val salaryScripts = ArrayList<SalaryScript>()

        fun  parse(time : Long):String {
            val format  = SimpleDateFormat("yyyy-MM-dd");
            return format.format(time)
        }
        for ( card in cards){
            val salaryScript = SalaryScript(
                    date = card[PunchCard.date]!!,
                    signInStamp =  parse(card[PunchCard.signInStamp]!!),
                    signOutStamp = parse(card[PunchCard.signOutStamp]!!),
                    final = 0)
            salaryScripts.add(salaryScript)
        }

        val salaryScript = SalaryScript(
                date = "",
                signInStamp =  "",
                signOutStamp = "",
                final = 6000 - salaryScripts.size)
        salaryScripts.add(salaryScript)

        val modelAndView = ModelAndView()
        modelAndView.addObject("salaryRecord",salaryScripts)
        modelAndView.viewName = "/salary"
        return modelAndView
    }

    class SalaryScript{

        var date :String = ""
        var signInStamp : String = ""
        var signOutStamp :String = ""
        var final :Int  = 0

        constructor(
                date :String,
                signInStamp : String,
                signOutStamp :String,
                final :Int
        ) {
            this.date = date
            this.signInStamp = signInStamp
            this.signOutStamp = signOutStamp
            this.final  = final
        }
    }
    object PunchCard:Table<Nothing>("punch_card"){


//        @Id
//        @GeneratedValue
//        private int punchCardId;
//
//        @Column
//        private long signInStamp;
//
//        @Column
//        private long signOutStamp;
//
//        @Column
//        private String date;
//
//        @Column
//        private int staffId;
//
//        @Column
//        private int status;

        val punchCardId by int("punch_card_id").primaryKey()
        val date by varchar("date")
        val signInStamp by long("sign_in_stamp")
        val signOutStamp by long("sign_out_stamp")
        val staffId by int("staff_id")
        val status by int("status")
    }
}