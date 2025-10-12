package BioAuth.api.dtos.userrole;

import jakarta.validation.constraints.NotNull;

public record UserRoleCreateDTO(
	@NotNull Long userId,
	@NotNull Long roleId
){}