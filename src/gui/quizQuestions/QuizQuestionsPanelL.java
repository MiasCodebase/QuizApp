package gui.quizQuestions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.GuiStyler;
import gui.model.AnswerData;
import gui.model.QuestionData;
import gui.model.SubjectData;

/**
 * Left-side JPanel for the QuizQuestions tab UI.
 * <p>
 * Contains graphical components related to displaying and editing
 * question details, including subject, title, question text, possible answers,
 * and controls for saving or deleting a question.
 * </p>
 * <p>
 * The panel provides methods to set and clear its fields and allows external
 * classes to register action listeners for its buttons.
 * </p>
 * <p>
 * This class focuses purely on the visual presentation and event delegation,
 * without embedding business logic.
 * </p>
 */

public class QuizQuestionsPanelL extends JPanel{


		private static final long serialVersionUID = 1L;
		static final private String SUBJECT_TEXT = "Thema";
		static final private String TITEL_TEXT = "Titel";
		static final private String QUESTION_TEXT = "Frage";
		static final private String POSSIBLE_ANSWERS_TEXT = "Mögliche Antworten";
		static final private String ANSWER1_TEXT = "Antwort 1";
		static final private String ANSWER2_TEXT = "Antwort 2";
		static final private String ANSWER3_TEXT = "Antwort 3";
		static final private String ANSWER4_TEXT = "Antwort 4";
		static final private String DELETE_QUESTION_BUTTON_TEXT = "Frage löschen";
		static final private String SAVE_QUESTION_BUTTON_TEXT = "speichern";

		
		JLabel subjectLabel, subjectValueLabel, titleLabel, questionLabel,  
		       possibleAnswersLabel, answer1Label, answer2Label, 
		       answer3Label, answer4Label;
		
		JTextField titleField, answer1Field, answer2Field, answer3Field, 
		           answer4Field;
		
		JTextArea questionArea, messages;
		
		JCheckBox checkbox1, checkbox2, checkbox3, checkbox4;
		
		JButton deleteQuestion, saveQuestion;
		
		
		
		public QuizQuestionsPanelL() {
			
			initializeGuiElements();
			setLayout();		
		}

		/**
		 * Initializes all GUI components like labels, text fields, checkboxes, and buttons,
		 * and applies styles to them.
		 */
		public void initializeGuiElements() {
			subjectLabel = new JLabel();
			subjectLabel.setText(SUBJECT_TEXT); 
			GuiStyler.setLabelStyle(subjectLabel, GuiStyler.LabelSize.LARGE);
			
			//TODO must change this later!
			subjectValueLabel = new JLabel();	
			setSubject("Leer");	
			GuiStyler.setLabelStyle(subjectValueLabel, GuiStyler.LabelSize.MEDIUM);
			
			
			
			titleLabel = new JLabel();
			titleLabel.setText(TITEL_TEXT); 
			GuiStyler.setLabelStyle(titleLabel, GuiStyler.LabelSize.MEDIUM);
			
			questionLabel = new JLabel();
			questionLabel.setText(QUESTION_TEXT);  
			GuiStyler.setLabelStyle(questionLabel, GuiStyler.LabelSize.MEDIUM);
			
			possibleAnswersLabel = new JLabel();
		    possibleAnswersLabel.setText(POSSIBLE_ANSWERS_TEXT);
		    GuiStyler.setLabelStyle(possibleAnswersLabel, GuiStyler.LabelSize.MEDIUM);
			
		    answer1Label = new JLabel();
		    answer1Label.setText(ANSWER1_TEXT);
		    GuiStyler.setLabelStyle(answer1Label, GuiStyler.LabelSize.SMALL);
			
		    answer2Label = new JLabel();
		    answer2Label.setText(ANSWER2_TEXT);
		    GuiStyler.setLabelStyle(answer2Label, GuiStyler.LabelSize.SMALL);
		    
		    answer3Label = new JLabel();
		    answer3Label.setText(ANSWER3_TEXT);
		    GuiStyler.setLabelStyle(answer3Label, GuiStyler.LabelSize.SMALL);
		    
		    answer4Label = new JLabel();
		    answer4Label.setText(ANSWER4_TEXT);
		    GuiStyler.setLabelStyle(answer4Label, GuiStyler.LabelSize.SMALL);
		    
		    
		    titleField = new JTextField();
		    GuiStyler.setDefaultFieldStyle(titleField, GuiStyler.FieldSize.LONG);
		    
		    answer1Field = new JTextField();
		    GuiStyler.setDefaultFieldStyle(answer1Field, GuiStyler.FieldSize.SHORT);
		    
		    answer2Field = new JTextField();
		    GuiStyler.setDefaultFieldStyle(answer2Field, GuiStyler.FieldSize.SHORT);
		    
		    answer3Field = new JTextField();
		    GuiStyler.setDefaultFieldStyle(answer3Field, GuiStyler.FieldSize.SHORT);
		    
		    answer4Field = new JTextField();
		    GuiStyler.setDefaultFieldStyle(answer4Field, GuiStyler.FieldSize.SHORT);
		    
		    
		    questionArea = new JTextArea();
		    GuiStyler.setDefaultStyleQuestionArea(questionArea); 
		    
		    //TODO: change this to something dynamic!!!
		    messages = new JTextArea();	    
		    GuiStyler.setDefaultStyleMessagesArea(messages);
		    messages.setText("Testing!");
		    
		    checkbox1 = new JCheckBox();	   
		    checkbox2 = new JCheckBox();
		    checkbox3 = new JCheckBox();
		    checkbox4 = new JCheckBox();
		    //TODO deal with actionListeners!
		    deleteQuestion = new JButton();
		    deleteQuestion.setText(DELETE_QUESTION_BUTTON_TEXT);
		    GuiStyler.setDefaultButtonStyle(deleteQuestion);
//		    showCorrectAnswer.addActionListener(this);
//		    showCorrectAnswer.setActionCommand(SHOW_ANSWER_BUTTON_TEXT);
		    
		    
		    saveQuestion = new JButton();
		    saveQuestion.setText(SAVE_QUESTION_BUTTON_TEXT);
		    GuiStyler.setDefaultButtonStyle(saveQuestion);
//		    saveMyAnswer.addActionListener(this);
//		    saveMyAnswer.setActionCommand(SAVE_ANSWER_BUTTON_TEXT);
		}


		

