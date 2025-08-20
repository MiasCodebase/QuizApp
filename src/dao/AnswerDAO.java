package dao;

import domain.Answer;
import java.util.List;
import java.util.Optional;

/**
 * DAO for Answer entities.
 * <p>Answers are  managed in relation to a Question.</p>
 */
public interface AnswerDAO {

    /** Find an answer by its ID. */
    Optional<Answer> findById(int id);

    /** Fetch all answers belonging to a question. */
    List<Answer> findByQuestionId(int questionId);
    
    /** Fetch all answers belonging to a question. */
    List<Answer> findByQuestion(domain.Question parent);

    /**
     * Insert a new answer.
     * @return generated primary key ID
     */
    int insert(Answer answer);

    /**
     * Update an existing answer.
     * @return true if a row was updated
     */
    boolean update(Answer answer);

    /**
     * Delete an answer by its primary key.
     *
     * @param id answer ID
     * @return true if a row was deleted, false otherwise
     */
    boolean delete(int id);
    
    /**
     * Delete all answers linked to a given question.
     *
     * @param questionId question ID
     * @return number of rows deleted (0 if none)
     */
    int deleteByQuestionId(int questionId);


    /**
     * Delete all answers linked to a given subject.
     *
     * @param subjectId subject ID
     * @return number of rows deleted (0 if none)
     */
    int deleteBySubjectId(int subjectId);
}
