package com.activiti.board.api.rest;


import com.activiti.board.api.payload.DeploymentPayload;
import com.activiti.board.api.payload.ProcessDefinitionPayload;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workflow")
@CrossOrigin
public class WorkflowController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("deployed")
    public List<DeploymentPayload> allDeployment(){
        List<Deployment> deployments = this.repositoryService.createDeploymentQuery().list();

        return deployments.stream().map(deployment -> {
            DeploymentPayload deploymentPayload = new DeploymentPayload();
            BeanUtils.copyProperties(deployment,deploymentPayload);
            return deploymentPayload;
        }).collect(Collectors.toList());
    }


    @GetMapping("processDefinition")
    public List<ProcessDefinitionPayload> allProcessDefinitions(){
        List<ProcessDefinition> processDefinitions = this.repositoryService.createProcessDefinitionQuery().list();
        return processDefinitions.stream()
                .map(processDefinition -> {
                    ProcessDefinitionPayload processDefinitionPayload = new ProcessDefinitionPayload();
                    BeanUtils.copyProperties(processDefinition,processDefinitionPayload);
                    return processDefinitionPayload;
                }).collect(Collectors.toList());
    }

    @GetMapping("activityInstance/{processDefinitionId}")
    public List<HistoricActivityInstance> activityInstances(@PathVariable String processDefinitionId){
        return this.historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).list();
    }

    @GetMapping("activityInstanceDetails/{processInstanceId}")
    public List<HistoricDetail> activityInstanceDetails(@PathVariable String processInstanceId){
        return this.historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();
    }
}
