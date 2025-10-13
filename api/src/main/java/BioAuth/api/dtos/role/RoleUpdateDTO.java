package BioAuth.api.dtos.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleUpdateDTO(
	@NotBlank String name,
	@NotBlank String description,
	@NotNull Long levelOrder
){}