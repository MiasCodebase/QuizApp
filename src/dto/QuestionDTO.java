package dto;

/**
 * Represents a full quiz question, including its ID, title, content,
 * and a reference to the subject it belongs to.
 */
public class QuestionDTO {

	private int questionId;
	private String questionTitle;
	private String questionContent;
	private int fkSubjectId;

	
	public QuestionDTO(int questionId, String questionTitle, String questionContent, int fkSubjectId) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionContent = questionContent;
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


	public String getQuestionContent() {
		return questionContent;
	}


	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}


	public int getFkSubjectId() {
		return fkSubjectId;
	}


	public void setFkSubjectId(int fkSubjectId) {
		this.fkSubjectId = fkSubjectId;
	}



}
