package dto;

/**
 * Aggregates a full QuestionDTO and a list of its associated AnswerDTOs.
 * Used when a user selects a question to view or edit its answers.
 */
import java.util.List;

public class QuestionAndAnswersDTO {

	private QuestionDTO question;
	private List<AnswerDTO> answersList;
	public QuestionAndAnswersDTO(QuestionDTO question, List<AnswerDTO> answersList) {
		super();
		this.question = question;
		this.answersList = answersList;
	}
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}
	public List<AnswerDTO> getAnswersList() {
		return answersList;
	}
	public void setAnswersList(List<AnswerDTO> answersList) {
		this.answersList = answersList;
	}

}
