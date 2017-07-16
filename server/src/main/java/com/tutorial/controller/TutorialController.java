package com.tutorial.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import com.tutorial.entity.Tutorial;
import com.tutorial.service.ITutorialService;
@Controller
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TutorialController {
	@Autowired
	private ITutorialService tutorialService;
	@GetMapping("tutorial")
	public ResponseEntity<Tutorial> getTutorialById(@RequestParam("id") String id) {
		Tutorial tutorial = tutorialService.getTutorialById(Integer.parseInt(id));
		return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);
	}
	@GetMapping("all-tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		List<Tutorial> list = tutorialService.getAllTutorials();
		return new ResponseEntity<List<Tutorial>>(list, HttpStatus.OK);
	}
	@PostMapping("tutorial")
	public ResponseEntity<Void> createTutorial(@RequestBody Tutorial tutorial, UriComponentsBuilder builder) {
		boolean flag = tutorialService.createTutorial(tutorial);
		if (flag == false) {
		     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/tutorial?id={id}").buildAndExpand(tutorial.getTutorialId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("tutorial")
	public ResponseEntity<Tutorial> updateTutorial(@RequestBody Tutorial tutorial) {
		tutorialService.updateTutorial(tutorial);
		return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);
	}
	@DeleteMapping("tutorial")
	public ResponseEntity<Void> deleteTutorial(@RequestParam("id") String id) {
		tutorialService.deleteTutorial(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 