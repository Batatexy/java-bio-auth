package BioAuth.api.mappers;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.role.RoleCreateDTO;
import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.entities.Role;

@Component
public class RoleMapper {
	public RoleResponseDTO toDTO(Role Role) {
		return new RoleResponseDTO(Role.getId(), Role.getName(), Role.getDescription(), Role.getLevelOrder());
	}
	
	public Role toEntity(RoleCreateDTO roleCreateDTO) {
		return new Role(roleCreateDTO.name(), roleCreateDTO.description(), roleCreateDTO.levelOrder());
	}
	
}
