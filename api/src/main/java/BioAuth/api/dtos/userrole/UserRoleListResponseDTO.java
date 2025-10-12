package BioAuth.api.dtos.userrole;

import java.util.List;

public record UserRoleListResponseDTO(
	List<UserRoleResponseDTO> usersRoles
){}