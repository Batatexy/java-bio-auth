package BioAuth.api.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import BioAuth.api.dtos.ruralproperties.RuralPropertiesCreateDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesDetailsResponseDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesListResponseDTO;
import BioAuth.api.dtos.ruralproperties.RuralPropertiesResponseDTO;
import BioAuth.api.entities.RuralProperties;
import BioAuth.api.exceptions.RuralPropertiesImageProcessingException;
import BioAuth.api.mappers.RuralPropertiesMapper;
import BioAuth.api.repositories.RuralPropertiesRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class RuralPropertiesService {

	private final RuralPropertiesRepository ruralPropertiesRepository;
	private final RuralPropertiesMapper ruralPropertiesMapper;

	public RuralPropertiesService(RuralPropertiesRepository ruralPropertiesRepository,
			RuralPropertiesMapper ruralPropertiesMapper) {
		super();
		this.ruralPropertiesRepository = ruralPropertiesRepository;
		this.ruralPropertiesMapper = ruralPropertiesMapper;
	}

	public RuralPropertiesListResponseDTO list() {
		List<RuralPropertiesResponseDTO> ruralPropertiess = ruralPropertiesRepository.findAll().stream()
				.map(ruralPropertiesMapper::toDTO).toList();
		return new RuralPropertiesListResponseDTO(ruralPropertiess);
	}

	public RuralPropertiesDetailsResponseDTO findById(@NotNull Long id) {
		return ruralPropertiesMapper.toDetailsDTO(ruralPropertiesRepository.findById(id).get());
	}

	public Optional<RuralProperties> findRuralPropertiesById(@NotNull Long id) {
		return ruralPropertiesRepository.findById(id);
	}

	@Transactional
	public RuralPropertiesResponseDTO create(@Valid RuralPropertiesCreateDTO ruralPropertiesCreateDTO,
			MultipartFile image) {
		byte[] imageBytes = null;
		if (image != null) { imageBytes = extractImageBytes(image); }
		return ruralPropertiesMapper.toDTO(ruralPropertiesRepository
				.save(ruralPropertiesMapper.toEntity(ruralPropertiesCreateDTO, imageBytes)));
	}
	
//	@Transactional
//	public RuralPropertiesResponseDTO update(@NotNull Long id, @Valid RuralPropertiesUpdateDTO ruralPropertiesUpdateDTO,
//			MultipartFile image) {
//		RuralProperties ruralProperties = ruralPropertiesRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("RuralPropertiesRole não encontrado"));
//
//		byte[] imageBytes = null;
//		if (image != null) { imageBytes = extractImageBytes(image); }
//
//		ruralProperties.setFullName(ruralPropertiesUpdateDTO.fullName());
//		ruralProperties.setImage(imageBytes);
//
//		return ruralPropertiesMapper.toDTO(ruralPropertiesRepository.save(ruralProperties));
//	}

	private byte[] extractImageBytes(MultipartFile image) {
		try {
			return image.getBytes();
		} catch (IOException e) {
			throw new RuralPropertiesImageProcessingException();
		}
	}
}
