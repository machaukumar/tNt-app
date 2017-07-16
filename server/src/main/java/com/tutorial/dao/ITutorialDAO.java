package com.tutorial.dao;

import java.util.List;

import com.tutorial.entity.Tutorial;

public interface ITutorialDAO {
	
	List<Tutorial> getAllTutorials();
    Tutorial getTutorialById(int tutorialId);
    void createTutorial(Tutorial tutorial);
    void updateTutorial(Tutorial tutorial);
    void deleteTutorial(int tutorialId);
    boolean tutorialExists(String title, String category);
}
