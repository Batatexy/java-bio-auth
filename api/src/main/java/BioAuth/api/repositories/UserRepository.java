package BioAuth.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BioAuth.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	@Query(value = """
			SELECT 1 FROM users
			WHERE email = :email
			""", nativeQuery = true)
	Long findByEmail(@Param("email") String email);
}