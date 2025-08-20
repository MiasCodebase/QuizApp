package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import common.OperationResult;


/**
 * Rich domain entity representing an answer to a quiz question.
 * 
 * <p>Contains the answer ID, answer text, correctness flag, and a reference to its parent
 * {@link Question}. Enforces programming invariants (non-negative ID, non-null question)
 * and validates user input (non-empty content).</p>
 */
public class Answer {

	
	private static final String DEV_ERROR_ANSWER_ID = 
		"Answer ID is negative, which should never happen.";
	private static final String DEV_ERROR_NULL_QUESTION = 
		"Answer must be associated with a non-null Question.";
	private static final String USER_ERROR_INVALID_CONTENT = 
		"Bitte geben Sie den Text für die Antwort ein!";
	
	private int answerId;
	private String answerContent;
	private boolean correct;
	private Question question;
	
	
    /**
     * Constructs a new Answer with the given parameters.
     * 
     * @param answerId the ID of the answer (0 for new answers)
     * @param answerContent the text of the answer
     * @param correct whether the answer is correct
     * @param question the parent Question; must not be null
     */
	public Answer(int answerId, String answerContent, boolean correct, Question question) {
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.correct = correct;
		this.question = question;
	}

	
    /**
     * Validates the Answer entity.
     * 
     * <p>Checks programming invariants and user input:</p>
     * <ul>
     *     <li>Programming invariants: {@code answerId >= 0} and non-null {@code question}.
     *         Throws {@link IllegalStateException} if violated.</li>
     *     <li>User input: {@code answerContent} must not be null or blank. Collected in
     *         {@link OperationResult}.</li>
     * </ul>
     *
     * @return {@link OperationResult}&lt;Void&gt; success if valid, failure with error messages otherwise
     */	 
	 public OperationResult<Void> validate() {		
		assertValidAnswerID();		
		assertValidQuestion();
		
		List<String> errors = new ArrayList<>();
		validateAnswerContent().ifPresent(errors::add);
		return errors.isEmpty() ? OperationResult.success() : OperationResult.failure(errors);
	}

	 /**
	  * Ensures the answer ID is valid.
	  * 
	  * <p>Answer ID can be 0 for new answers that have not yet been persisted,
	  * but it must never be negative. Throws {@link IllegalStateException} if violated.</p>
	  */
	private void assertValidAnswerID() {
	    if (answerId < 0) {
	        throw new IllegalStateException(DEV_ERROR_ANSWER_ID);
	    }
	}
	

	/**
	 * Ensures the answer is associated with a valid question.
	 * 
	 * <p>Throws {@link IllegalStateException} if {@code question} is null.</p>
	 */
	private void assertValidQuestion() {
	    if (question == null) {
	        throw new IllegalStateException(DEV_ERROR_NULL_QUESTION);
	    }
	}

	
	/**
	 * Validates the answer content.
	 * 
	 * <p>Returns an {@link Optional} containing an error message if the content
	 * is null or blank, otherwise returns {@link Optional#empty()}.</p>
	 */
	private Optional<String> validateAnswerContent() {
		if (answerContent == null || answerContent.isBlank()) {
			return Optional.of(USER_ERROR_INVALID_CONTENT);
		}
		return Optional.empty();
	}


	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}


	/**
	 * @return the answerContent
	 */
	public String getAnswerContent() {
		return answerContent;
	}


	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}
	
	
	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}


	
}
