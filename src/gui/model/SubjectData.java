package gui.model;

/**
 * Represents a subject in the GUI layer.
 * 
 * <p>This class holds the subject's ID and title, serving as a
 * lightweight model designed for presentation and interaction
 * in GUI components.</p>
 * 
 * <p>The {@code toString()} method returns the subject title,
 * facilitating display in lists and combo boxes.</p>
 */
public class SubjectData {

	private int subjectId;
	private String subjectTitle;
	
	public SubjectData(int subjectId, String subjectTitle) {
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

	@Override
	public String toString() {
		return subjectTitle;
	}
}
