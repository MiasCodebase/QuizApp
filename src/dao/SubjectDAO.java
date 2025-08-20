package dao;

import java.util.List;
import java.util.Optional;
import domain.Subject;

/**
 * DAO for Subject entities.
 */
public interface SubjectDAO {
	
    /** Find a subject by its ID. */
    Optional<Subject> findById(int id);
    
    /** Fetch all subjects. */
    List<Subject> findAll();
    
    /**
     * Insert a new subject.
     * @return generated primary key ID
     */
    int insert(Subject subject);
    
    /**
     * Update an existing subject.
     * @return true if a row was updated
     */
    boolean update(Subject subject);
    
    /**
     * Delete a subject by ID.
     * @return true if a row was deleted
     */
    boolean delete(int id);
}