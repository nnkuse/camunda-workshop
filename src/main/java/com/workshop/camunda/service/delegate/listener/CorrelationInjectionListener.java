package com.workshop.camunda.service.delegate.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;

import static com.workshop.camunda.config.CommonConstants.HEADER_CORRELATION_ID;
import static com.workshop.camunda.config.CommonConstants.TASK_VARIABLE_HTTP_HEADER;

@Slf4j
public class CorrelationInjectionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders httpHeaders = (HttpHeaders) delegateExecution.getVariable(TASK_VARIABLE_HTTP_HEADER);
        String correlationId = httpHeaders.getFirst(HEADER_CORRELATION_ID);
        MDC.put(HEADER_CORRELATION_ID, correlationId);
        log.debug("Added correlation id", correlationId);
    }
}
