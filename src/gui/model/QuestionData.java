package gui.model;

/**
 * Represents a question in the GUI layer.
 * 
 * <p>This class stores the question's ID and title, providing a
 * simplified model optimized for display and user interaction
 * within GUI components.</p>
 * 
 * <p>The {@code toString()} method returns the question title,
 * making it suitable for use in list and combo box elements.</p>
 */
public class QuestionData {

	private int questionId;
	private String questionTitle;
	
	public QuestionData(int questionId, String questionTitle) {
		super();
		this.questionId = questionId;
		this.questionTitle = questionTitle;
	}
	
	//-1 is the ID of empty QuestionData. Must not be passed on to QuestionDTO and Service.
	public QuestionData() {
		this(-1, "");
	}
	
	//0 is the ID of new QuestionData. It can be passed on to QuestionDTO and Service.
	public QuestionData(String questionTitle) {
		this(0, questionTitle);
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

	@Override
	public String toString() {
		return questionTitle;
	}

}
