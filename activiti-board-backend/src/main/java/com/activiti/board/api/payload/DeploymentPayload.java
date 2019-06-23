package com.activiti.board.api.payload;


import lombok.Data;

import java.util.Date;

@Data
public class DeploymentPayload {

    private String id;
    private String name;
    private Date deploymentTime;
}
