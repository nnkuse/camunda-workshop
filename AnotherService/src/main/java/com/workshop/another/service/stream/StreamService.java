package com.workshop.another.service.stream;

import com.workshop.another.service.SubProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import th.co.scb.fasteasy.stream.interceptor.util.StreamMessageFactory;

import static com.workshop.another.service.stream.StreamChannel.MESSAGE_INPUT;

@Service
@Slf4j
@EnableBinding(StreamChannel.class)
public class StreamService {

    @Autowired
    private StreamChannel streamChannel;
    @Autowired
    private SubProcessService subProcessService;

    @StreamListener(MESSAGE_INPUT)
    public void receiveFromSendMessageTask(@Payload String request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        log.debug("Incoming stream message from {}. Message -> \nHeader: {}, \nBody: \n{}", MESSAGE_INPUT, httpHeaders.toString(), request);
        subProcessService.printMessageInput(request, httpHeaders);
    }
    public void publishToReceiveMessageTask(String request, HttpHeaders headers) {
        headers.set("camunda-msg-name", "RECEVIVE_MESSAGE");
        boolean result = streamChannel.streamingMessageOutput().send(
                StreamMessageFactory.getMessage(request, headers)
        );
        log.debug("Message sent to {}.", result);
    }

}
