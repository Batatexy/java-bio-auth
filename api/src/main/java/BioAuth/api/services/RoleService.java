package BioAuth.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import BioAuth.api.dtos.role.RoleCreateDTO;
import BioAuth.api.dtos.role.RoleListResponseDTO;
import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.role.RoleUpdateDTO;
import BioAuth.api.entities.Role;
import BioAuth.api.mappers.RoleMapper;
import BioAuth.api.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;

	public RoleService(RoleRepository RoleRepository, RoleMapper roleMapper) {
		super();
		this.roleRepository = RoleRepository;
		this.roleMapper = roleMapper;
	}

	public RoleListResponseDTO list() {
		List<RoleResponseDTO> Roles = roleRepository.findAll().stream().map(roleMapper::toDTO).toList();
		return new RoleListResponseDTO(Roles);
	}

	public RoleResponseDTO findById(@NotNull Long id) {
		return roleMapper.toDTO(roleRepository.findById(id).get());
	}

	public Optional<Role> findRoleById(@NotNull Long id) {
		return roleRepository.findById(id);
	}

	@Transactional
	public RoleResponseDTO create(@Valid @NotNull RoleCreateDTO RoleCreateDTO) {
		Role Role = roleMapper.toEntity(RoleCreateDTO);
		return roleMapper.toDTO(roleRepository.save(Role));
	}

	@Transactional
	public RoleResponseDTO update(@NotNull Long id, @Valid @NotNull RoleUpdateDTO roleUpdateDTO) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("UserRole não encontrado"));

		role.setName(roleUpdateDTO.name());
		role.setDescription(roleUpdateDTO.description());
		role.setLevelOrder(roleUpdateDTO.levelOrder());
		
		return roleMapper.toDTO(roleRepository.save(role));
	}
}
