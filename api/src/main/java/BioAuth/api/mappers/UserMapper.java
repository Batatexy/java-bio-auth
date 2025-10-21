package BioAuth.api.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.entities.User;

@Component
public class UserMapper {
	public UserResponseDTO toDTO(User user) {
		return new UserResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getImage(),
				user.getDigitalImagesRoles());
	}

	public User toEntity(UserCreateDTO userCreateDTO, byte[] userImageBytes, 
			List<byte[]> digitalImageBytes, String password) {
		return new User(userCreateDTO.fullName(), userCreateDTO.email(), password, userImageBytes, digitalImageBytes);
	}

	public User toUpdateEntity(User user, byte[] imageBytes, List<byte[]> digitalImageBytes, String password) {
		return new User(user.getFullName(), user.getEmail(), password, imageBytes, digitalImageBytes);
	}

}
