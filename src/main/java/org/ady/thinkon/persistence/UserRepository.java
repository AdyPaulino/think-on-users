package org.ady.thinkon.persistence;


import org.ady.thinkon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer for User entity. Using default JpaRepository methods.
 *
 * @author Ady Paulino
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
