import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { Tutorial } from './tutorial';

@Injectable()
export class TutorialService {
    //URLs for CRUD operations
    allTutorialsUrl = "http://localhost:8080/user/all-tutorials";
    tutorialUrl = "http://localhost:8080/user/tutorial";
    //Create constructor to get Http instance
    constructor(private http:Http) { 
    }
    //Fetch all tutorials
    getAllTutorials(): Observable<Tutorial[]> {
        return this.http.get(this.allTutorialsUrl)
	       .map(this.extractData)
	       .catch(this.handleError);

    }
    //Create tutorial
    createTutorial(tutorial: Tutorial):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.tutorialUrl, tutorial, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
    //Fetch tutorial by id
    getTutorialById(tutorialId: string): Observable<Tutorial> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
	let cpParams = new URLSearchParams();
	cpParams.set('id', tutorialId);			
	let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
	return this.http.get(this.tutorialUrl, options)
		.map(this.extractData)
		.catch(this.handleError);
    }	
    //Update tutorial
    updateTutorial(tutorial: Tutorial):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.put(this.tutorialUrl, tutorial, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
    //Delete tutorial	
    deleteTutorialById(tutorialId: string): Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
	let cpParams = new URLSearchParams();
	cpParams.set('id', tutorialId);			
	let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
	return this.http.delete(this.tutorialUrl, options)
	       .map(success => success.status)
	       .catch(this.handleError);
    }		
    private extractData(res: Response) {
	let body = res.json();
        return body;
    }
    private handleError (error: Response | any) {
	console.error(error.message || error);
	return Observable.throw(error.status);
    }
} 