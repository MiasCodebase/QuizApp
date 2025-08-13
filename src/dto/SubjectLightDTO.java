package dto;

/**
 * A lightweight representation of a quiz subject with only its ID
 * and title, optimized for quick loading in UI components.
 */
public class SubjectLightDTO {

	private int subjectId;
	private String subjectTitle;
	
	public SubjectLightDTO(int subjectId, String subjectTitle) {
		super();
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

}
