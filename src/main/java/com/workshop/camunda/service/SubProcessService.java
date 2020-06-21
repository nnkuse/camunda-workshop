package com.workshop.camunda.service;

import com.workshop.camunda.service.stream.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubProcessService {

    @Autowired
    StreamService streamService;
    public void printMessageInput(String message, HttpHeaders headers) {
        System.out.println(message);
        streamService.publishToReceiveMessageTask(message, headers);
    }
}
