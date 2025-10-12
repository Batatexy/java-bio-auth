package BioAuth.api.dtos.role;

import jakarta.validation.constraints.NotNull;

public record RoleCreateDTO(
	@NotNull String name,
	@NotNull String description
){}