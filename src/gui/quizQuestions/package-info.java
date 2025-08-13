/**
 * The {@code gui.quizQuestions} package is responsible for the "QuizQuestions" tab of the application.
 * <p>
 * It consists of purely visual components (JPanels) as well as a central
 * interaction class that processes user actions and communicates with the service layer
 * and {@code SessionData}.
 * </p>
 *
 * <h2>Architecture and Responsibilities</h2>
 * <ul>
 *   <li>
 *     <b>FrameQuizQuestions</b>: Entry point with a {@code main} method.
 *     Creates and wires all required components to promote loose coupling.
 *   </li>
 *   <li>
 *     <b>MainQuizQuestionsView</b>: Container panel that visually combines the
 *     left and right panel sections. Contains no logic.
 *   </li>
 *   <li>
 *     <b>QuizQuestionsPanelL</b>: Left UI section with graphical elements and
 *     listener hooks for external control.
 *   </li>
 *   <li>
 *     <b>QuizQuestionsPanelR</b>: Right UI section with graphical elements and
 *     methods for populating the {@code JComboBox} (Subjects) and {@code JList} (Questions)
 *     using the custom GUI data classes {@code SubjectData} and {@code QuestionData}.
 *   </li>
 *   <li>
 *     <b>QuizQuestionsInteractionHandler</b>: Central logic and communication class.
 *     Responds to user actions, retrieves data from the service layer, updates
 *     {@code SessionData}, and passes data to the panels.
 *   </li>
 * </ul>
 *
 * <h2>Design Principles</h2>
 * <ul>
 *   <li>Strict separation of UI and logic: panels contain only visual elements.</li>
 *   <li>Loose coupling via constructor injection instead of direct object creation.</li>
 *   <li>{@code SessionData} serves as a cache for session-specific data and keeps the
 *       {@code InteractionHandler} free of large field sets.</li>
 *   <li>Use of custom GUI data classes to retain relevant IDs without adding extra
 *       state fields to the panels.</li>
 * </ul>
 */
package gui.quizQuestions;
