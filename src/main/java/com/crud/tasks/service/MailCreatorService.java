package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private DBService dbService;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Value("${info.company.name}")
    private String companyDetails;


    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("tasks_url","https://lukstasko.github.io/");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("goodbye_message","Have a nice day!");
        context.setVariable("company_details",companyDetails);
        context.setVariable("is_friend",true);
        context.setVariable("admin_config",adminConfig);
        context.setVariable("message", message);
        context.setVariable("show_button",false);

        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);

    }

    public String buildTaskDailyReportMail(){

        List<Task> taskList=dbService.getAllTasks();
        String message="You have " + taskList.size()+" tasks today:" ;

        Context raportContext = new Context();
        raportContext.setVariable("message", message);
        raportContext.setVariable("tasks_url","https://lukstasko.github.io/");
        raportContext.setVariable("button", "Visit website");
        raportContext.setVariable("goodbye_message", "Have a nice day!");
        raportContext.setVariable("company_details", companyDetails);
        raportContext.setVariable("show_button", true);
        raportContext.setVariable("is_friend", true);
        raportContext.setVariable("admin_config", adminConfig);
        raportContext.setVariable("application_functionality", taskList);

        return templateEngine.process("mail/task_daily_report_mail", raportContext);


    }

}
