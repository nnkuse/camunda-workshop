package com.workshop.camunda.service.stream;

import com.workshop.camunda.model.request.CamundaProcessRequest;
import com.workshop.camunda.service.CamundaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import th.co.scb.fasteasy.stream.interceptor.util.StreamMessageFactory;

import static com.workshop.camunda.service.stream.StreamChannel.*;

@Service
@Slf4j
@EnableBinding(StreamChannel.class)
public class StreamService {

    @Autowired
    private StreamChannel streamChannel;

    @Autowired
    private CamundaService camundaService;

    @StreamListener(CAMUNDA_PROCESS_INPUT)
    public void receiveFromCamundaProcessInput(@Payload CamundaProcessRequest request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        log.debug("Incoming stream message from {}. Message -> \nHeader: {}, \nBody: \n{}", CAMUNDA_PROCESS_INPUT, httpHeaders.toString(), request);
        camundaService.startCamundaProcess(request, httpHeaders);
    }

    // sub-process
    public void sendMessageTask(String request, HttpHeaders headers) {
        headers.set("camunda-msg-name", "RECEVIVE_MESSAGE");
        boolean result = streamChannel.streamingMessageOutput().send(
                StreamMessageFactory.getMessage(request, headers)
        );
        log.debug("Message sent to {}.", result);
    }
    @StreamListener(MESSAGE_INPUT)
    public void receiveFromIncomingMessageInput(@Payload String request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        log.debug("Incoming stream message from {}. Message -> \nHeader: {}, \nBody: \n{}", CAMUNDA_PROCESS_INPUT, httpHeaders.toString(), request);
        camundaService.resumeAsyncTask(request, httpHeaders);
    }

}
