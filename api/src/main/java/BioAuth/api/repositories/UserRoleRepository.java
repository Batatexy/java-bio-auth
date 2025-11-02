package BioAuth.api.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BioAuth.api.dtos.userrole.UserRoleProjection;
import BioAuth.api.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
	@Query(value = """
			SELECT
			    users.user_id AS userId,
			    users.full_name AS fullName,
			    users.email AS email,
			    HEX(users.image) AS image,
			    HEX(users.digital_image1) AS digitalImage1,
			    HEX(users.digital_image2) AS digitalImage2,
			    HEX(users.digital_image3) AS digitalImage3,
			    HEX(users.digital_image4) AS digitalImage4,
			    HEX(users.digital_image5) AS digitalImage5,
			    HEX(users.digital_image6) AS digitalImage6,
			    roles.role_id AS roleId,
			    roles.name AS name,
			    roles.description AS description,
			    roles.level_order AS levelOrder
			FROM user_roles AS userRoles
			JOIN users AS users ON users.user_id = userRoles.user_id
			JOIN roles AS roles ON roles.role_id = userRoles.role_id
			WHERE users.email = :email
			AND users.password = :password
			""", nativeQuery = true)
	List<UserRoleProjection> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query(value = """
			SELECT
			    users.user_id AS userId,
			    users.full_name AS fullName,
			    users.email AS email,
			    HEX(users.image) AS image,
			    HEX(users.digital_image1) AS digitalImage1,
			    HEX(users.digital_image2) AS digitalImage2,
			    HEX(users.digital_image3) AS digitalImage3,
			    HEX(users.digital_image4) AS digitalImage4,
			    HEX(users.digital_image5) AS digitalImage5,
			    HEX(users.digital_image6) AS digitalImage6,
			    roles.role_id AS roleId,
			    roles.name AS name,
			    roles.description AS description,
			    roles.level_order AS levelOrder
			FROM user_roles AS userRoles
			JOIN users AS users ON users.user_id = userRoles.user_id
			JOIN roles AS roles ON roles.role_id = userRoles.role_id
			WHERE users.user_id = :userId
			""", nativeQuery = true)
	List<UserRoleProjection> findByUserId(@Param("userId") Long userId);

	@Query(value = """
			SELECT
			    users.user_id AS userId,
			    users.full_name AS fullName,
			    users.email AS email,
			    HEX(users.image) AS image,
			    HEX(users.digital_image1) AS digitalImage1,
			    HEX(users.digital_image2) AS digitalImage2,
			    HEX(users.digital_image3) AS digitalImage3,
			    HEX(users.digital_image4) AS digitalImage4,
			    HEX(users.digital_image5) AS digitalImage5,
			    HEX(users.digital_image6) AS digitalImage6,
			    roles.role_id AS roleId,
			    roles.name AS name,
			    roles.description AS description,
			    roles.level_order AS levelOrder
			FROM user_roles AS userRoles
			JOIN users AS users ON users.user_id = userRoles.user_id
			JOIN roles AS roles ON roles.role_id = userRoles.role_id
			""", nativeQuery = true)
	List<UserRoleProjection> findAllUserRoles();

}
