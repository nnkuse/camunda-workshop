package com.workshop.camunda.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CamundaProcessRequest implements Serializable {
    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String message;
}
