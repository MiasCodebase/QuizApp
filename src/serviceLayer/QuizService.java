package serviceLayer;

import common.OperationResult;
import dto.QuestionAndAnswersDTO;
import dto.QuizQuestionsInitDTO;

/**
 * Interface defining the contract for quiz-related service operations.
 * <p>
 * Provides methods to retrieve initial quiz data and detailed question
 * and answer data by question or subject.
 * </p>
 * <p>
 * This abstraction allows switching between fake and real service implementations
 * without having to change code other than where the dependency Injection happens.
 * </p>
 */
public interface QuizService {

	//TODO: At the moment I just added the real methods as final top not break functionality.
	// the old methods must be later deleted and the new ones renamed and the default implementation
	// must be removed too!
	
	/**
     * Retrieves the initial data needed to populate the quiz view,
     * including lists of subjects and questions.
     * 
     * @return a {@link dto.QuizQuestionsInitDTO} containing the initial quiz data
     */
	QuizQuestionsInitDTO getQuizQuestionsInitData();	
	
	default OperationResult<QuizQuestionsInitDTO> getQuizQuestionsInitDataFinal() {
		return null;
	}
	 
	
	/**
     * Retrieves detailed question and associated answers by question ID.
     * 
     * @param questionId the unique ID of the question
     * @return a {@link dto.QuestionAndAnswersDTO} containing question and answers
     */
	QuestionAndAnswersDTO getQuestionAndAnswersById(int questionId);
	
	default OperationResult<QuestionAndAnswersDTO> getQuestionAndAnswersByIdFinal(int questionId) {
		return null;
	}
	
	
    /**
     * Retrieves detailed question and associated answers by subject ID.
     * Intended to provide data for questions belonging to a specific subject.
     * 
     * @param subjectId the unique ID of the subject
     * @return a {@link dto.QuestionAndAnswersDTO} containing question and answers
     */
	QuestionAndAnswersDTO getQuestionAndAnswersBySubject(int subjectId);
	
	default OperationResult<QuestionAndAnswersDTO> getQuestionAndAnswersBySubjectFinal(int subjectId) {
		return null;
	}
}
