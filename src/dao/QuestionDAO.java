package dao;

import java.util.List;
import java.util.Optional;

import domain.Question;
import domain.Subject;

/**
 * DAO for Question entities.
 */
public interface QuestionDAO {

    /** Find a question by its ID. */
    Optional<Question> findById(int id);

    /** Fetch all questions. */
    List<Question> findAll();

    /** Fetch all questions that belong to a subject. */
    List<Question> findBySubjectId(int subjectId);
    
    /** Fetch all questions that belong to a subject. */
    List<Question> findBySubject(Subject parent);

    /**
     * Insert a new question.
     * @return generated primary key ID
     */
    int insert(Question question);

    /**
     * Update an existing question.
     * @return true if a row was updated
     */
    boolean update(Question question);

    /**
     * Delete a question by ID.
     * @return true if a row was deleted
     */
    boolean delete(int id);
    
    /**
     * Delete all questions linked to a given subject.
     *
     * @param subjectId subject ID
     * @return number of rows deleted (0 if none)
     */
    int deleteBySubjectId(int subjectId);
}