package com.tutorial.service;

import java.util.List;

import com.tutorial.entity.Tutorial;

public interface ITutorialService {
	
	List<Tutorial> getAllTutorials();
    Tutorial getTutorialById(int tutorialId);
    boolean createTutorial(Tutorial tutorial);
    void updateTutorial(Tutorial tutorial);
    void deleteTutorial(int tutorialId);
}
