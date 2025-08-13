package dto;

/**
 * A lightweight version of QuestionDTO containing only the ID,
 * title, and subject ID.
 */
public class QuestionLightDTO {

	private int questionId;
	private String questionTitle;
	private int fkSubjectId;
	
	public QuestionLightDTO(int questionId, String questionTitle, int fkSubjectId) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.fkSubjectId = fkSubjectId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public int getFkSubjectId() {
		return fkSubjectId;
	}

	public void setFkSubjectId(int fkSubjectId) {
		this.fkSubjectId = fkSubjectId;
	}
	


}
