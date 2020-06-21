package com.workshop.camunda.service.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface StreamChannel {

    String CAMUNDA_PROCESS_INPUT = "CamundaProcessInput";
    String CREATE_FILE_INPUT = "CreateFileInput";
    String CREATE_FILE_OUTPUT = "CreateFileOutput";

    @Input(StreamChannel.CAMUNDA_PROCESS_INPUT)
    SubscribableChannel streamingCamundaProcessInput();

    @Input(StreamChannel.CREATE_FILE_INPUT)
    SubscribableChannel streamingCreateFileInput();

    @Output(StreamChannel.CREATE_FILE_OUTPUT)
    SubscribableChannel streamingCreateFileOutput();

}
