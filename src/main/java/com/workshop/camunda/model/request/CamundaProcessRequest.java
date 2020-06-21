package com.workshop.camunda.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CamundaProcessRequest implements Serializable {
    private String id;
    private String message;
    private Boolean flag;
}
