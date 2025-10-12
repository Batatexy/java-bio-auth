package BioAuth.api.dtos.user;

import java.util.List;

public record UserListResponseDTO(
	List<UserResponseDTO> users
){}