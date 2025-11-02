package BioAuth.api.dtos.userrole;

import java.util.List;

import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;

public record UserRoleResponseDTO(
	UserResponseDTO user,
	List<RoleResponseDTO> roles
){}