		/**
		 * Sets the layout manager and arranges all components on the panel
		 * using GridBagLayout with appropriate constraints.
		 */
		public void setLayout() {
			
			this.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();			
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.fill = GridBagConstraints.NONE;
			
			gbc.gridy = 0;
			gbc.gridx = 0;
			gbc.gridwidth = 1;	
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(subjectLabel,gbc);
			
			gbc.gridy = 0;
			gbc.gridx = 1;	
			gbc.gridwidth = 1;
			JPanel spacer = new JPanel();		
			spacer.setPreferredSize(new Dimension(20, 0));
			this.add(spacer,gbc);
			
			gbc.gridy = 0;
			gbc.gridx = 2;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(subjectValueLabel, gbc);
			
			gbc.gridy = 1;
			gbc.gridx = 0;	
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
//			titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			this.add(titleLabel, gbc);
			
			gbc.gridy = 1;
			gbc.gridx = 2;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
//			titleField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			this.add(titleField, gbc);		
				
			gbc.gridy = 2;
			gbc.gridx = 0;	
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(questionLabel, gbc);
			
			gbc.gridy = 2;
			gbc.gridx = 2;
			gbc.gridwidth = 2;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			JScrollPane scrollPane = new JScrollPane(questionArea);
			scrollPane.setBorder(null);
			this.add(scrollPane, gbc);
			
			gbc.gridy = 3;
			gbc.gridx = 0;
			gbc.gridwidth = 3;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(possibleAnswersLabel, gbc);
			
			gbc.gridy = 4;
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer1Label, gbc);
			
			gbc.gridy = 4;
			gbc.gridx = 2;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer1Field, gbc);
			
			gbc.gridy = 4;
			gbc.gridx = 3;
			gbc.gridwidth = 1;		
			gbc.ipadx = 0;
			gbc.ipady = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			JPanel wrapper = new JPanel();
			wrapper.add(checkbox1);
			this.add(wrapper, gbc);

			
			gbc.gridy = 5;
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer2Label, gbc);
			
			gbc.gridy = 5;
			gbc.gridx = 2;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer2Field, gbc);
			
			gbc.gridy = 5;
			gbc.gridx = 3;
			gbc.gridwidth = 1;
			gbc.ipadx = 0;
			gbc.ipady = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			wrapper = new JPanel();
			wrapper.add(checkbox2);
			this.add(wrapper, gbc);
			
			gbc.gridy = 6;
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer3Label, gbc);
			
			gbc.gridy = 6;
			gbc.gridx = 2;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer3Field, gbc);
			
			gbc.gridy = 6;
			gbc.gridx = 3;
			gbc.gridwidth = 1;
			gbc.ipadx = 0;
			gbc.ipady = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			wrapper = new JPanel();
			wrapper.add(checkbox3);
			this.add(wrapper, gbc);
			
			gbc.gridy = 7;
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer4Label, gbc);
			
			gbc.gridy = 7;
			gbc.gridx = 2;
			gbc.gridwidth = 1;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.anchor = GridBagConstraints.BASELINE_LEADING;
			this.add(answer4Field, gbc);
			
			gbc.gridy = 7;
			gbc.gridx = 3;
			gbc.gridwidth = 1;
			gbc.ipadx = 0;
			gbc.ipady = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			wrapper = new JPanel();
			wrapper.add(checkbox4);
			this.add(wrapper, gbc);
			
			gbc.gridy = 8;
			gbc.gridx = 0;
			gbc.insets = new Insets(20,0,0,0);
			gbc.gridwidth = 4;
