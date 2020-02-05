package ch.fhnw.webfr.flashcard.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="questionnaires")
public class Questionnaire {
    @Id
    private String id;
	
	@NotNull
    @Size(min=2, max=30)
    private String  title;
	
	@NotNull
    @Size(min=10, max=50)
    private String description;
    
}
