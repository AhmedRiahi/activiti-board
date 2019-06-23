import { Component, OnInit } from '@angular/core';
import {ActivitiBoardService} from '../services/activiti-board.service'

import {ProcessDefinitionPayload} from '../payloads/ProcessDefinitionPayload';
import {ActivityInstancePayload} from '../payloads/ActivityInstancePayload';
import {ActivityInstanceDetailsPayload} from '../payloads/ActivityInstanceDetailsPayload';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	processDefinitionPaylods:ProcessDefinitionPayload[];
	activityInstancePayloads:ActivityInstancePayload[];
	activityInstanceDetailsPayloads:ActivityInstanceDetailsPayload[];

	selectedProcessDefinition:ProcessDefinitionPayload;
	selectedActivityInstance:ActivityInstancePayload;

	constructor(private activitiBoardService:ActivitiBoardService) { }

	ngOnInit() {
		this.activitiBoardService.getProcessDefinitions().subscribe(data => {
			this.processDefinitionPaylods = data;
		})
	}

	selectProcessDefinition(processDefinition){
		this.selectedProcessDefinition = processDefinition;
		this.activitiBoardService.getActivityInstance(this.selectedProcessDefinition.id).subscribe(data => {
			this.activityInstancePayloads = data;
		})
	}

	selectActivityInstance(activityInstance){
		this.selectedActivityInstance = activityInstance;
		this.activitiBoardService.getActivityInstanceDetails(this.selectedActivityInstance.processInstanceId).subscribe(data => {
			this.activityInstanceDetailsPayloads = data;
		})
	}

}
