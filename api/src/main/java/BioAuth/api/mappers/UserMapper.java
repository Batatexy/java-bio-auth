package BioAuth.api.mappers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserDetailsResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.entities.User;

@Component
public class UserMapper {
	public UserResponseDTO toDTO(User user) {
		List<byte[]> digitalImages = Arrays.asList(user.getDigitalImage1(), user.getDigitalImage2(),
				user.getDigitalImage3(), user.getDigitalImage4(), user.getDigitalImage5(), user.getDigitalImage6());

		return new UserResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getImage(), digitalImages);
	}

	public UserDetailsResponseDTO toDetailsDTO(User user) {
		return new UserDetailsResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getImage(), 
				user.getDigitalImage1(), user.getDigitalImage2(), user.getDigitalImage3(), user.getDigitalImage4(),
				user.getDigitalImage5(), user.getDigitalImage6());
	}

	public User toEntity(UserCreateDTO userCreateDTO, byte[] userImageBytes, String password,
			byte[] digitalImage1Bytes, byte[] digitalImage2Bytes, byte[] digitalImage3Bytes, byte[] digitalImage4Bytes,
			byte[] digitalImage5Bytes, byte[] digitalImage6Bytes) {
		return new User(userCreateDTO.fullName(), userCreateDTO.email(), password, userImageBytes, digitalImage1Bytes,
				digitalImage2Bytes, digitalImage3Bytes, digitalImage4Bytes, digitalImage5Bytes, digitalImage6Bytes);
	}

	public User toUpdateEntity(User user, String password, byte[] userImageBytes,
			byte[] digitalImage1Bytes, byte[] digitalImage2Bytes, byte[] digitalImage3Bytes, byte[] digitalImage4Bytes,
			byte[] digitalImage5Bytes, byte[] digitalImage6Bytes) {
		return new User(user.getFullName(), user.getEmail(), password, userImageBytes, digitalImage1Bytes,
				digitalImage2Bytes, digitalImage3Bytes, digitalImage4Bytes, digitalImage5Bytes, digitalImage6Bytes);
	}

}
