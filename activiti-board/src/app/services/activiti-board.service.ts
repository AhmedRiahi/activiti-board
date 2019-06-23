import { Injectable } from '@angular/core';
import { HttpClient }    from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

import {ProcessDefinitionPayload} from '../payloads/ProcessDefinitionPayload';
import {ActivityInstancePayload} from '../payloads/ActivityInstancePayload';
import {ActivityInstanceDetailsPayload} from '../payloads/ActivityInstanceDetailsPayload';

@Injectable({
  providedIn: 'root'
})
export class ActivitiBoardService {

	env = environment;

  	constructor(private http:HttpClient) { }

	getProcessDefinitions():Observable<ProcessDefinitionPayload[]>{
		return this.http.get<ProcessDefinitionPayload[]>(this.env.serverUrl+'workflow/processDefinition/');
	}

	getActivityInstance(processDefinitionId):Observable<ActivityInstancePayload[]>{
		return this.http.get<ActivityInstancePayload[]>(this.env.serverUrl+"workflow/activityInstance/"+processDefinitionId);
	}

	getActivityInstanceDetails(processInstanceId):Observable<ActivityInstanceDetailsPayload[]>{
		return this.http.get<ActivityInstanceDetailsPayload[]>(this.env.serverUrl+"workflow/activityInstanceDetails/"+processInstanceId);
	}
}
