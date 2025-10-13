package BioAuth.api.mappers;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.ruralproperties.RuralPropertiesCreateDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesDetailsResponseDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesResponseDTO;
import BioAuth.api.entities.RuralProperties;

@Component
public class RuralPropertiesMapper {
	public RuralPropertiesResponseDTO toDTO(RuralProperties RuralProperties) {
		return new RuralPropertiesResponseDTO(
				RuralProperties.getId(), 
				RuralProperties.getPlaceName(),
				RuralProperties.getOwnerName(), 
				RuralProperties.getAddress(),
				RuralProperties.getSize(),
				RuralProperties.getAgroChemicals(),
				RuralProperties.getAgrochemicalsLevelOrder(),
				RuralProperties.getLevelOrder(),
				RuralProperties.getImage()
		);
	}

	public RuralPropertiesDetailsResponseDTO toDetailsDTO(RuralProperties RuralProperties) {
		return new RuralPropertiesDetailsResponseDTO(
				RuralProperties.getId(), 
				RuralProperties.getPlaceName(),
				RuralProperties.getDescription(),
				RuralProperties.getOwnerName(), 
				RuralProperties.getAddress(),
				RuralProperties.getSize(),
				RuralProperties.getAgroChemicals(),
				RuralProperties.getAgrochemicalsLevelOrder(),
				RuralProperties.getLevelOrder(),
				RuralProperties.getImage()
		);
	}

	public RuralProperties toEntity(RuralPropertiesCreateDTO RuralPropertiesCreateDTO, byte[] imageBytes) {
		return new RuralProperties(
				RuralPropertiesCreateDTO.placeName(), 
				RuralPropertiesCreateDTO.description(),
				RuralPropertiesCreateDTO.ownerName(), 
				RuralPropertiesCreateDTO.address(), 
				RuralPropertiesCreateDTO.size(), 
				RuralPropertiesCreateDTO.agroChemicals(), 
				RuralPropertiesCreateDTO.agrochemicalsLevelOrder(), 
				RuralPropertiesCreateDTO.levelOrder(), 
				imageBytes);
	}

}
