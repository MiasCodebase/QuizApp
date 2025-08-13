package serviceLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dto.AnswerDTO;
import dto.QuestionDTO;
import dto.QuestionAndAnswersDTO;
import dto.QuestionLightDTO;
import dto.QuizQuestionsInitDTO;
import dto.SubjectLightDTO;

/**
 * A fake implementation of {@link QuizService} that generates
 * sample data for testing and presentation purposes.
 * <p>
 * This service creates 20 subjects, 100 questions (5 per subject),
 * and 4 answers per question with random validity flags.
 * </p>
 * <p>
 * It allows UI and other layers to be tested independently from
 * any real backend or database.
 * </p>
 */
public class FakeQuizService implements QuizService{

	public FakeQuizService() {
		// TODO Auto-generated constructor stub
			
	}

	public QuizQuestionsInitDTO getQuizQuestionsinitData() {		
		List<SubjectLightDTO> subjectList = generateSubjectLightList();
		List<QuestionLightDTO> questionList = generateQuestionLightList();
				
		QuizQuestionsInitDTO initDTO = new QuizQuestionsInitDTO(subjectList, questionList);
		return initDTO;
	}

	/**
	 * Generates a list of 100 fake questions (5 per subject) with IDs and titles.
	 * 
	 * @return a list of {@link dto.QuestionLightDTO} representing lightweight question data
	 */
	private List<QuestionLightDTO> generateQuestionLightList() {
		
		List<QuestionLightDTO> questionList = new ArrayList<>();
		
		for (int i = 1, refSubjectId = 1; i <= 100; i++) {			
			String title = "Frage " + i;				
			QuestionLightDTO question = new QuestionLightDTO(i, title, refSubjectId);
			if (i % 5 == 0) {refSubjectId ++;}
			questionList.add(question);	
		}
		return questionList;
	}

	/**
	 * Generates a list of 20 fake subjects with IDs and titles.
	 * 
	 * @return a list of {@link dto.SubjectLightDTO} representing lightweight subject data
	 */
	private List<SubjectLightDTO> generateSubjectLightList() {
		
		List<SubjectLightDTO> subjectList = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			String title = "Thema " + i;
			SubjectLightDTO subject = new SubjectLightDTO(i, title);
			subjectList.add(subject);	
		}
		return subjectList;
	}
//
//	public QuizQuestionsInitDTO getQuestionListForQuizQuestions() {		
//
//		return null;
//	}

	//TODO have it return a random question when there wasn't one specified and
	//the particular question when its specified.
	public QuestionAndAnswersDTO getQuestionAndAnswersBySubject(int subjectId) {		

		return null;
	}
	
	/**
	 * Retrieves a {@link dto.QuestionAndAnswersDTO} containing
	 * a fake question and its answers by the given question ID.
	 * 
	 * @param questionId the ID of the question to retrieve
	 * @return a {@link dto.QuestionAndAnswersDTO} with generated question and answers
	 */
	public QuestionAndAnswersDTO getQuestionAndAnswersById(int questionId) {
		QuestionDTO question = generateQuestion(questionId);
		List<AnswerDTO> answersList = generateAnswersList(questionId);
		
		QuestionAndAnswersDTO qAndA = new QuestionAndAnswersDTO(question, answersList);
		return qAndA;
	}

	/**
	 * Generates a fake question DTO based on the given question ID.
	 * 
	 * @param questionId the question ID to generate data for
	 * @return a {@link dto.QuestionDTO} with test title, content, and subject reference
	 */
	private QuestionDTO generateQuestion(int questionId) {
				
		String questionTitle = "Frage " + questionId;
		String questionContent = "Testinhalt" + questionId;
		int fkSubjectId = (int)questionId/5 + 1 ; 
		QuestionDTO question = new QuestionDTO(questionId, questionTitle 
			,questionContent, fkSubjectId);
		return question;		
	}

	/**
	 * Generates a list of four fake answers for a given question ID.
	 * One answer is guaranteed to be valid; others are randomly marked valid or invalid.
	 * 
	 * @param questionId the ID of the question to generate answers for
	 * @return a list of {@link dto.AnswerDTO} representing fake answers
	 */
	private List<AnswerDTO> generateAnswersList(int questionId) {
		
		List<AnswerDTO> answersList = new ArrayList<>();
		int answerId = (questionId - 1) * 4;
		
		Random random = new Random();
		int definitelyvalidId = random.nextInt(4)+1;
		
		for(int i = 1; i <= 4; i++) {
			answerId ++ ;
			String answerContent = "Testinhalt " + answerId;						
			boolean valid = (i == definitelyvalidId) ? true : random.nextBoolean();
			
			AnswerDTO answer = new AnswerDTO(answerId, answerContent, valid, questionId);
			answersList.add(answer);
		}
		
		return answersList;
	}
	
}

