package gui.model;

/**
 * Represents a single answer in the GUI layer.
 * 
 * <p>This class holds minimal data needed for display and interaction:
 * the answer's ID, the textual content, and whether the answer is correct.
 * It serves as a lightweight model tailored for use in GUI components,
 * distinct from the service layer DTO.</p>
 * 
 * <p>The {@code toString()} method returns the answer content, 
 * enabling convenient display in list components.</p>
 */
public class AnswerData {

	private int answerId;
	private String answerContent;
	private boolean correct;
	
	public AnswerData(int answerId, String answerContent, boolean correct) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.correct = correct;
	}
	
	//-1 is the ID of empty AnswerData. Must not be passed on to AnswerDTO and Service.
	public AnswerData() {
		this(-1, "", false );
	} 

	//0 is the ID of a new AnswerData. It can be passed on to AnswerDTO and Service.
	public AnswerData(String answerContent, boolean correct) {
		this(0, answerContent, correct);
	} 
	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return the answerContent
	 */
	public String getAnswerContent() {
		return answerContent;
	}

	/**
	 * @param answerContent the answerContent to set
	 */
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @param correct the valid to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return answerContent;
	}


}
