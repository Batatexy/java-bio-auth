package BioAuth.api.dtos.role;

import java.util.List;

public record RoleListResponseDTO(
	List<RoleResponseDTO> roles
){}