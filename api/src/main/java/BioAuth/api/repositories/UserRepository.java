package BioAuth.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BioAuth.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	@Query(value = """
			SELECT *
			FROM users
			WHERE email = :email
			AND password = :password
			""", nativeQuery = true)
	Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query(value = """
			SELECT *
			FROM users
			WHERE email = :email
			""", nativeQuery = true)
	Optional<User> findByEmail(@Param("email") String email);
}
