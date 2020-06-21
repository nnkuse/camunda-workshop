package com.workshop.camunda.service;

import com.workshop.camunda.model.request.CamundaProcessRequest;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
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

    public void startCamundaProcess(CamundaProcessRequest request, HttpHeaders httpHeaders){
        runtimeService.startProcessInstanceByKey(
                PROCESS_KEY_CAMUNDA_PROCESS,
                request.getId(),
                Variables.putValue(TASK_VARIABLE_ID, request.getId()).
                        putValue(TASK_VARIABLE_NAME, request.getName()).
                        putValue(TASK_VARIABLE_FIRST_NAME, request.getFirstName()).
                        putValue(TASK_VARIABLE_LAST_NAME, request.getLastName()).
                        putValue(TASK_VARIABLE_MESSAGE, request.getMessage())
        );
    }
}
