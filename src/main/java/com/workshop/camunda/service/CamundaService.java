package com.workshop.camunda.service;

import com.workshop.camunda.model.request.CamundaProcessRequest;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static com.workshop.camunda.config.CommonConstants.*;

@Slf4j
@Service
public class CamundaService {

    @Autowired
    private RuntimeService runtimeService;

    public void startCamundaProcess(CamundaProcessRequest request, HttpHeaders headers){
        runtimeService.startProcessInstanceByKey(
                "CAMUNDA_PROCESS",
                request.getId(),
                Variables.putValue("varHttpHeader", headers).
                        putValue("varCorrelationId", headers.getFirst("correlationid")).
                        putValue("varId", request.getId()).
                        putValue("varMessage", request.getMessage()).
                        putValue("varFlag", request.getFlag())
        );
    }

    public void resumeAsyncTask(String request, HttpHeaders headers) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        query.variableValueEquals("varCorrelationId", headers.getFirst("correlationid"));
        ProcessInstance processInstance = query.active().singleResult();
        if (processInstance == null) {
            log.warn("Cannot find active processInstance for CORRELATION_ID {} and UserID {}", headers.getFirst("correlationid"));
        } else {
            System.out.println(request);
            runtimeService.createMessageCorrelation(headers.getFirst("camunda-msg-name")).processInstanceId(processInstance.getProcessInstanceId()).correlate();
        }
    }
}
