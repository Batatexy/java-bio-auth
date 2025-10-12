package BioAuth.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserListResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public UserListResponseDTO list() {
		return userService.list();
	}

	@GetMapping("/{id}")
	public UserResponseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return userService.findById(id);
	}

	// Erro
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseDTO create(@Valid @NotNull @RequestBody UserCreateDTO userCreateDTO) {
		return userService.create(userCreateDTO);
	}

}
