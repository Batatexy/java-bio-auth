package BioAuth.api.dtos.ruralproperties;

import java.util.List;

public record RuralPropertiesListResponseDTO(
	List<RuralPropertiesResponseDTO> ruralProperties
){}