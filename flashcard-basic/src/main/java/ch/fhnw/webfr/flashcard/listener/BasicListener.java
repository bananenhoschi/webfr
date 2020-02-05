package ch.fhnw.webfr.flashcard.listener;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import ch.fhnw.webfr.flashcard.util.QuestionnaireInitializer;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class BasicListener implements ServletContextListener {


    enum Mode {
        test,
        prod
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext ctx = sce.getServletContext();
        Object attribute = ctx.getInitParameter("mode");
        try {
            Mode mode = Mode.valueOf(String.valueOf(attribute));
            log.info("Mode: {}", mode.name());
            switch (mode) {
                case test:
                    QuestionnaireRepository questionnaireRepository = new QuestionnaireInitializer().initRepoWithTestData();
                    ctx.setAttribute(QuestionnaireRepository.class.getName(), questionnaireRepository);
                    log.info("Test Repository initialized and set.");
                    break;
                case prod:
                    // init prod repo
                    break;
                default:
                    log.info("No mode set.");
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid mode {}", attribute);
        }


    }
}
