package gui.quizQuestions;


import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.model.SessionData;
import serviceLayer.FakeQuizService;
import serviceLayer.QuizService;

/**
 * Main application window (JFrame) for the QuizQuestions tab.
 * <p>
 * Serves as the entry point of the application and is responsible for
 * creating the main frame, setting its size and position, and displaying
 * the MainQuizQuestionsView panel.
 * </p>
 * <p>
 * Dependencies such as UI panels and the service layer are injected externally
 * to promote loose coupling and easier testing.
 * </p>
 */

public class FrameQuizQuestions extends JFrame {

	

	private static final long serialVersionUID = 1L;
	private static final int FRAME_X = 400;
	private static final int FRAME_Y = 100;		
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 730;
	
	
	public FrameQuizQuestions(JPanel panel) throws HeadlessException {
		
		super("QuizApp");		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		setContentPane(panel);
		setVisible(true);
	}

	// manually injecting quizService and Views inside the panel, instead of creating
	// them inside the class for more decoupling and testability.
	public static void main(String[] args) {
		
		QuizService quizService = new FakeQuizService();
		QuizQuestionsPanelR panelR = new QuizQuestionsPanelR();
		QuizQuestionsPanelL panelL = new QuizQuestionsPanelL();
		MainQuizQuestionsView mainQuizQuestionView = new MainQuizQuestionsView(panelL, panelR);
		SessionData sessionData = new SessionData();
		QuizQuestionsInteractionHandler qQInteractionHandler = 
				new QuizQuestionsInteractionHandler(quizService, sessionData, panelL, panelR);
		qQInteractionHandler.initializeView();
		new FrameQuizQuestions(mainQuizQuestionView);	

	}

}
