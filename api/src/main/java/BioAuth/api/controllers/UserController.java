package BioAuth.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import BioAuth.api.dtos.user.UserCreateDTO;
import BioAuth.api.dtos.user.UserFindByEmailAndPasswordDTO;
import BioAuth.api.dtos.user.UserFindByEmailDTO;
import BioAuth.api.dtos.user.UserListResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.dtos.user.UserUpdateDTO;
import BioAuth.api.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@CrossOrigin(origins = "*")
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

	@PostMapping("/find-by-email")
	public UserResponseDTO findByEmail(@Valid @NotNull @RequestBody UserFindByEmailDTO userDTO) {
		return userService.findByEmail(userDTO);
	}

	@PostMapping("/me")
	public UserResponseDTO findByEmailAndPassword(@Valid @NotNull @RequestBody UserFindByEmailAndPasswordDTO userDTO) {
		return userService.findByEmailAndPassword(userDTO);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseDTO create(@RequestPart("data") @Valid UserCreateDTO userCreateDTO,
			@RequestPart(value = "image", required = false) MultipartFile userImage,
			@RequestPart(value = "digitalImage", required = false) List<MultipartFile> digitalImages) {
		return userService.create(userCreateDTO, userImage);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserResponseDTO update(@PathVariable @NotNull @Positive Long id,
			@RequestPart("data") @Valid UserUpdateDTO userUpdateDTO,
			@RequestPart(value = "image", required = false) MultipartFile image) {
		return userService.update(id, userUpdateDTO, image);
	}

}
