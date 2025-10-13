package BioAuth.api.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.entities.UserRole;

@Component
public class UserRoleMapper {
	public UserRoleResponseDTO toDTO(UserRole userRole, UserResponseDTO userResponseDTO,
			List<RoleResponseDTO> roleResponseDTO) {
		return new UserRoleResponseDTO(userRole.getId(), userResponseDTO, roleResponseDTO);
	}

	


}
