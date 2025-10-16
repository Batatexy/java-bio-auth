package BioAuth.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import BioAuth.api.dtos.ruralproperties.RuralPropertiesCreateDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesDetailsResponseDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesListResponseDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesResponseDTO;
import BioAuth.api.services.RuralPropertiesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/rural-properties")
public class RuralPropertiesController {

	private final RuralPropertiesService ruralPropertiesService;

	public RuralPropertiesController(RuralPropertiesService ruralPropertiesService) {
		super();
		this.ruralPropertiesService = ruralPropertiesService;
	}

	@GetMapping
	public RuralPropertiesListResponseDTO list() {
		return ruralPropertiesService.list();
	}

	@GetMapping("/{id}")
	public RuralPropertiesDetailsResponseDTO findById(@PathVariable @NotNull @Positive Long id) {
		return ruralPropertiesService.findById(id);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public RuralPropertiesResponseDTO create(
			@RequestPart("data") @Valid RuralPropertiesCreateDTO ruralPropertiesCreateDTO,
			@RequestPart(value = "image", required = false) MultipartFile image) {
		return ruralPropertiesService.create(ruralPropertiesCreateDTO, image);
	}

//	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public RuralPropertiesResponseDTO update(@PathVariable @NotNull @Positive Long id,
//			@RequestPart("data") @Valid RuralPropertiesUpdateDTO ruralPropertiesUpdateDTO,
//			@RequestPart(value = "image", required = false) MultipartFile image) {
//		return ruralPropertiesService.update(id, ruralPropertiesUpdateDTO, image);
//	}

}
