package BioAuth.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BioAuth.api.dtos.role.RoleCreateDTO;
import BioAuth.api.dtos.role.RoleListResponseDTO;
import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.role.RoleUpdateDTO;
import BioAuth.api.services.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@GetMapping
	public RoleListResponseDTO list() {
		return roleService.list();
	}

	@GetMapping("/{id}")
	public RoleResponseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return roleService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RoleResponseDTO create(@Valid @NotNull @RequestBody RoleCreateDTO roleCreateDTO) {
		return roleService.create(roleCreateDTO);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RoleResponseDTO update(@PathVariable @NotNull @Positive Long id,
			@Valid @NotNull @RequestBody RoleUpdateDTO roleUpdateDTO) {
		return roleService.update(id, roleUpdateDTO);
	}

}
