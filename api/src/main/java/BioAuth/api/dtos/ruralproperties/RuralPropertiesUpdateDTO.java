package BioAuth.api.dtos.ruralproperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RuralPropertiesUpdateDTO(
	@NotBlank String placeName,
	@NotBlank String description,
	@NotBlank String ownerName,
	@NotBlank String address,
	@NotNull float size,
	@NotBlank String agroChemicals,
	@NotNull Long agrochemicalsLevelOrder,
	@NotNull Long levelOrder
){}