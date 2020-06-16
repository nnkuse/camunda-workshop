package com.workshop.camunda.service.delegate.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import static com.workshop.camunda.config.CommonConstants.TASK_VARIABLE_RETRY;


/**
 * To be used as a start execution listener to inject a retry flag (local variable) with default value of FALSE.
 */
public class RetryFlagInjectionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariableLocal(TASK_VARIABLE_RETRY, Boolean.FALSE);
    }
}
