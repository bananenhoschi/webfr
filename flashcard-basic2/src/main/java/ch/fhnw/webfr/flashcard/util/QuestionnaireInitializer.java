package ch.fhnw.webfr.flashcard.util;

import lombok.extern.slf4j.Slf4j;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Slf4j
public class QuestionnaireInitializer {
	
	public QuestionnaireRepository initRepoWithTestData() {
		QuestionnaireRepository repo = new QuestionnaireRepository();
		repo.save(new Questionnaire("Test {questionnaire} 1", "Lorem ipsum dolor sit amet..."));
		repo.save(new Questionnaire("Test {questionnaire} 2", "Lorem ipsum dolor sit amet..."));
		repo.save(new Questionnaire("i18n Test {five}", "Lorem ipsum dolor sit amet..."));
		
		log.debug("Questionnaires initialized successfully");
		
		return repo;
	}

}
