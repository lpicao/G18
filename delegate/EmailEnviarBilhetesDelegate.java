package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.TravelAgentDTO;
import com.mycompany.myapp.service.dto.TravelAgentProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Component
public class EmailEnviarBilhetesDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelAgentProcessDTO travelAgentProcess = (TravelAgentProcessDTO) delegateExecution.getVariable("processInstance");
        TravelAgentDTO travelAgent = travelAgentProcess.getTravelAgent();
        String to = travelAgent.getMail();
	String subject = "[AgileKip] Summary of your travel " + travelAgent.getIdBilhete();
	Context context = new Context(Locale.getDefault());
	context.setVariable("travelAgent", travelAgent);
	String content = templateEngine.process("travelAgentProcess/emailEnviarBilhetesDelegate", context);
	mailService.sendEmail(to, subject, content, false, true);
    }
}