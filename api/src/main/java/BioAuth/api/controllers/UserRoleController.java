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

import BioAuth.api.dtos.userrole.UserRoleCreateDTO;
import BioAuth.api.dtos.userrole.UserRoleListResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleMicroResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleUpdateDTO;
import BioAuth.api.services.UserRoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/users-roles")
public class UserRoleController {

	private final UserRoleService userRoleService;

	public UserRoleController(UserRoleService userRoleService) {
		super();
		this.userRoleService = userRoleService;
	}

	@GetMapping
	public UserRoleListResponseDTO list() {
		return userRoleService.list();
	}

	@GetMapping("/{id}")
	public UserRoleResponseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return userRoleService.findById(id);
	}

	@GetMapping("/user/{userId}")
	public UserRoleResponseDTO findByUserId(@PathVariable @NotNull @Positive Long userId) {
		return userRoleService.findByUserId(userId);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserRoleMicroResponseDTO create(@Valid @NotNull @RequestBody UserRoleCreateDTO userRoleCreateDTO) {
		return userRoleService.create(userRoleCreateDTO);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserRoleMicroResponseDTO update(@PathVariable @NotNull @Positive Long id,
			@Valid @NotNull @RequestBody UserRoleUpdateDTO userRoleUpdateDTO) {
		return userRoleService.update(id, userRoleUpdateDTO);
	}

}
