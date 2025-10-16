package BioAuth.api.dtos.user;

import java.util.List;

public record UserFindByFingerPrintsDTO(
	List<byte[]> fingerPrints
){}