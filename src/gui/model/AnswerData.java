package gui.model;

/**
 * Represents a single answer in the GUI layer.
 * 
 * <p>This class holds minimal data needed for display and interaction:
 * the answer's ID, the textual content, and whether the answer is valid.
 * It serves as a lightweight model tailored for use in GUI components,
 * distinct from the service layer DTO.</p>
 * 
 * <p>The {@code toString()} method returns the answer content, 
 * enabling convenient display in list components.</p>
 */
public class AnswerData {

	private int answerId;
	private String answerContent;
	private boolean valid;
	
	public AnswerData(int answerId, String answerContent, boolean valid) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.valid = valid;
	}

	public AnswerData() {
		this(-1, "", false );
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
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return answerContent;
	}


}
