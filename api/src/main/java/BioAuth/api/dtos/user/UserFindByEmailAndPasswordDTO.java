package BioAuth.api.dtos.user;

public record UserFindByEmailAndPasswordDTO(
	String email,
	String password
){}