package com.tutorial.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tutorial.entity.Tutorial;
@Transactional
@Repository
public class TutorialDAO implements ITutorialDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Tutorial getTutorialById(int tutorialId) {
		return entityManager.find(Tutorial.class, tutorialId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tutorial> getAllTutorials() {
		String hql = "FROM Tutorial as atcl ORDER BY atcl.tutorialId DESC";
		return (List<Tutorial>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createTutorial(Tutorial tutorial) {
		entityManager.persist(tutorial);
	}
	@Override
	public void updateTutorial(Tutorial tutorial) {
		Tutorial artcl = getTutorialById(tutorial.getTutorialId());
		artcl.setTitle(tutorial.getTitle());
		artcl.setCategory(tutorial.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteTutorial(int tutorialId) {
		entityManager.remove(getTutorialById(tutorialId));
	}
	@Override
	public boolean tutorialExists(String title, String category) {
		String hql = "FROM Tutorial as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
} 