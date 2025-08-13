package gui.quizQuestions;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * Container JPanel that visually combines the left and right quiz question panels.
 * <p>
 * Holds the left (QuizQuestionsPanelL) and right (QuizQuestionsPanelR) panels
 * and arranges them side by side using a FlowLayout.
 * </p>
 * <p>
 * This class contains no business logic and is solely responsible for the layout
 * of the contained panels.
 * </p>
 */
public class MainQuizQuestionsView extends JPanel{


	private static final long serialVersionUID = 1L;
	private QuizQuestionsPanelL panelL;
	private QuizQuestionsPanelR panelR;
	

	public MainQuizQuestionsView(QuizQuestionsPanelL panelL, QuizQuestionsPanelR panelR) {
		this.panelL = panelL;
		this.panelR = panelR;	
		setLayout();
	}


	private void setLayout() {
		this.setLayout(new FlowLayout());
		this.add(panelL);
		this.add(panelR);	
		
	}

}
