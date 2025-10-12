package BioAuth.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BioAuth.api.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
	@Query(value = """
			 SELECT *
			FROM user_roles
			WHERE user_id = :userId
			""", nativeQuery = true)
	Optional<UserRole> findByUserId(@Param("userId") Long userId);
}
