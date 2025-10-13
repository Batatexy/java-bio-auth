package BioAuth.api.mappers;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.entities.User;

@Component
public class UserMapper {
	public UserResponseDTO toDTO(User user) {
		return new UserResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getImage());
	}

	public User toEntity(UserCreateDTO userCreateDTO, byte[] imageBytes, String password) {
		return new User(userCreateDTO.fullName(), userCreateDTO.email(), password, imageBytes);
	}

	public User toUpdateEntity(User user, byte[] imageBytes, String password) {
		return new User(user.getFullName(), user.getEmail(), password, imageBytes);
	}

}
