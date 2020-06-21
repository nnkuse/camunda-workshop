package com.workshop.camunda.controller;

import com.workshop.camunda.model.request.CamundaProcessRequest;
import com.workshop.camunda.service.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamundaProcessController {

    @Autowired
    CamundaService camundaService;

    @PostMapping("/message")
    public ResponseEntity postMessage(@RequestBody CamundaProcessRequest request, @RequestHeader HttpHeaders httpHeaders) {
        camundaService.startCamundaProcess(request, httpHeaders);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
