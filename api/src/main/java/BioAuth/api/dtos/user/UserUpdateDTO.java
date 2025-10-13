package BioAuth.api.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
	@NotBlank String fullName
){}