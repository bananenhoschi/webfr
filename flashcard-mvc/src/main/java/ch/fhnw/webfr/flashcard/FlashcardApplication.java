package ch.fhnw.webfr.flashcard;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FlashcardApplication implements ApplicationRunner {


    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public static void main(String[] args) {
        SpringApplication.run(FlashcardApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        questionnaireRepository.deleteAll();
        questionnaireRepository.save(new Questionnaire("Wahlempfehlung", "Als Wählerin oder Wähler fragt man sich oft: Welche Politiker vertreten meine Meinung am besten? Die Antwort findet man – vielleicht – in Parteiprogrammen. Oder man füllt den Fragebogen von Smartvote aus und lässt sich anschliessend eine Auswahl an passenden Kandidierenden präsentieren."));
        questionnaireRepository.save(new Questionnaire("Anlageberatung", "Hier erfahren Sie, wie ein Beratungsgespräch mit dem Anlageberater Ihrer Bank, Ihrer Sparkasse oder Ihres Finanzdienstleisters ablaufen muss. Sie sollen einen Überblick darüber erhalten, welche Informationen Ihnen als Kundin oder Kunde zustehen und welche Angaben für die Beratung erforderlich sind, nach denen Sie der Anlageberater also fragen darf."));
        questionnaireRepository.save(new Questionnaire("eleval", "Bewertung des Moduls Webframeworks (webfr) für das Herbstsemester 2019."));

        log.debug("Questionnaires initialized successfully");
    }

}
