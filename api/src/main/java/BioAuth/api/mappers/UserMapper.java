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
		User user = new User();
		user.setFullName(userCreateDTO.fullName());
		user.setEmail(userCreateDTO.email());
		user.setEmail(password);
		user.setImage(imageBytes);
		return user;
	}
}
