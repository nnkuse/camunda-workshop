package com.workshop.camunda.service.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface StreamChannel {

    String CAMUNDA_PROCESS_INPUT = "CamundaProcessInput";
    String INCOMING_MESSAGE_INPUT = "MessageInput";
    String OUTGOING_MESSAGE_OUTPUT = "MessageOutput";

    @Input(StreamChannel.CAMUNDA_PROCESS_INPUT)
    SubscribableChannel streamingCamundaProcessInput();

    @Input(StreamChannel.INCOMING_MESSAGE_INPUT)
    SubscribableChannel streamingIncomingMessageInput();

    @Output(StreamChannel.OUTGOING_MESSAGE_OUTPUT)
    SubscribableChannel streamingOutgoingMessageOutput();

}
