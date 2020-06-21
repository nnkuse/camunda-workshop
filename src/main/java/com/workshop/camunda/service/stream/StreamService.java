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

import static com.workshop.camunda.config.CommonConstants.INCOMING_STREAM_FROM_MESSAGE;
import static com.workshop.camunda.service.stream.StreamChannel.CAMUNDA_PROCESS_INPUT;

@Service
@Slf4j
@EnableBinding(StreamChannel.class)
public class StreamService {

    @Autowired
    private StreamChannel streamChannel;

    @Autowired
    private CamundaService camundaService;


    @StreamListener(CAMUNDA_PROCESS_INPUT)
    public void receiveFromLoanPreApprovalProcessInput(@Payload CamundaProcessRequest request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        log.debug(INCOMING_STREAM_FROM_MESSAGE, CAMUNDA_PROCESS_INPUT, httpHeaders.toString(), request);
        camundaService.startCamundaProcess(request, httpHeaders);
    }
}
