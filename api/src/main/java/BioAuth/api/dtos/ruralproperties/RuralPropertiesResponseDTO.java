package BioAuth.api.dtos.ruralproperties;

public record RuralPropertiesResponseDTO(
	Long id,
	String placeName,
	String ownerName,
	String address,
	float size,
	String agroChemicals,
	Long agrochemicalsLevelOrder,
	Long levelOrder,
	byte[] image
){}