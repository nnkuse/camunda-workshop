package com.workshop.another.service.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface StreamChannel {

    String MESSAGE_INPUT = "MessageInput";
    String MESSAGE_OUTPUT = "MessageOutput";

    @Input(StreamChannel.MESSAGE_INPUT)
    SubscribableChannel streamingMessageInput();

    @Output(StreamChannel.MESSAGE_OUTPUT)
    SubscribableChannel streamingMessageOutput();
}
