package com.workshop.camunda.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonConstants {

    public static final String HEADER_CORRELATION_ID = "correlationid";
    public static final String HEADER_USER_ID = "userId";
    public static final String HEADER_PROCESS_ID = "processId";
    public static final String HEADER_CAMUNDA_MSG_NAME = "camunda-msg-name";
    public static final String HEADER_PROCESS_INSTANCE_ID = "processInstanceId";
    public static final String FLAG_Y = "Y";
    public static final String HEADER_CUSTOMER_ID = "customerId";

    public static final String INCOMING_STREAM_FROM_MESSAGE = "Incoming stream message from {}. Message -> \nHeader: {}, \nBody: \n{}";
    public static final String OUTCOMING_STREAM_TO_MESSAGE = "Message sent to {}. - {} Message -> \nHeader: {}, \nBody: \n{}";

    // Process key
    public static final String PROCESS_KEY_CAMUNDA_PROCESS = "CAMUNDA_PROCESS";

    // Variable key
    public static final String TASK_VARIABLE_ID = "varId";
    public static final String TASK_VARIABLE_NAME = "varName";
    public static final String TASK_VARIABLE_FIRST_NAME = "varFirstName";
    public static final String TASK_VARIABLE_LAST_NAME = "varLastName";
    public static final String TASK_VARIABLE_MESSAGE = "varMessage";
    public static final String TASK_VARIABLE_HTTP_HEADER = "varHttpHeader";
    public static final String TASK_VARIABLE_RETRY = "varRetry";

}
