package BioAuth.api.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import BioAuth.api.dtos.userrole.UserRoleCreateDTO;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.entities.Role;
import BioAuth.api.entities.User;
import BioAuth.api.entities.UserRole;
import BioAuth.api.mappers.RoleMapper;
import BioAuth.api.mappers.UserMapper;
import BioAuth.api.mappers.UserRoleMapper;
import BioAuth.api.repositories.UserRoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
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

//	public UserRoleListResponseDTO list() {
//		List<UserRoleResponseDTO> userRoles = userRoleRepository.findAll().stream().map(userRoleMapper::toDTO).toList();
//		return new UserRoleListResponseDTO(userRoles);
//	}

	public UserRoleResponseDTO findById(@NotNull Long id) {
		UserRole userRole = userRoleRepository.findById(id).get();
		return userRoleMapper.toDTO(userRole, userMapper.toDTO(userRole.getUser()), roleMapper.toDTO(userRole.getRole()));
	}
	
	public UserRoleResponseDTO findByUserId(@NotNull Long userId) {
		UserRole userRole = userRoleRepository.findByUserId(userId).get();
		return userRoleMapper.toDTO(userRole, userMapper.toDTO(userRole.getUser()), roleMapper.toDTO(userRole.getRole()));
	}

	@Transactional
	public UserRoleResponseDTO create(@Valid @NotNull UserRoleCreateDTO userRoleCreateDTO) {
		User user = userService.findUserById(userRoleCreateDTO.userId()).get();
		Role role = roleService.findRoleById(userRoleCreateDTO.roleId()).get();
		UserRole userRole = userRoleMapper.toEntity(user, role);
		return userRoleMapper.toDTO(userRoleRepository.save(userRole), userMapper.toDTO(user), roleMapper.toDTO(role));
	}
}
