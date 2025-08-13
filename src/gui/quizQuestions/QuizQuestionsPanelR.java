package gui.quizQuestions;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import gui.GuiStyler;
import gui.model.QuestionData;
import gui.model.SubjectData;

/**
 * Right-side JPanel for the QuizQuestions tab UI.
 * <p>
 * Contains graphical components for browsing and selecting quiz content,
 * including a subject selection drop-down (JComboBox) and a question list (JList),
 * as well as controls for creating new questions or viewing subject details.
 * </p>
 * <p>
 * The panel provides methods to populate its lists with {@code SubjectData}
 * and {@code QuestionData} instances, and to register event listeners
 * for user actions such as selecting a subject, selecting a question,
 * or triggering button actions.
 * </p>
 * <p>
 * This class is purely responsible for the visual presentation and event
 * delegation; it contains no business logic.
 * </p>
 */
public class QuizQuestionsPanelR extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String QUESTION_TEXT = "Fragen zum Thema";
	private static final String NEW_QUESTION_BUTTON_TEXT = "Neue Frage";
	private static final String SHOW_SUBJECT_BUTTON_TEXT = "Thema anzeigen";
	
	JComboBox<SubjectData> subjectsComboBox;	
	JLabel questionsLabel;
	JButton showSubjectButton, newQuestionButton;
	JList<QuestionData> questionsJList; 


	
	public QuizQuestionsPanelR() {
		initializeGuiElements();
		setLayout();			
	}	

	/**
	 * Initializes all graphical components used in the right panel of the QuizQuestions view.
	 * <p>
	 * This includes creating labels, buttons, a combo box for subjects, and a list for questions.
	 * All components are styled using {@link GuiStyler} to ensure consistent appearance across the UI.
	 * Models for the combo box and question list are set up with empty, type-safe data containers.
	 * </p>
	 */
	private void initializeGuiElements() {
			
		questionsLabel = new JLabel();
		questionsLabel.setText(QUESTION_TEXT); 
		GuiStyler.setLabelStyle(questionsLabel, GuiStyler.LabelSize.MEDIUM);
		
		showSubjectButton = new JButton();
		showSubjectButton.setText(SHOW_SUBJECT_BUTTON_TEXT);
		GuiStyler.setDefaultButtonStyle(showSubjectButton);
		
		newQuestionButton = new JButton();	
		newQuestionButton.setText(NEW_QUESTION_BUTTON_TEXT);
		GuiStyler.setDefaultButtonStyle(newQuestionButton);
	
		DefaultComboBoxModel<SubjectData> comboBoxModel = 
			new DefaultComboBoxModel<>();
		subjectsComboBox = new JComboBox<SubjectData>(comboBoxModel);
		GuiStyler.setComboBoxStyle(subjectsComboBox);
		
		DefaultListModel<QuestionData> questionListModel =
			new DefaultListModel<QuestionData>();
		questionsJList = new JList<QuestionData>(questionListModel); 
		GuiStyler.setJListStyle(questionsJList);
	}
	
	/**
	 * Arranges the panel's components using a {@link GridBagLayout}.
	 */
	private void setLayout() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();			
		gbc.ipadx = 20;
		gbc.ipady = 20;
		gbc.insets = new Insets(10, 5, 10, 5);
		gbc.fill = GridBagConstraints.NONE;
		
		gbc.gridy = 0;
		gbc.gridx = 0;		
		gbc.gridwidth = 2;	
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		this.add(subjectsComboBox,gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 1;	
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		this.add(questionsLabel,gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.gridwidth = 1;	
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		JPanel wrapper = new JPanel();
		wrapper.add(showSubjectButton);
		this.add(wrapper,gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 2;	
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		questionsJList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JScrollPane scrollPane = new JScrollPane(questionsJList);
		scrollPane.setPreferredSize(new Dimension(380,385));
		scrollPane.setBorder(null);
		this.add(scrollPane,gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 1;
		gbc.gridwidth = 1;	
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		wrapper = new JPanel();
		wrapper.add(newQuestionButton);
		this.add(wrapper,gbc);
		
	}
	
	/**
	 * Registers a listener to be notified when the "Show Subject" button is clicked.
	 *
	 * @param listener the ActionListener to handle the button click
	 */
	public void addShowSubjectListener(ActionListener listener) {
		showSubjectButton.addActionListener(listener);
	}

	/**
	 * Registers a listener to be notified when the "New Question" button is clicked.
	 *
	 * @param listener the ActionListener to handle the button click
	 */
	public void addNewQuestionListener(ActionListener listener) {
		newQuestionButton.addActionListener(listener);
	}
	
	/**
	 * Registers a listener to be notified when a new subject is selected
	 * from the combo box.
	 *
	 * @param listener the ActionListener to handle subject selection changes
	 */
	public void addChooseSubjectListener(ActionListener listener) {
		subjectsComboBox.addActionListener(listener);
	}
	
	/**
	 * Registers a listener to be notified when a question is selected from the list.
	 * <p>
	 * The provided listener is only triggered after the selection is finalized
	 * (ignores intermediate adjusting events).
	 * </p>
	 *
	 * @param listener the ListSelectionListener to handle question selection
	 */
	public void addChooseQuestionListener(ListSelectionListener listener) {
		
		questionsJList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				listener.valueChanged(e);
			}
		});
	}
	
	
	/**
	 * Replaces all entries in the subject combo box with the provided list of subjects.
	 * <p>
	 * Adds an extra synthetic entry representing "All Subjects" with an ID of {@code -1}
	 * before appending the given subjects. Uses a new {@link DefaultComboBoxModel}
	 * for each call to avoid residual state and potential data inconsistencies.
	 * </p>
	 *
	 * @param subjectDataList the subjects to display in the combo box
	 */
	public void setAllSubjects(List<SubjectData> subjectDataList) {
		
		DefaultComboBoxModel<SubjectData> comboBoxModel = new DefaultComboBoxModel<>();
		SubjectData allSubjects = new SubjectData(-1, "Alle Themen");			
		comboBoxModel.addElement(allSubjects);
		comboBoxModel.addAll(subjectDataList);		
		subjectsComboBox.setModel(comboBoxModel);						
	} 
	 
	/**
	 * Replaces all entries in the question list with the provided list of questions.
	 * <p>
	 * Uses a new {@link DefaultListModel} for each call to ensure a clean data model
	 * and avoid leftover items from previous updates.
	 * </p>
	 *
	 * @param questionDataList the questions to display in the list
	 */
	public void setAllQuestions(List<QuestionData> questionDataList) {
	 
		DefaultListModel<QuestionData> questionListModel = new DefaultListModel<>();			
		questionListModel.addAll(questionDataList);
		questionsJList.setModel(questionListModel);
	}
	// If i want to add items individually I need to check if the model is empty.
	//Interestingly when we create a ComboBox a DefaultComboBoxModel is created
	//so it's no use to check if it's null. I need the cast because .getModel() 
	//returns only the comboBoxModel(which is the interface).
	//TODO: should i check for duplicate subject key?
	//TODO: is it better to load the whole models rather than manually add and removing?
//	public void addSubject(SubjectData subjectData) {
//		
//		DefaultComboBoxModel<SubjectData> comboBoxModel = 
//			(DefaultComboBoxModel<SubjectData>) subjectsComboBox.getModel();
//		
//		if (comboBoxModel.getSize() == 0) {
//			comboBoxModel = new DefaultComboBoxModel<>();
//			SubjectData allSubjects = new SubjectData(-1, "Alle Themen");			
//			comboBoxModel.addElement(allSubjects);
//		}		
//		comboBoxModel.addElement(subjectData);						
//	}
//	
//	public void addQuestion(QuestionData questionData) {
//
//		DefaultListModel<QuestionData> questionListModel =
//			(DefaultListModel<QuestionData>) questionsJList.getModel();			
//		questionListModel.addElement(questionData);	
//	}


	/**
	 * @return the subjectsComboBox
	 */
	public JComboBox<SubjectData> getSubjectsComboBox() {
		return subjectsComboBox;
	}


	/**
	 * @return the questionsJList
	 */
	public JList<QuestionData> getQuestionsJList() {
		return questionsJList;
	}

	
}