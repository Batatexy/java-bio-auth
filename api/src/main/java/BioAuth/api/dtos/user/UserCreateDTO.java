package BioAuth.api.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(
	@NotNull String fullName,
	@NotNull String email,
	@NotNull String password,
	String image
){}