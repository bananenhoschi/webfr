package ch.fhnw.webfr.flashcard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "questionnaires")
@Data
@NoArgsConstructor
public class Questionnaire {

    @Id
    private String id;
    @NotNull
    @Size(min = 2, max = 20)
    private String title;
    @NotNull
    @Size(min = 10)
    private String description;

    public Questionnaire(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String toString() {
        return "[" + id + "; " + title + "; " + description + "]";
    }

}
