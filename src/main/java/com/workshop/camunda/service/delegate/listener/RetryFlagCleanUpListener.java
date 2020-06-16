package com.workshop.camunda.service.delegate.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import static com.workshop.camunda.config.CommonConstants.TASK_VARIABLE_RETRY;


/**
 * To be used as an end execution listener to remove retry flag (local variable).
 */
public class RetryFlagCleanUpListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.removeVariableLocal(TASK_VARIABLE_RETRY);
    }
}
