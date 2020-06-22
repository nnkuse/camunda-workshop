package com.workshop.another.service;

import com.workshop.another.service.stream.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubProcessService {

    @Autowired
    StreamService streamService;
    public void printMessageInput(String message, HttpHeaders httpHeaders) {
        log.info("External service print message: {}}, correlationid: {}", message, httpHeaders.getFirst("correlationid"));
        streamService.publishToReceiveMessageTask(message, httpHeaders);
    }
}
