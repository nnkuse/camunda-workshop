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

import java.util.List;

@Slf4j
@Service
public class CamundaService {

    @Autowired
    private RuntimeService runtimeService;

    public void startCamundaProcess(CamundaProcessRequest request, HttpHeaders headers) {
        runtimeService.startProcessInstanceByKey(
                "CAMUNDA_PROCESS_WORK_SHOP",
                request.getId(),
                Variables.putValue("varHttpHeader", headers).
                        putValue("varCorrelationId", headers.getFirst("correlationid")).
                        putValue("varId", request.getId()).
                        putValue("varMessage", request.getMessage()).
                        putValue("varFlag", request.getFlag())
        );
    }

    public void resumeAsyncTask(String request, HttpHeaders headers) {
        try {
            ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
            query.variableValueEquals("varCorrelationId", headers.getFirst("correlationid"));
            ProcessInstance processInstance = query.active().singleResult();
            if (processInstance == null) {
                log.warn("Cannot find active processInstance for CORRELATION_ID {} and UserID {}", headers.getFirst("correlationid"));
            } else {
                log.info("Resume Task pring message: {}, correlationid: {}", request, headers.getFirst("correlationid"));
                runtimeService.createMessageCorrelation(headers.getFirst("camunda-msg-name")).processInstanceId(processInstance.getProcessInstanceId()).correlate();
            }
        } catch (Exception ex) {
            ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
            // Query process instance by correlationId
            query.variableValueEquals("varCorrelationId", headers.getFirst("correlationid"));

            // Add condition to check whether compile process is still in progress or not
            // (Process still inside sub-process)
            query.activityIdIn("SUB_PROCESS", "SEND_MESSAGE_TASK", "RECEVIVE_MESSAGE");

            List<ProcessInstance> processInstanceList = query.list();

            // If found any record, assume that compile process hasn't done yet rethrow exception to let MS reprocess message again
            if (!processInstanceList.isEmpty()) {
                log.error("Exception: {}", ex.toString());
                throw ex;
            }
        }
    }
}
