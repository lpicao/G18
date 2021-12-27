package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.TravelAgentProcessDTO;
import com.mycompany.myapp.domain.TravelAgent;
import com.mycompany.myapp.repository.TravelAgentRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;

@Component
public class GerarCupaoDelegate implements JavaDelegate {

    @Autowired
    TravelAgentRepository travelAgentRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        TravelAgentProcessDTO travelAgentProcess = (TravelAgentProcessDTO) delegateExecution.getVariable("processInstance");
	TravelAgent travelAgent = travelAgentRepository.getOne(travelAgentProcess.getTravelAgent().getId());
	String codigoPromocional = "";
	for(int i = 0; i != 5; i++){
		Random r1 = new Random();
		int aux = r1.nextInt(10);
		codigoPromocional = codigoPromocional + aux;
	}

	travelAgent.setCodigoCupao(codigoPromocional);
	travelAgentRepository.save(travelAgent);
    }
}