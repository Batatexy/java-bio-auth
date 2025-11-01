package BioAuth.api.dtos.user;

public record UserDetailsResponseDTO(
	Long id,
	String fullName,
	String email,
	byte[] userImage,
	byte[] digitalImage1,
	byte[] digitalImage2,
	byte[] digitalImage3,
	byte[] digitalImage4,
	byte[] digitalImage5,
	byte[] digitalImage6
){}