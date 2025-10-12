package BioAuth.api.mappers;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.entities.Role;
import BioAuth.api.entities.User;
import BioAuth.api.entities.UserRole;

@Component
public class UserRoleMapper {
	public UserRoleResponseDTO toDTO(UserRole userRole, UserResponseDTO userResponseDTO, RoleResponseDTO roleResponseDTO) {
		return new UserRoleResponseDTO(userRole.getId(), userResponseDTO, roleResponseDTO);
	}

	public UserRole toEntity(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		return userRole;
	}
	
	
	
	
}
