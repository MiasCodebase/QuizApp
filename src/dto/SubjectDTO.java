package dto;

public class SubjectDTO {

	private int subjectId;
	private String subjectTitle; 
	private String subjectInfo;
	
	public SubjectDTO(int subjectId, String subjectTitle, String subjectInfo) {
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.subjectInfo = subjectInfo;
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
	
	

}
