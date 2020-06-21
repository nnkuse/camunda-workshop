package com.workshop.camunda.service.delegate;

import com.workshop.camunda.service.stream.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMessageTaskDelegate implements JavaDelegate {

    @Autowired
    StreamService streamService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders httpHeaders = (HttpHeaders) delegateExecution.getVariable("varHttpHeader");
        String id = (String) delegateExecution.getVariable("varId");
        String message = (String) delegateExecution.getVariable("varMessage");
        Boolean flag = (Boolean) delegateExecution.getVariable("varFlag");
        System.out.println("Process One print message: " + message);

        streamService.sendMessageTask(message, httpHeaders);
    }
}
