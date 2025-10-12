package BioAuth.api.dtos.userrole;

import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;

public record UserRoleResponseDTO(
	Long id,
	UserResponseDTO user, 
	RoleResponseDTO role
){}