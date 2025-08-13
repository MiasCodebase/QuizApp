package dto;

import java.util.List;

/**
 * Aggregates lists of SubjectLightDTOs and QuestionLightDTOs.
 * Provides the initial data to populate quiz panels, such as JComboBox
 * and JList UI components.
 */
public class QuizQuestionsInitDTO {
	
	private List<SubjectLightDTO> subjectList;
	private List<QuestionLightDTO> questionList;

	public QuizQuestionsInitDTO(List<SubjectLightDTO> subjectList, List<QuestionLightDTO> questionList) {
		super();
		this.subjectList = subjectList;
		this.questionList = questionList;
	}

	public List<SubjectLightDTO> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectLightDTO> subjectList) {
		this.subjectList = subjectList;
	}

	public List<QuestionLightDTO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionLightDTO> questionList) {
		this.questionList = questionList;
	}
	

}
