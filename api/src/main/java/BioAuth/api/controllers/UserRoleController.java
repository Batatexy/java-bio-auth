package BioAuth.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BioAuth.api.dtos.userrole.UserRoleCreateDTO;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.services.UserRoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("api/users-roles")
public class UserRoleController {

	private final UserRoleService userRoleService;

	public UserRoleController(UserRoleService userRoleService) {
		super();
		this.userRoleService = userRoleService;
	}

//	@GetMapping
//	public RoleListResponseDTO list() {
//		return userRoleService.list();
//	}

	@GetMapping("/{id}")
	public UserRoleResponseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return userRoleService.findById(id);
	}

	@GetMapping("/{userId}")
	public UserRoleResponseDTO findByUserId(@PathVariable @NotNull @Positive Long userId) {
		return userRoleService.findByUserId(userId);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserRoleResponseDTO create(@Valid @NotNull @RequestBody UserRoleCreateDTO userRoleCreateDTO) {
		return userRoleService.create(userRoleCreateDTO);
	}

}
