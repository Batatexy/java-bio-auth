package BioAuth.api.dtos.ruralproperties;

public record RuralPropertiesDetailsResponseDTO(
	Long id,
	String placeName,
	String description,
	String ownerName,
	String address,
	float size,
	String agroChemicals,
	Long agrochemicalsLevelOrder,
	Long levelOrder,
	byte[] image
){}