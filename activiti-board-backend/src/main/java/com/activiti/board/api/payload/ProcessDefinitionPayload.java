package com.activiti.board.api.payload;


import lombok.Data;

@Data
public class ProcessDefinitionPayload {

    private String id;
    private String name;
    private String description;
    private String key;
    private String deploymentId;
    private String resourceName;
}
