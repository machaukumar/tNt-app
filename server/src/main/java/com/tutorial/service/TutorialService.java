package com.tutorial.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tutorial.dao.ITutorialDAO;
import com.tutorial.entity.Tutorial;
@Service
public class TutorialService implements ITutorialService {
	@Autowired
	private ITutorialDAO tutorialDAO;
	@Override
	public Tutorial getTutorialById(int tutorialId) {
		Tutorial obj = tutorialDAO.getTutorialById(tutorialId);
		return obj;
	}	
	@Override
	public List<Tutorial> getAllTutorials(){
		return tutorialDAO.getAllTutorials();
	}
	@Override
	public synchronized boolean createTutorial(Tutorial tutorial){
               if (tutorialDAO.tutorialExists(tutorial.getTitle(), tutorial.getCategory())) {
    	           return false;
               } else {
    	           tutorialDAO.createTutorial(tutorial);
    	           return true;
               }
	}
	@Override
	public void updateTutorial(Tutorial tutorial) {
		tutorialDAO.updateTutorial(tutorial);
	}
	@Override
	public void deleteTutorial(int tutorialId) {
		tutorialDAO.deleteTutorial(tutorialId);
	}
} 