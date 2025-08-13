package gui.model;

import java.util.List;
import java.util.Map;

import dto.AnswerDTO;
import dto.QuestionDTO;
import dto.QuestionLightDTO;
import dto.SubjectLightDTO;

/**
 * Holds session-level cached data and lookup maps for quiz questions
 * and related entities within the GUI layer.
 * 
 * <p>This class stores multiple maps that associate unique IDs (such as
 * question or subject IDs) with their corresponding lightweight or full
 * DTOs from the service layer, enabling efficient data lookup.</p>
 * 
 * <p>It acts as a centralized cache to avoid redundant service calls
 * and maintains state needed across multiple interactions, helping to
 * keep GUI panels simple and the controller lean.</p>
 * 
 * <p>The {@code QuizQuestionsInteractionHandler} controller is
 * responsible for populating and updating this data, while GUI panels
 * retrieve data indirectly via the controller.</p>
 */
public class SessionData {
	
	private Map<Integer, SubjectLightDTO> subjectLightMap;
	private Map<Integer, QuestionLightDTO> questionLightMap;
	private Map<Integer, QuestionDTO> questionMap;
	
	private Map<Integer, List<QuestionLightDTO>> questionLightBySubjectIdMap;
	private Map<Integer, List<AnswerDTO>> answerByQuestionIdMap;
	
	
	
	
	/**
	 * @return the subjectLightMap
	 */
	public Map<Integer, SubjectLightDTO> getSubjectLightMap() {
		return subjectLightMap;
	}
	
	/**
	 * @param subjectLightMap the subjectLightMap to set
	 */
	public void setSubjectLightMap(Map<Integer, SubjectLightDTO> subjectLightMap) {
		this.subjectLightMap = subjectLightMap;
	}
	
	/**
	 * @return the questionLightMap
	 */
	public Map<Integer, QuestionLightDTO> getQuestionLightMap() {
		return questionLightMap;
	}
	
	/**
	 * @param questionLightMap the questionLightMap to set
	 */
	public void setQuestionLightMap(Map<Integer, QuestionLightDTO> questionLightMap) {
		this.questionLightMap = questionLightMap;
	}

	/**
	 * @return the questionLightBySubjectIdMap
	 */
	public Map<Integer, List<QuestionLightDTO>> getQuestionLightBySubjectIdMap() {
		return questionLightBySubjectIdMap;
	}

	/**
	 * @param questionLightBySubjectIdMap the questionLightBySubjectIdMap to set
	 */
	public void setQuestionLightBySubjectIdMap(Map<Integer, List<QuestionLightDTO>> questionLightBySubjectIdMap) {
		this.questionLightBySubjectIdMap = questionLightBySubjectIdMap;
	}

	/**
	 * @return the questionMap
	 */
	public Map<Integer, QuestionDTO> getQuestionMap() {
		return questionMap;
	}

	/**
	 * @param questionMap the questionMap to set
	 */
	public void setQuestionMap(Map<Integer, QuestionDTO> questionMap) {
		this.questionMap = questionMap;
	}
	
	public void addToQuestionMap(Integer key, QuestionDTO questionDTO) {
		questionMap.put(key, questionDTO);
	}
	
	public void addToAnswerByQuestionIdMap(Map<Integer, List<AnswerDTO>>  answersMap) {
		answerByQuestionIdMap.putAll(answersMap);
	} 
	
	/**
	 * @return the answerByQuestionIdMap
	 */
	public Map<Integer, List<AnswerDTO>> getAnswerByQuestionIdMap() {
		return answerByQuestionIdMap;
	}

	/**
	 * @param answerByQuestionIdMap the answerByQuestionIdMap to set
	 */
	public void setAnswerByQuestionIdMap(Map<Integer, List<AnswerDTO>> answerByQuestionIdMap) {
		this.answerByQuestionIdMap = answerByQuestionIdMap;
	}
	
}