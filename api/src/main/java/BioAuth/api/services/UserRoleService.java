package BioAuth.api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import BioAuth.api.dtos.user.UserFindByEmailAndPasswordDTO;
import BioAuth.api.dtos.userrole.UserRoleCreateDTO;
import BioAuth.api.dtos.userrole.UserRoleMicroResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleProjection;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleUpdateDTO;
import BioAuth.api.entities.Role;
import BioAuth.api.entities.User;
import BioAuth.api.entities.UserRole;
import BioAuth.api.mappers.UserRoleMapper;
import BioAuth.api.repositories.UserRoleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserRoleService {

	private final UserRoleRepository userRoleRepository;
	private final UserRoleMapper userRoleMapper;

	public UserRoleService(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
		super();
		this.userRoleRepository = userRoleRepository;
		this.userRoleMapper = userRoleMapper;
	}

	public UserRoleResponseDTO findByUserId(@NotNull Long userId) {
		List<UserRoleProjection> userRoles = userRoleRepository.findByUserId(userId);
		return userRoleMapper.toDTO(userRoles);
	}

	public UserRoleResponseDTO findByEmailAndPassword(@Valid @NotNull UserFindByEmailAndPasswordDTO userDTO) {
		List<UserRoleProjection> userRoles = userRoleRepository.findByEmailAndPassword(userDTO.email(),
				userDTO.password());

		return userRoleMapper.toDTO(userRoles);
	}

	public UserRoleResponseDTO findByDigitalImage(MultipartFile digitalImage) {
		List<UserRoleProjection> allUserRoles = userRoleRepository.findAllUserRoles();
		
		// Implementar Algoritmo de Digital
		
		

		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public UserRoleMicroResponseDTO create(@Valid @NotNull UserRoleCreateDTO userRoleCreateDTO) {
		User user = new User(); user.setId(userRoleCreateDTO.userId());
		Role role = new Role(); role.setId(userRoleCreateDTO.roleId());
		return new UserRoleMicroResponseDTO(userRoleRepository.save(new UserRole(user, role)).getId(), user.getId(), role.getId());
	}

	@Transactional
	public UserRoleMicroResponseDTO update(@NotNull Long userRoleId, @Valid @NotNull UserRoleUpdateDTO userRoleUpdateDTO) {
		UserRole userRole = userRoleRepository.findById(userRoleId)
				.orElseThrow(() -> new RuntimeException("UserRole não encontrado"));

		User user = new User();
		user.setId(userRoleUpdateDTO.userId());
		Role role = new Role();
		role.setId(userRoleUpdateDTO.roleId());

		userRole.setUser(user);
		userRole.setRole(role);

		UserRole updated = userRoleRepository.save(userRole);
		return new UserRoleMicroResponseDTO(updated.getId(), user.getId(), role.getId());
	}


}
