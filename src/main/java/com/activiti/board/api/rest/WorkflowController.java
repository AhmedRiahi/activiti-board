package com.activiti.board.api.rest;


import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("deployed")
    public List<Deployment> allDeployment(){
        List<Deployment> deployments = this.repositoryService.createDeploymentQuery().list();
        return deployments;
    }
}
