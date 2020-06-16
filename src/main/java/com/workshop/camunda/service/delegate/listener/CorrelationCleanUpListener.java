package com.workshop.camunda.service.delegate.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.MDC;

import static com.workshop.camunda.config.CommonConstants.HEADER_CORRELATION_ID;

@Slf4j
public class CorrelationCleanUpListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        log.debug("Removing correlation id");
        MDC.remove(HEADER_CORRELATION_ID);
    }
}
