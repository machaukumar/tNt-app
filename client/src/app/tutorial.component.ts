import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { TutorialService } from './tutorial.service';
import { Tutorial } from './tutorial';

@Component({
   selector: 'app-tutorial',
   templateUrl: './tutorial.component.html',
   styleUrls: ['./tutorial.component.css']
})
export class TutorialComponent implements OnInit { 
   //Component properties
   allTutorials: Tutorial[];
   statusCode: number;
   requestProcessing = false;
   tutorialIdToUpdate = null;
   processValidation = false;
   //Create form
   tutorialForm = new FormGroup({
       title: new FormControl('', Validators.required),
       category: new FormControl('', Validators.required)	   
   });
   //Create constructor to get service instance
   constructor(private tutorialService: TutorialService) {
   }
   //Create ngOnInit() and and load tutorials
   ngOnInit(): void {
	   this.getAllTutorials();
   }   
   //Fetch all tutorials
   getAllTutorials() {
        this.tutorialService.getAllTutorials()
	   .subscribe(
                data => this.allTutorials = data,
                errorCode =>  this.statusCode = errorCode);   
   }
   //Handle create and update tutorial
   onTutorialFormSubmit() {
	  this.processValidation = true;   
	  if (this.tutorialForm.invalid) {
	       return; //Validation failed, exit from method.
	  }   
	  //Form is valid, now perform create or update
          this.preProcessConfigurations();
	  let title = this.tutorialForm.get('title').value.trim();
          let category = this.tutorialForm.get('category').value.trim();	  
	  if (this.tutorialIdToUpdate === null) {  
	    //Handle create tutorial
	    let tutorial= new Tutorial(null, title, category);	  
	    this.tutorialService.createTutorial(tutorial)
	      .subscribe(successCode => {
		              this.statusCode = successCode;
			      this.getAllTutorials();	
			      this.backToCreateTutorial();
			},
		        errorCode => this.statusCode = errorCode);
	  } else {  
   	    //Handle update tutorial
	    let tutorial= new Tutorial(this.tutorialIdToUpdate, title, category);	  
	    this.tutorialService.updateTutorial(tutorial)
	      .subscribe(successCode => {
		        this.statusCode = successCode;
			      this.getAllTutorials();	
			      this.backToCreateTutorial();
			},
		        errorCode => this.statusCode = errorCode);	  
	  }
   }
   //Load tutorial by id to edit
   loadTutorialToEdit(tutorialId: string) {
      this.preProcessConfigurations();
      this.tutorialService.getTutorialById(tutorialId)
	      .subscribe(tutorial => {
		            this.tutorialIdToUpdate = tutorial.tutorialId;   
		            this.tutorialForm.setValue({ title: tutorial.title, category: tutorial.category });
			    this.processValidation = true;
			    this.requestProcessing = false;   
		    },
		    errorCode =>  this.statusCode = errorCode);   
   }
   //Delete tutorial
   deleteTutorial(tutorialId: string) {
      this.preProcessConfigurations();
      this.tutorialService.deleteTutorialById(tutorialId)
	      .subscribe(successCode => {
		      this.statusCode = successCode;
		      this.getAllTutorials();	
		      this.backToCreateTutorial();
		   },
		   errorCode => this.statusCode = errorCode);    
   }
   //Perform preliminary processing configurations
   preProcessConfigurations() {
          this.statusCode = null;
	  this.requestProcessing = true;   
   }
   //Go back from update to create
   backToCreateTutorial() {
          this.tutorialIdToUpdate = null;
          this.tutorialForm.reset();	  
	  this.processValidation = false;
   }
} 