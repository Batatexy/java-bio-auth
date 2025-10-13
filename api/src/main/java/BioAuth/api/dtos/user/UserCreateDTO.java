package BioAuth.api.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
	@NotBlank String fullName,
	@NotBlank String email,
	@NotBlank String password
){}