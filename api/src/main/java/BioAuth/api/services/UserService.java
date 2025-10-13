package BioAuth.api.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserListResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.dtos.user.UserUpdateDTO;
import BioAuth.api.entities.User;
import BioAuth.api.exceptions.UserImageProcessingException;
import BioAuth.api.mappers.UserMapper;
import BioAuth.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserListResponseDTO list() {
		List<UserResponseDTO> users = userRepository.findAll().stream().map(userMapper::toDTO).toList();
		return new UserListResponseDTO(users);
	}

	public UserResponseDTO findById(@NotNull Long id) {
		return userMapper.toDTO(userRepository.findById(id).get());
	}

	public Optional<User> findUserById(@NotNull Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	public UserResponseDTO create(@Valid UserCreateDTO userCreateDTO, MultipartFile image) {
		byte[] imageBytes = null;
		if (image != null) { imageBytes = extractImageBytes(image); }
		var password = userCreateDTO.password();
		return userMapper.toDTO(userRepository.save(userMapper.toEntity(userCreateDTO, imageBytes, password)));
	}
	
	@Transactional
	public UserResponseDTO update(@NotNull Long id, @Valid UserUpdateDTO userUpdateDTO, MultipartFile image) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("UserRole não encontrado"));

		byte[] imageBytes = null;
		if (image != null) { imageBytes = extractImageBytes(image); }

		user.setFullName(userUpdateDTO.fullName());
		user.setImage(imageBytes);

		return userMapper.toDTO(userRepository.save(user));
	}

	private byte[] extractImageBytes(MultipartFile image) {
		try {
			return image.getBytes();
		} catch (IOException e) {
			throw new UserImageProcessingException();
		}
	}
}
