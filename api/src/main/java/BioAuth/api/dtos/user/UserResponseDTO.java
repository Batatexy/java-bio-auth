package BioAuth.api.dtos.user;

import java.util.List;

public record UserResponseDTO(
	Long id,
	String fullName,
	String email,
	byte[] userImage,
	List<byte[]> digitalImages
){}