package BioAuth.api.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UserFindByEmailAndPasswordDTO(
	@NotNull String email,
	@NotNull String password
){}