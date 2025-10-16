package BioAuth.api.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleCreateDTO;
import BioAuth.api.dtos.userrole.UserRoleListResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleMicroResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleUpdateDTO;
import BioAuth.api.entities.Role;
import BioAuth.api.entities.User;
import BioAuth.api.entities.UserRole;
import BioAuth.api.mappers.RoleMapper;
import BioAuth.api.mappers.UserMapper;
import BioAuth.api.mappers.UserRoleMapper;
import BioAuth.api.repositories.UserRoleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserRoleService {

	private final UserRoleRepository userRoleRepository;
	private final UserService userService;
	private final RoleService roleService;
	private final UserRoleMapper userRoleMapper;

	private final UserMapper userMapper;
	private final RoleMapper roleMapper;

	public UserRoleService(UserRoleRepository userRoleRepository, UserService userService, RoleService roleService,
			UserRoleMapper userRoleMapper, UserMapper userMapper, RoleMapper roleMapper) {
		super();
		this.userRoleRepository = userRoleRepository;
		this.userService = userService;
		this.roleService = roleService;
		this.userRoleMapper = userRoleMapper;
		this.userMapper = userMapper;
		this.roleMapper = roleMapper;
	}

	public UserRoleListResponseDTO list() {
		List<UserRole> userRoles = userRoleRepository.findAll();

		Map<User, List<RoleResponseDTO>> rolesByUser = userRoles.stream()
				.collect(Collectors.groupingBy(UserRole::getUser, LinkedHashMap::new,
						Collectors.mapping(ur -> roleMapper.toDTO(ur.getRole()), Collectors.toList())));

		List<UserRoleResponseDTO> userRoleDTOs = rolesByUser.entrySet().stream().map(entry -> {
			User firstUser = entry.getKey();
			UserRole firstUserRole = userRoles.stream().filter(ur -> ur.getUser().equals(firstUser)).findFirst()
					.orElseThrow();
			return new UserRoleResponseDTO(firstUserRole.getId(), userMapper.toDTO(firstUser), entry.getValue()
			);
		}).toList();

		return new UserRoleListResponseDTO(userRoleDTOs);
	}

	public UserRoleResponseDTO findById(@NotNull Long id) {
		UserRoleListResponseDTO userRoleListResponseDTO = list();
		return userRoleListResponseDTO.usersRoles().stream().filter(dto -> dto.id().equals(id))
				.findFirst().orElseThrow(() -> new RuntimeException("UserRole não encontrado"));
	}

	public UserRoleResponseDTO findByUserId(@NotNull Long userId) {
		UserRoleListResponseDTO userRoleListResponseDTO = list();
		return userRoleListResponseDTO.usersRoles().stream().filter(dto -> dto.user().id().equals(userId))
				.findFirst().orElseThrow(() -> new RuntimeException("User não encontrado"));
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
