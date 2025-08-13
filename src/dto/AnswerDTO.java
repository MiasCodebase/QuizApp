package dto;

/**
 * Represents a single answer to a quiz question.
 * Contains the answer ID, answer text, correctness flag,
 * and a reference to the question it belongs to.
 */
public class AnswerDTO {

	private int answerId;
	private String answerContent;
	private boolean valid;
	private int fkQuestionId; //Reference to the Question it belongs to
	
	public AnswerDTO(int answerId, String answerContent, boolean valid, int fkQuestionId) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.valid = valid;
		this.fkQuestionId = fkQuestionId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getFkQuestionId() {
		return fkQuestionId;
	}

	public void setFkQuestionId(int fkQuestionId) {
		this.fkQuestionId = fkQuestionId;
	}

}
