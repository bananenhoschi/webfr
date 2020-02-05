package ch.fhnw.webfr.flashcard.web;


import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/questionnaires")
@RequiredArgsConstructor
@Slf4j
public class QuestionnaireController {

    private final QuestionnaireRepository questionnaireRepository;


    @GetMapping
    public ResponseEntity<List<Questionnaire>> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        return new ResponseEntity<>(questionnaireRepository.findAll(sort), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        log.info("Find by id {}", id);
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        if (questionnaire.isPresent()) {
            log.info("Find a Questionnaire for id {}", id);
            return new ResponseEntity(questionnaire.get(), HttpStatus.OK);
        }
        log.error("No Questionnaire found for id {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Questionnaire> create(@Valid @RequestBody Questionnaire questionnaire, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        questionnaire = questionnaireRepository.save(questionnaire);
        log.debug("Created questionnaire with id=" + questionnaire.getId());
        return new ResponseEntity<>(questionnaire, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Questionnaire> update(@PathVariable String id,
                                                @Valid @RequestBody Questionnaire questionnaire, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        Optional<Questionnaire> questionnaireOptional = questionnaireRepository.findById(id);
        if (questionnaireOptional.isPresent()) {
            Questionnaire updateQuestionnaire = questionnaireOptional.get();
            if (questionnaire.getTitle() != null) {
                updateQuestionnaire.setTitle(questionnaire.getTitle());
            }
            if (questionnaire.getDescription() != null) {
                updateQuestionnaire.setDescription(questionnaire.getDescription());
            }
            questionnaire = questionnaireRepository.save(updateQuestionnaire);
            log.debug("Updated questionnaire with id=" + updateQuestionnaire.getId());
            return new ResponseEntity<Questionnaire>(questionnaire, HttpStatus.OK);
        }
        log.debug("No questionnaire with id=" + id + " found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        Optional<Questionnaire> questionnaireOptional = questionnaireRepository.findById(id);
        if (questionnaireOptional.isPresent()) {
            questionnaireRepository.deleteById(id);
            log.debug("Deleted questionnaire with id=" + id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.debug("No questionnaire with id=" + id + " found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
