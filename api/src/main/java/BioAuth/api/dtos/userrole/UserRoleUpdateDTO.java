package BioAuth.api.dtos.userrole;

import jakarta.validation.constraints.NotNull;

public record UserRoleUpdateDTO(
	@NotNull Long userId,
	@NotNull Long roleId
){}