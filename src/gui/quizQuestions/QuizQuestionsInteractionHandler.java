package gui.quizQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dto.AnswerDTO;
import dto.QuestionAndAnswersDTO;
import dto.QuestionDTO;
import dto.QuestionLightDTO;
import dto.QuizQuestionsInitDTO;
import dto.SubjectLightDTO;
import gui.model.AnswerData;
import gui.model.QuestionData;
import gui.model.SessionData;
import gui.model.SubjectData;
import serviceLayer.QuizService;

/**
 * Handles all user interactions for the quiz questions view, acting as the 
 * mediator between the GUI panels, SessionData, and service layer.
 * <p>
 * Listens to button clicks and item selections in both the left and right panels
 * of the quiz questions UI. Retrieves data from the service layer when necessary,
 * caches it in {@link SessionData}, and maps it to GUI-specific model classes 
 * ({@link QuestionData}, {@link AnswerData}, {@link SubjectData}) before passing 
 * it to the panels for display.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Initialize the view by fetching data from the service layer and 
 *       populating {@link SessionData} with initial mappings.</li>
 *   <li>Handle subject selection changes by clearing question/answer fields and 
 *       displaying either all questions or those filtered by the selected subject.</li>
 *   <li>Handle question selection changes by loading question details and answers 
 *       from cache or service layer, then updating the left panel accordingly.</li>
 *   <li>Maintain and update cached mappings of subjects, questions, and answers 
 *       in {@link SessionData} for efficient reuse.</li>
 * </ul>
 */
public class QuizQuestionsInteractionHandler {


	private QuizQuestionsPanelL quizQuestionsPanelL;	
	private QuizQuestionsPanelR quizQuestionsPanelR;
	private QuizService quizService;	
	private SessionData sessionData;

	
	public QuizQuestionsInteractionHandler(QuizService quizService,
			SessionData sessionData, QuizQuestionsPanelL quizQuestionsPanelL,
			QuizQuestionsPanelR quizQuestionsPanelR) {
		super();
		this.quizService = quizService;
		this.sessionData = sessionData;
		this.quizQuestionsPanelL = quizQuestionsPanelL;
		this.quizQuestionsPanelR = quizQuestionsPanelR;
	
		quizQuestionsPanelL.addDeleteQuestionListener(null);
		quizQuestionsPanelL.addSaveQuestionListener(null);
		
		quizQuestionsPanelR.addShowSubjectListener(null);
		quizQuestionsPanelR.addNewQuestionListener(null);
		quizQuestionsPanelR.addChooseSubjectListener(e -> onSubjectSelected());
		quizQuestionsPanelR.addChooseQuestionListener(e -> onQuestionSelected());
				
	}
	
	/**
	 * Handles changes in the selected question from the right panel's question list.
	 * Retrieves the selected question's details and answers from the cache if available,
	 * or from the service layer if not cached, then updates the left panel with this data.
	 */
	private void onQuestionSelected() {
		// TODO working on it!
		QuestionData selectedQuestion = 
			quizQuestionsPanelR.getQuestionsJList().getSelectedValue();
		if (selectedQuestion == null) return;
		int questionId = selectedQuestion.getQuestionId();
		
		if (!sessionData.getQuestionMap().containsKey(questionId)) {
			QuestionAndAnswersDTO questionAndAnswersDTO = 
					quizService.getQuestionAndAnswersById(questionId);
			setQuestionAndAnswerMaps(questionAndAnswersDTO);
		}
		
		QuestionDTO question = getQuestion(questionId);		
		quizQuestionsPanelL.setQuestionTitle(question.getQuestionTitle());
		quizQuestionsPanelL.setQuestionAreaContent(question.getQuestionContent());
		
		List<AnswerData> answersList = getAnswer(questionId);
		quizQuestionsPanelL.setAnswers(answersList);
		
	}

	/**
	 * Handles changes in the selected subject from the right panel's combo box.
	 * Clears the question and answer fields on the left panel and updates the question
	 * list either to show all questions (if "All Subjects" selected) or only those
	 * belonging to the chosen subject.
	 */
	private void onSubjectSelected() {
		SubjectData selectedSubject = 
			(SubjectData) quizQuestionsPanelR.getSubjectsComboBox().getSelectedItem();
		int subjectId = selectedSubject.getSubjectId();			
		quizQuestionsPanelL.clearQuestionAndAnswerFields();
		
		if (subjectId == -1) {
			quizQuestionsPanelR.setAllQuestions(getQuestionDataList());
			quizQuestionsPanelL.setSubject("Leer");
		} else { 
			
			quizQuestionsPanelL.setSubject(selectedSubject);
			
			List<QuestionLightDTO> questionDtoList = sessionData.getQuestionLightBySubjectIdMap()
					.get(subjectId);
			List<QuestionData>questionList = questionDtoList.stream()				
					.map(item -> new QuestionData(item.getQuestionId(), item.getQuestionTitle()))
					.collect(Collectors.toList());
			quizQuestionsPanelR.setAllQuestions(questionList);	
		}
	}