//			gbc.ipadx = 20;
//			gbc.ipady = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			wrapper = new JPanel();
			wrapper.add(messages);
			this.add(wrapper, gbc);
			
			gbc.gridy = 9;
			gbc.gridx = 0;
			gbc.gridwidth = 4;
			gbc.ipadx = 20;
			gbc.ipady = 20;
			gbc.insets = new Insets(20,0,0,0);
			gbc.anchor = GridBagConstraints.SOUTH;
			wrapper = new JPanel();
			wrapper.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
			wrapper.add(deleteQuestion);
			wrapper.add(saveQuestion);
			this.add(wrapper, gbc);
			
		}
		
		/**
		 * Registers an ActionListener for the "Delete Question" button.
		 * @param listener the listener to be notified when the button is clicked
		 */
		public void addDeleteQuestionListener(ActionListener listener) {
			deleteQuestion.addActionListener(listener);
		}
		
		/**
		 * Registers an ActionListener for the "Save Question" button.
		 * @param listener the listener to be notified when the button is clicked
		 */
		public void addSaveQuestionListener(ActionListener listener) {
			saveQuestion.addActionListener(listener);
		}
		
		
		/**
		 * Sets the subject label with the string representation of a SubjectData.
		 * @param subjectData the SubjectData whose title to display
		 */
		public void setSubject(SubjectData subjectData) {		
			subjectValueLabel.setText(subjectData.toString());			
		}
		
		/**
		 * Sets the subject label text field directly with a string.
		 * @param subject the subject text to display
		 */
		public void setSubject(String subject) {		
			subjectValueLabel.setText(subject);			
		}

		/**
		 * Sets the question title text field using the string representation of a QuestionData.
		 * @param question the QuestionData whose title to display
		 */
		public void setQuestionTitle(QuestionData question) {		
			titleField.setText(question.toString());			
		} 
		
		/**
		 * Sets the question title text field directly with a string.
		 * @param question the title text to display
		 */
		public void setQuestionTitle(String question) {		
			titleField.setText(question);			
		}
		
		public void setQuestionAreaContent(String questionContent) {		
			questionArea.setText(questionContent);			
		}
		
		
		/**
		 * Populates the answer fields and corresponding checkboxes based on
		 * the provided list of AnswerData. Ensures exactly four answer slots are filled,
		 * using empty defaults if fewer answers are provided.
		 * @param answersList list of answers to display
		 */
		public void setAnswers(List<AnswerData> answersList) {
		    // Ensure we have exactly 4 answers (fill with empty AnswerData if needed)
		    List<AnswerData> list = new ArrayList<>(answersList);
		    while (list.size() < 4) {
		        list.add(new AnswerData());
		    }

		    JTextField[] fields = { answer1Field, answer2Field, answer3Field, answer4Field };
		    JCheckBox[] checks = { checkbox1, checkbox2, checkbox3, checkbox4 };

		    for (int i = 0; i < 4; i++) {
		        fields[i].setText(list.get(i).getAnswerContent());
		        checks[i].setSelected(list.get(i).isCorrect());
		    }
		}
		
		
		public List<AnswerData> getAnswers() {
		    List<AnswerData> answersList = new ArrayList<>();

		    JTextField[] fields = { answer1Field, answer2Field, answer3Field, answer4Field };
		    JCheckBox[] checks = { checkbox1, checkbox2, checkbox3, checkbox4 };

		    for (int i = 0; i < fields.length; i++) {
		        String content = fields[i].getText().trim();
		        boolean correct = checks[i].isSelected();
		        AnswerData answer = createAnswerDataIfValid(content, correct);
		        if (answer != null) {
		            answersList.add(answer);
		        }
		    }
		    return answersList;
		}
		
		
		private AnswerData createAnswerDataIfValid(String answerContent, boolean correct) {
			if(!answerContent.isBlank() || correct) {
				return new AnswerData(answerContent, correct);				
			}
			return null;
		}

		
		/**
		 * Clears all fields related to the question and answers,
		 * resetting the UI to an empty state.
		 */
		public void clearQuestionAndAnswerFields() {			
			List<AnswerData> emptyList = new ArrayList<>(List.of(
				new AnswerData(),
				new AnswerData(),
				new AnswerData(),
				new AnswerData()
			));
			QuestionData emptyQuestion = new QuestionData();
			
			setQuestionTitle(emptyQuestion);
			setQuestionAreaContent("");
			setAnswers(emptyList);
			
		}
		
//		public void dislayMessages() {
//			// TODO set is dynamically later.
//			messages.setText("Testing!");
//			
//		}


		
	

}
