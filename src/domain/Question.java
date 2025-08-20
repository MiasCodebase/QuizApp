package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.OperationResult;


/**
 * Rich domain entity representing a quiz question.
 * 
 * <p>This class contains the core data of a question (ID, title, content), the associated 
 * {@link Subject}, and a list of {@link Answer} entities. It includes validation methods 
 * to ensure that the question has a valid ID, title, content, and subject before being used 
 * or persisted.</p>
 * 
 * <p>The answers list is initialized in the constructor and is never null.</p>
 */
public class Question {
	
	
	private static final String USER_ERROR_INVALID_SUBJECT = 
		"Bitte wählen Sie ein Thema aus!";
	private static final String USER_ERROR_INVALID_TITLE = 
		"Bitte geben Sie einen Titel für die Frage ein!";	
	private static final String USER_ERROR_INVALID_CONTENT = 
		"Bitte geben Sie den Text für die Frage ein!";
	private static final String DEV_ERROR_INVALID_ID = 
		"Question ID is negative, which should never happen.";
	
	
	private int questionId;
	private String questionTitle; 
	private String questionContent;
	private Subject subject;
	private List<Answer> answersList;
	
	
    /**
     * Full constructor including answers list.
     *
     * @param questionId ID of the question
     * @param questionTitle Title of the question
     * @param questionContent Content/text of the question
     * @param subject Associated subject
     * @param answersList List of answers for the question
     */public Question(int questionId, String questionTitle, String questionContent, Subject subject,
					List<Answer> answersList) {
		this.questionId = questionId;
		this.questionTitle = questionTitle;
		this.questionContent = questionContent;
		this.subject = subject;
		this.answersList = answersList;
	}
	
     
    /**
     * Constructor without answers list; initializes with empty list.
     *
     * @param questionId ID of the question
     * @param questionTitle Title of the question
     * @param questionContent Content/text of the question
     * @param subject Associated subject
     */
	public Question(int questionId, String questionTitle, String questionContent, Subject subject) {
		this(questionId, questionTitle, questionContent, subject, new ArrayList<>());
	}

	
    /**
     * Validates the question for correctness.
     * 
     * <p>Checks that the title, content, and subject are valid according to business rules.
     * Aggregates all validation errors and returns them in an {@link OperationResult}.</p>
     *
     * <p><b>Note:</b> The {@code questionId} is checked as a programming invariant 
     * (throws an exception if negative) but is not included in the returned
     * {@link OperationResult} errors list.</p>
     *
     * @return OperationResult<Void> success if valid, failure with error messages otherwise
     */
	public OperationResult<Void> validate() {		
		assertValidQuestionID();		
		List<String> errors = new ArrayList<>();
		validateQuestionTitle().ifPresent(errors::add);
		validateQuestionContent().ifPresent(errors::add);
		validateSubject().ifPresent(errors::add);
		return errors.isEmpty() ? OperationResult.success() : OperationResult.failure(errors);
	}
	
	

	/**
	 * Validates the {@code questionId} according to the following rules:
	 * <ul>
	 *     <li>{@code questionId == 0} → The question is new and has not been persisted yet (valid).</li>
	 *     <li>{@code questionId > 0} → The question already exists in the database (valid).</li>
	 *     <li>{@code questionId < 0} → Programming error; IDs must not be negative.</li>
	 * </ul>
	 *
	 * <p>This method is not included in normal user-facing validation because a negative
	 * question ID indicates a bug in the program, not invalid user input. 
	 * It can be used internally to assert object state.</p>
	 *
	 * @throws IllegalStateException if the {@code questionId} is negative
	 */
	private void assertValidQuestionID() {
	    if (questionId < 0) {
	        throw new IllegalStateException(DEV_ERROR_INVALID_ID);
	    }
	}

	private Optional<String> validateQuestionTitle() {
		if (questionTitle == null || questionTitle.isBlank()) {
			return Optional.of(USER_ERROR_INVALID_TITLE);
		}
		return Optional.empty();
	}

	
	private Optional<String> validateQuestionContent() {
		if (questionContent == null || questionContent.isBlank()) {
			return Optional.of(USER_ERROR_INVALID_CONTENT);
		}
		return Optional.empty();
	}

    /**
     * Validates the associated subject.
     * <p>Subject ID = -1 means all subjects selected; Subject ID = 0 is allowed for new subjects,
     * but not when here where we are adding a question to a subject.</p>
     *
     * @return Optional<String> error message if invalid, otherwise empty
     */
	private Optional<String> validateSubject() {
		if (subject == null || subject.getSubjectId() <= 0) {
			return Optional.of(USER_ERROR_INVALID_SUBJECT);
		}
		else {
			return Optional.empty();
		}
	}
	

	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the questionTitle
	 */
	public String getQuestionTitle() {
		return questionTitle;
	}

	/**
	 * @param questionTitle the questionTitle to set
	 */
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	/**
	 * @return the questionContent
	 */
	public String getQuestionContent() {
		return questionContent;
	}

	/**
	 * @param questionContent the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the answersList
	 */
	public List<Answer> getAnswersList() {
		return answersList;
	}

	/**
	 * @param answersList the answersList to set
	 */
	public void setAnswersList(List<Answer> answersList) {
		this.answersList = answersList;
	}
	
	

}
