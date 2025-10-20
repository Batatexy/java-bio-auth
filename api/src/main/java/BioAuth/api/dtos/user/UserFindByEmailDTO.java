package BioAuth.api.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UserFindByEmailDTO(
	@NotNull String email
){}