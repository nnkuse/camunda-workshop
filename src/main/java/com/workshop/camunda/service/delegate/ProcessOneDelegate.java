package com.workshop.camunda.service.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessOneDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders httpHeaders = (HttpHeaders) delegateExecution.getVariable("varHttpHeader");
        String id = (String) delegateExecution.getVariable("varId");
        String message = (String) delegateExecution.getVariable("varMessage");
        Boolean flag = (Boolean) delegateExecution.getVariable("varFlag");
        log.info("Process One print message: {}, correlationid: {}", message, httpHeaders.getFirst("correlationid"));
    }
}
