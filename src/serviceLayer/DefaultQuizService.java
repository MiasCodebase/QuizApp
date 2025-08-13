package serviceLayer;

import dto.QuestionAndAnswersDTO;
import dto.QuizQuestionsInitDTO;

/**
 * Placeholder for the default (real) quiz service implementation.
 * <p>
 * Intended to be replaced with actual data access and business logic
 * in the future. Currently returns empty or null data.
 * </p>
 */
public class DefaultQuizService implements QuizService{

	public DefaultQuizService() {
		// TODO Auto-generated constructor stub
			
	}

	public QuizQuestionsInitDTO getQuizQuestionsinitData() {
		
		
		QuizQuestionsInitDTO initDTO = new QuizQuestionsInitDTO(null, null);
		return initDTO;
	}

	@Override
	public QuestionAndAnswersDTO getQuestionAndAnswersById(int questionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionAndAnswersDTO getQuestionAndAnswersBySubject(int subjectId) {
		// TODO Auto-generated method stub
		return null;
	}
}
