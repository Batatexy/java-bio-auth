package BioAuth.api.mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import BioAuth.api.dtos.role.RoleResponseDTO;
import BioAuth.api.dtos.user.UserResponseDTO;
import BioAuth.api.dtos.userrole.UserRoleProjection;
import BioAuth.api.dtos.userrole.UserRoleResponseDTO;

@Component
public class UserRoleMapper {

	public UserRoleResponseDTO toDTO(List<UserRoleProjection> userRoleProjection) {

		if (userRoleProjection == null || userRoleProjection.isEmpty()) {
			return null;
		}

		UserRoleProjection first = userRoleProjection.get(0);

		java.util.function.Function<String, byte[]> hexToByteArray = hex -> {
			if (hex == null || hex.isEmpty())
				return null;
			return hexStringToByteArray(hex);
		};

		List<byte[]> digitalImages = Arrays.asList(hexToByteArray.apply(first.getDigitalImage1()),
				hexToByteArray.apply(first.getDigitalImage2()), hexToByteArray.apply(first.getDigitalImage3()),
				hexToByteArray.apply(first.getDigitalImage4()), hexToByteArray.apply(first.getDigitalImage5()),
				hexToByteArray.apply(first.getDigitalImage6()));

		byte[] userImageBytes = hexToByteArray.apply(first.getImage());

		UserResponseDTO userResponseDTO = new UserResponseDTO(first.getUserId(), first.getFullName(), first.getEmail(),
				userImageBytes, digitalImages
		);

		List<RoleResponseDTO> roleResponseList = userRoleProjection.stream()
				.map(userRole -> new RoleResponseDTO(userRole.getRoleId(), userRole.getName(),
						userRole.getDescription(), userRole.getLevelOrder()))
				.collect(Collectors.toList());

		return new UserRoleResponseDTO(userResponseDTO, roleResponseList);
	}
	
	public static byte[] hexStringToByteArray(String hex) {
		if (hex == null || hex.isEmpty()) {
			return null;
		}
		String cleanHex = hex.replaceAll("\\s", "");

		int len = cleanHex.length();

		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// Converte dois caracteres hexadecimais em um byte
			data[i / 2] = (byte) ((Character.digit(cleanHex.charAt(i), 16) << 4)
					+ Character.digit(cleanHex.charAt(i + 1), 16));
		}
		return data;
	}
	
	
	
	
//	public UserRoleResponseDTO toDTO(List<UserRoleProjection> userRoleProjection) {
//
//		List<byte[]> digitalImages = Arrays.asList(userRoleProjection.get(0).getDigitalImage1(),
//				userRoleProjection.get(0).getDigitalImage2(), userRoleProjection.get(0).getDigitalImage3(),
//				userRoleProjection.get(0).getDigitalImage4(), userRoleProjection.get(0).getDigitalImage5(),
//				userRoleProjection.get(0).getDigitalImage6());
//
//		UserResponseDTO userResponseDTO = new UserResponseDTO(userRoleProjection.get(0).getUserId(),
//				userRoleProjection.get(0).getFullName(), userRoleProjection.get(0).getEmail(),
//				userRoleProjection.get(0).getImage(), digitalImages);
//
//		List<RoleResponseDTO> roleResponseList = userRoleProjection.stream()
//				.map(userRole -> new RoleResponseDTO(userRole.getRoleId(), userRole.getName(),
//						userRole.getDescription(), userRole.getLevelOrder()))
//				.collect(Collectors.toList());
//
//		return new UserRoleResponseDTO(userResponseDTO, roleResponseList);
//	}

}
