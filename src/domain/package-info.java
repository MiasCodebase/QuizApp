/**
 * This package contains the rich domain entities for a quiz application.
 * 
 * <p>The domain model consists of three main entities:</p>
 * 
 * <ul>
 *     <li>{@link domain.Subject} – Represents a quiz subject or topic. Contains a list of
 *         associated {@link domain.Question} objects. Provides validation for its own fields
 *         such as title and description.</li>
 *         
 *     <li>{@link domain.Question} – Represents a question belonging to a {@link domain.Subject}.
 *         Contains the question text, a list of {@link domain.Answer} objects, and provides
 *         validation for user input such as title, content, and associated subject.</li>
 *         
 *     <li>{@link domain.Answer} – Represents an answer to a {@link domain.Question}.
 *         Contains the answer text, correctness flag, and a reference to its parent question.
 *         Enforces programming invariants (non-null question, non-negative ID) and validates
 *         user input such as empty answer text.</li>
 * </ul>
 * 
 */
package domain;