	/**
	 * Initializes the view by requesting initial quiz data from the service layer,
	 * populating the SessionData with this data, and updating the right panel's
	 * subject and question lists accordingly.
	 */
	public void initializeView() {
		QuizQuestionsInitDTO initDTO = quizService.getQuizQuestionsInitData();		
		setInitMaps(initDTO);
		
		List<SubjectData> subjectDataList = getSubjectDataList();
		quizQuestionsPanelR.setAllSubjects(subjectDataList);
		
		List<QuestionData> questionDataList = getQuestionDataList();
		quizQuestionsPanelR.setAllQuestions(questionDataList);	

	}

	private List<SubjectData> getSubjectDataList() {
		List<SubjectData> subjectDataList;
		
		List<SubjectLightDTO> subjectDTOList = new ArrayList<>(sessionData
			.getSubjectLightMap().values());
		
		subjectDataList = subjectDTOList.stream()
			.map(dto -> new SubjectData(dto.getSubjectId(), dto.getSubjectTitle()))
			.collect(Collectors.toList());
				
		return subjectDataList;
	}

	private List<QuestionData> getQuestionDataList() {
		List<QuestionData> questionDataList;
		
		List<QuestionLightDTO> questionDTOList = new ArrayList<>(sessionData
			.getQuestionLightMap().values());
		
		questionDataList = questionDTOList.stream()
			.map(dto -> new QuestionData(dto.getQuestionId(), dto.getQuestionTitle()))
			.collect(Collectors.toList());
				
		return questionDataList;
	}
	
	
	private QuestionDTO getQuestion(int questionId) {
		// TODO Decide:DTO or Gui Model?
		return sessionData.getQuestionMap().get(questionId);		
	}	

	private List<AnswerData> getAnswer(int questionId) {
	
		List<AnswerDTO> answerDTOList = 
			sessionData.getAnswerByQuestionIdMap().get(questionId);
		List<AnswerData> answers;
		answers = answerDTOList.stream()
			.map(dto -> new AnswerData(dto.getAnswerId(), dto.getAnswerContent(), dto.isCorrect()))
			.collect(Collectors.toList());
				
		return answers;
	}

	/**
	 * Converts and stores initial quiz data received from the service layer into
	 * SessionData maps for subjects, questions, and answers.
	 *
	 * @param initDTO Data transfer object containing initial lists of subjects and questions.
	 */
	private void setInitMaps(QuizQuestionsInitDTO initDTO) {
		
		Map<Integer, SubjectLightDTO> subjectLightMap = initDTO.getSubjectList().stream()
				.collect(Collectors.toMap(SubjectLightDTO::getSubjectId, sub -> sub));
			sessionData.setSubjectLightMap(subjectLightMap);
			
			Map<Integer, QuestionLightDTO> questionLightMap = initDTO.getQuestionList().stream()
				.collect(Collectors.toMap(QuestionLightDTO::getQuestionId, sub -> sub));
			sessionData.setQuestionLightMap(questionLightMap);	
		
			Map<Integer, List<QuestionLightDTO>> questionBySubjectIdMap = 
				initDTO.getQuestionList().stream()
				.collect(Collectors.groupingBy(QuestionLightDTO::getFkSubjectId));
			sessionData.setQuestionLightBySubjectIdMap(questionBySubjectIdMap);	
			
			Map<Integer, QuestionDTO> questionMap = new HashMap<>();
			sessionData.setQuestionMap(questionMap);
			
			Map<Integer, List<AnswerDTO>> answerByQuestionIdMap = new HashMap<>();
			sessionData.setAnswerByQuestionIdMap(answerByQuestionIdMap);	
	}
	
	/**
	 * Updates the SessionData with detailed data for a specific question and its answers,
	 * based on the received DTO from the service layer.
	 *
	 * @param questionAndAnswersDTO DTO containing a question and its associated answers.
	 */
	private void setQuestionAndAnswerMaps(QuestionAndAnswersDTO questionAndAnswersDTO) {

		Integer questionKey = questionAndAnswersDTO.getQuestion().getQuestionId();
		QuestionDTO questionDTO = questionAndAnswersDTO.getQuestion();
		sessionData.addToQuestionMap(questionKey, questionDTO);
		
		List<AnswerDTO> answersList = questionAndAnswersDTO.getAnswersList();
		Map<Integer, List<AnswerDTO>> answersMap = new HashMap<>();
		answersMap.put(questionKey, answersList);
		sessionData.addToAnswerByQuestionIdMap(answersMap);
	}
}
