package gui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Utility class providing methods to apply consistent styles
 * to various Swing GUI components such as labels, text fields,
 * buttons, combo boxes, and lists.
 * <p>
 * Methods in this class use predefined constants from GuiStyleConstants
 * to set fonts, colors, sizes, and other UI properties,
 * supporting different sizes and component types.
 * <p>
 * This class is not meant to be instantiated.
 */
public class GuiStyler {
	
	private GuiStyler() {}

	//Here we have one method for different sized labels rather than one for each size.
	public static void setLabelStyle(JLabel label, LabelSize size) {
		
		switch(size) {
			case LARGE -> {
				label.setFont(GuiStyleConstants.H1);
				label.setForeground(GuiStyleConstants.DARK_TEAL);
			}
			case MEDIUM -> {
				label.setFont(GuiStyleConstants.H2);
				label.setForeground(GuiStyleConstants.DARK_TEAL);
			}
			case SMALL -> {
				label.setFont(GuiStyleConstants.REGULAR);
				label.setForeground(GuiStyleConstants.DARK_TEAL);
			}				
		}	
	}
	
	public static void setDefaultFieldStyle(JTextField field, FieldSize size) {
		field.setFont(GuiStyleConstants.REGULAR);
		field.setForeground(GuiStyleConstants.DARK_TEAL);	
		field.setBackground(GuiStyleConstants.BIEGE);
		field.setEditable(false);
		
		switch(size) {
		case SHORT -> field.setPreferredSize(new Dimension(200, 15));
		case LONG -> field.setPreferredSize(new Dimension(250, 15));
		}
		
	}
	

	public static void setDefaultStyleQuestionArea(JTextArea questionArea) {
		questionArea.setFont(GuiStyleConstants.REGULAR);
		questionArea.setForeground(GuiStyleConstants.DARK_TEAL);
		questionArea.setBackground(GuiStyleConstants.BIEGE);
		questionArea.setEditable(false);
		questionArea.setPreferredSize(new Dimension(250, 100));
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
	}
	
	public static void setDefaultStyleMessagesArea(JTextArea messages) {
		messages.setFont(GuiStyleConstants.REGULAR);
		messages.setForeground(GuiStyleConstants.DARK_TEAL);		
		messages.setBackground(GuiStyleConstants.BIEGE);
		messages.setEditable(false);
		messages.setPreferredSize(new Dimension(400, 80));
		messages.setLineWrap(true);
		messages.setWrapStyleWord(true);
	}

	public static void setDefaultButtonStyle(JButton button) {
		button.setFont(GuiStyleConstants.REGULAR);
		button.setForeground(GuiStyleConstants.DARK_TEAL);
		button.setBackground(GuiStyleConstants.ORANGE);
		button.setPreferredSize(new Dimension(170, 40));
		button.setMargin(new Insets(10, 10, 10, 10));	
		button.setBorder(null);
	}
	
	public static <T> void setComboBoxStyle(JComboBox<T> comboBox) {
		comboBox.setFont(GuiStyleConstants.REGULAR);
		comboBox.setForeground(GuiStyleConstants.DARK_TEAL);
		comboBox.setBackground(GuiStyleConstants.BIEGE);
		comboBox.setPreferredSize(new Dimension(380, 15));
	}
	
	public static <T> void setJListStyle(JList<T> questionsJList) {
		questionsJList.setFont(GuiStyleConstants.REGULAR);
		questionsJList.setForeground(GuiStyleConstants.DARK_TEAL);
		questionsJList.setBackground(GuiStyleConstants.BIEGE);
	}
	
	public static enum LabelSize {
	    LARGE, MEDIUM, SMALL
	}
	
	public static enum FieldSize {
	    LONG, SHORT
	}
	
	
	public static enum AreaSize {
	    LARGE, MEDIUM
	}



}
