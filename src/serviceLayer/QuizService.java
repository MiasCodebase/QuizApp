package serviceLayer;

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


	/**
     * Retrieves the initial data needed to populate the quiz view,
     * including lists of subjects and questions.
     * 
     * @return a {@link dto.QuizQuestionsInitDTO} containing the initial quiz data
     */
	QuizQuestionsInitDTO getQuizQuestionsinitData();
	 
	/**
     * Retrieves detailed question and associated answers by question ID.
     * 
     * @param questionId the unique ID of the question
     * @return a {@link dto.QuestionAndAnswersDTO} containing question and answers
     */
	QuestionAndAnswersDTO getQuestionAndAnswersById(int questionId);

    /**
     * Retrieves detailed question and associated answers by subject ID.
     * Intended to provide data for questions belonging to a specific subject.
     * 
     * @param subjectId the unique ID of the subject
     * @return a {@link dto.QuestionAndAnswersDTO} containing question and answers
     */
	QuestionAndAnswersDTO getQuestionAndAnswersBySubject(int subjectId);
}
