
/**
 * Data Transfer Objects (DTOs) used by the quiz application.
 * <p>
 * These DTOs facilitate communication between the service layer and the presentation layer,
 * representing quiz subjects, questions, answers, and related aggregated data.
 * <p>
 * The package includes:
 * <ul>
 *   <li><b>SubjectLightDTO</b> and <b>QuestionLightDTO</b> – lightweight versions of SubjectDTO and QuestionDTO,
 *       containing only essential fields to efficiently initialize UI components like JComboBox and JList.</li>
 *   <li><b>QuizQuestionsInitDTO</b> – aggregates lists of SubjectLightDTOs and QuestionLightDTOs,
 *       providing initial data to populate quiz question panels.</li>
 *   <li><b>QuestionDTO</b> and <b>AnswerDTO</b> – represent full quiz questions and their answers with all relevant details.</li>
 *   <li><b>QuestionAndAnswersDTO</b> – aggregates a full QuestionDTO with its corresponding list of AnswerDTOs,
 *       used when a user selects a question to view or edit its answers.</li>
 * </ul>
 * <p>
 * These DTOs are designed to be simple containers of data, optimized for transferring only the necessary information
 * between layers without exposing internal domain models or business logic.
 */
package dto;
