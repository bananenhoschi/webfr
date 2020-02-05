package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
@Slf4j
public class BasicServlet extends HttpServlet {

    private static final String HTML_HEAD =
            "<html><head><title>{title}</title><style>body {font-family: \"Lucida Console\", Monaco, monospace;}</style></head><body>";
    private static final String HTML_BODY_END = "</body></html>";

    enum Directories {
        questionnaires,
        funny
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=utf-8");

        String[] pathElements = request.getPathInfo().split("/");
        Arrays.stream(pathElements).forEach(log::debug);

        if (pathElements.length > 0) {

            if (Arrays.stream(Directories.values()).anyMatch(d -> d.name().equals(pathElements[1]))) {

                switch (Directories.valueOf(pathElements[1])) {
                    case questionnaires:
                        Long id = pathElements.length == 3 ? Long.valueOf(pathElements[2]) : null;
                        // Path contains ID
                        if (id != null) {
                            handleSingleQuestionaire(id, request, response);
                        } else {
                            handleQuestionaires(request, response);
                        }
                        return;
                    case funny:
                        handleSmiley(request, response);
                        return;
                    default:
                        handleRoot(request, response);

                }
            } else {
                handelDefault(request, response);
            }
        } else {
            handleRoot(request, response);
        }

    }

    private void handelDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.append(HTML_HEAD);
        writer.append("<span style=\"color:red; font:bold;\">{pagenotfound}</span>");
        writer.append(HTML_BODY_END);
    }

    private void handleSmiley(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.append(HTML_HEAD);
        writer.append("<img src=\"https://media1.tenor.com/images/58f23c62733b75711785a822cdb052c6/tenor.gif\"/>");
        writer.append(HTML_BODY_END);
    }


    private void handleSingleQuestionaire(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionnaireRepository questionnaireRepository = (QuestionnaireRepository) request.getServletContext().getAttribute(QuestionnaireRepository.class.getName());
        PrintWriter writer = response.getWriter();
        writer.append(HTML_HEAD);
        try {
            Questionnaire questionnaire = questionnaireRepository.findById(id);
            writer.append(String.format("<h3>{questionnaire} %d</h3>", questionnaire.getId()));
            writer.append(String.format("<h4>%s</h4>", questionnaire.getTitle()));
            writer.append(String.format("%s", questionnaire.getDescription()));
        } catch (IndexOutOfBoundsException e) {
            writer.append("{noquestionnairefound}");
        }

        writer.append(HTML_BODY_END);
    }

    private void handleQuestionaires(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionnaireRepository questionnaireRepository = (QuestionnaireRepository) request.getServletContext().getAttribute(QuestionnaireRepository.class.getName());
        PrintWriter writer = response.getWriter();
        writer.append(HTML_HEAD);
        writer.append("<h3>{questionnaires}</h3>");
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        questionnaires.forEach(questionnaire -> {
            final String url = request.getContextPath() + request.getServletPath() + "/questionnaires/" + questionnaire.getId().toString();
            writer.append(String.format("<p><a href='%s'>%s</a></p>", response.encodeURL(url), questionnaire.getTitle()));
        });
        writer.append(HTML_BODY_END);
    }

    private void handleRoot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.append(HTML_HEAD);
        writer.append("<h3>{welcome}</h3>");
        String url = request.getContextPath() + request.getServletPath();
        writer.append(String.format("<p><a href='%s/questionnaires'>{allquestionnaires}</a></p>", response.encodeURL(url)));
        writer.append(String.format("<p><a href='%s/funny'>{fun}</a></p>", response.encodeURL(url)));
        writer.append(HTML_BODY_END);
    }

}
