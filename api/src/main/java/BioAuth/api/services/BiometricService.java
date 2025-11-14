package BioAuth.api.services;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

@Service
public class BiometricService {
	private static final double MATCH_THRESHOLD = 40.0;
	private static final int FINGERPRINT_DPI = 500;

	public boolean checkDigitalMatch(MultipartFile uploadedImage, String storedImageString) {

		if (uploadedImage == null || uploadedImage.isEmpty()) {
			System.err.println("[DEBUG] Imagem enviada está nula ou vazia.");
			return false;
		}
		if (storedImageString == null || storedImageString.isEmpty()) {
			System.err.println("[DEBUG] String da imagem armazenada (Base64) está nula ou vazia.");
			return false;
		}

		try {
			byte[] uploadedImageBytes = uploadedImage.getBytes();

			byte[] storedImageBytes;
			try {
				storedImageBytes = storedImageString.getBytes(StandardCharsets.UTF_8);
			} catch (IllegalArgumentException e) {
				System.err.println("[DEBUG] Falha ao decodificar string Base64. A string está corrompida ou não é Base64?");
				e.printStackTrace();
				return false;
			}

			// automaticamente e de forma correta.
			FingerprintTemplate uploadedTemplate = new FingerprintTemplate()
				.dpi(FINGERPRINT_DPI)
				.create(uploadedImageBytes);

			FingerprintTemplate storedTemplate = new FingerprintTemplate()
				.dpi(FINGERPRINT_DPI)
				.create(storedImageBytes);

			if (uploadedTemplate == null || storedTemplate == null) {
				 System.err.println("[DEBUG] Falha ao criar template de minúcias. A imagem é inválida ou não é uma digital?");
				 return false;
			}

			double score = new FingerprintMatcher()
				.index(storedTemplate)
				.match(uploadedTemplate);

			System.out.println("[DEBUG] Score de correspondência (SourceAFIS): " + score);
			System.out.println("[DEBUG] Limiar mínimo necessário: " + MATCH_THRESHOLD);

			return score >= MATCH_THRESHOLD;

		} catch (Exception e) {
			System.err.println("Erro ao comparar digitais: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}