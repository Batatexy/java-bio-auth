package BioAuth.api.dtos.user;

public record UserResponseDTO(
	Long id,
	String fullName,
	String email,
	byte[] image
){}