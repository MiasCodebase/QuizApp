package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.OperationResult;

/**
 * Rich domain entity representing a quiz subject.
 * <p>
 * Contains the core data of a subject (ID, title, info) and a list of associated 
 * Question entities. Provides validation methods for service layer usage.
 * The {@code questionsList} is always initialized and never null.
 * </p>
 */
public class Subject {	

	
	// error message constants
	private static final String USER_ERROR_INVALID_TITLE = 
		"Bitte geben Sie einen Titel für das Thema ein!";
	
	private static final String USER_ERROR_INVALID_INFO =
		"Bitte geben Sie eine kurze Beschreibung für das Thema ein!";
	
	
	private int subjectId;
	private String subjectTitle; 
	private String subjectInfo;
	private List<Question> questionsList;
	
	
	/**
     * Full constructor for Subject.
     *
     * @param subjectId      ID of the subject
     * @param subjectTitle   Title of the subject
     * @param subjectInfo    Description
     * @param questionsList  List of associated questions (can be empty but not null)
     */
	public Subject(int subjectId, String subjectTitle, String subjectInfo, List<Question> questionsList) {
		super();
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.subjectInfo = subjectInfo;
		this.questionsList = questionsList;
	}
	
	
	/**
     * Constructor for a new subject with an empty question list.
     *
     * @param subjectId    ID of the subject
     * @param subjectTitle Title of the subject
     * @param subjectInfo  Short description
     */
	public Subject(int subjectId, String subjectTitle, String subjectInfo) {		
		this(subjectId, subjectTitle, subjectInfo, new ArrayList<>());			
	}

	
	/**
     * Validates the subject fields and returns an OperationResult containing 
     * any error messages found.
     *
     * @return OperationResult<Void> containing all validation errors, or success if none
     */	
	public OperationResult<Void> validate() {
		List<String> errors = new ArrayList<>();
		validateSubjectTitle().ifPresent(errors::add);
		validateSubjectInfo().ifPresent(errors::add);
		return errors.isEmpty() ? OperationResult.success() : OperationResult.failure(errors);
	}
	
	
    /**
     * Validates subjectTitle.
     *
     * @return Optional<String> error message if invalid, otherwise empty
     */
	private Optional<String> validateSubjectTitle() {
		if (subjectTitle == null || subjectTitle.isBlank()) {
			return Optional.of(USER_ERROR_INVALID_TITLE);
		}
		return Optional.empty();
	}
	
	
	/**
     * Validates subjectInfo.
     *
     * @return Optional<String> error message if invalid, otherwise empty
     */
	private Optional<String> validateSubjectInfo() {
		if (subjectInfo == null || subjectInfo.isBlank()) {
			return Optional.of(USER_ERROR_INVALID_INFO);
		}
		return Optional.empty();
	}
	
	
	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the subjectTitle
	 */
	public String getSubjectTitle() {
		return subjectTitle;
	}

	/**
	 * @param subjectTitle the subjectTitle to set
	 */
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	/**
	 * @return the subjectInfo
	 */
	public String getSubjectInfo() {
		return subjectInfo;
	}

	/**
	 * @param subjectInfo the subjectInfo to set
	 */
	public void setSubjectInfo(String subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	/**
	 * @return the questionsList
	 */
	public List<Question> getQuestionsList() {
		return questionsList;
	}

	/**
	 * @param questionsList the questionsList to set
	 */
	public void setQuestionsList(List<Question> questionsList) {
		this.questionsList = questionsList;
	}

	
}
