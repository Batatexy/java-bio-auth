package BioAuth.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserListResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.entities.User;
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
	public UserResponseDTO create(@Valid @NotNull UserCreateDTO userCreateDTO) {
//		byte[] imageBytes = userCreateDTO.image().getBytes();
		byte[] imageBytes = null;
		// Criptografar
		var password = userCreateDTO.password();
		User user = userMapper.toEntity(userCreateDTO, imageBytes, password);
		return userMapper.toDTO(userRepository.save(user));
	}
}
