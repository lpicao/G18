package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.TravelAgentProcessDTO;
import com.mycompany.myapp.domain.TravelAgent;
import com.mycompany.myapp.repository.TravelAgentRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Component
public class CalcularTempodeViagemDelegate implements JavaDelegate {

    @Autowired
    TravelAgentRepository travelAgentRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        TravelAgentProcessDTO travelAgentProcess = (TravelAgentProcessDTO) delegateExecution.getVariable("processInstance");
	TravelAgent travelAgent = travelAgentRepository.getOne(travelAgentProcess.getTravelAgent().getId());


	LocalDate inicioDataViagem= travelAgentProcess.getTravelAgent().getInicioDataViagem();
	LocalDate fimDataViagem = travelAgentProcess.getTravelAgent().getFimDataViagem();

	Date dateInicio = new Date(inicioDataViagem.getYear(), inicioDataViagem.getMonthValue(), inicioDataViagem.getDayOfMonth());
	Date dateFim = new Date(fimDataViagem.getYear(), fimDataViagem.getMonthValue(), fimDataViagem.getDayOfMonth());

	long diffDays = dateFim.getTime() - dateInicio.getTime();
	Integer diferenca = (int) TimeUnit.DAYS.convert(diffDays, TimeUnit.MILLISECONDS);

	Boolean maisQue5 = false;
	if(diferenca > 5){
		maisQue5 = true;
	}
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");

	System.out.println("Numero dias viagem:   " + diferenca);

	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");
	System.out.println("##################################################################################");

	delegateExecution.setVariable("maisQue5", maisQue5);
	travelAgent.setMaisQue5(maisQue5);
	travelAgentRepository.save(travelAgent);
    }
}