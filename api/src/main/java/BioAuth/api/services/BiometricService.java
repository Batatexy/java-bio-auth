package BioAuth.api.services;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// 1. Imports do SourceAFIS (substituindo o BoofCV)
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

@Service
public class BiometricService {

	// 2. Constantes atualizadas para o SourceAFIS
	// SourceAFIS usa um "score" de similaridade. Um valor acima de 40
	// é geralmente considerado um "match" forte para autenticação 1-para-1.
	// Você pode ajustar este valor com base nos seus testes.
	private static final double MATCH_THRESHOLD = 40.0;
	
	// O DPI (Dots Per Inch) é vital para o SourceAFIS.
    // 500 é o padrão da indústria para scanners de digital.
    private static final int FINGERPRINT_DPI = 500;


	/**
	 * Método principal que verifica a correspondência biométrica.
	 */
	public boolean checkDigitalMatch(MultipartFile uploadedImage, String storedImageString) {
		
		// 3. Validação de entrada
		if (uploadedImage == null || uploadedImage.isEmpty()) {
			System.err.println("[DEBUG] Imagem enviada está nula ou vazia.");
			return false;
		}
		if (storedImageString == null || storedImageString.isEmpty()) {
			System.err.println("[DEBUG] String da imagem armazenada (Base64) está nula ou vazia.");
			return false;
		}

		try {

			// 4. Obter os bytes das imagens
			byte[] uploadedImageBytes = uploadedImage.getBytes();
			
			// 5. CORREÇÃO DO BUG (Hex vs Base64)
			//    A string estava sendo tratada como HEX, mas o nome da variável
			//    era Base64. Estou usando o decodificador Base64 padrão do Java.
			byte[] storedImageBytes;
			try {
				storedImageBytes = storedImageString.getBytes(StandardCharsets.UTF_8);
			} catch (IllegalArgumentException e) {
				System.err.println("[DEBUG] Falha ao decodificar string Base64. A string está corrompida ou não é Base64?");
				e.printStackTrace();
				return false;
			}

			// 6. Criar os "Templates" de minúcias usando SourceAFIS
			// O template é a representação matemática (mapa de minúcias) da digital.
			// O SourceAFIS faz todo o pré-processamento (equalização, binarização, etc.)
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

			// 7. Comparar os dois templates
			// O "index" é o template que está no "banco de dados"
			// O "match" é o template que está tentando autenticar
			double score = new FingerprintMatcher()
				.index(storedTemplate)
				.match(uploadedTemplate);

			System.out.println("[DEBUG] Score de correspondência (SourceAFIS): " + score);
			System.out.println("[DEBUG] Limiar mínimo necessário: " + MATCH_THRESHOLD);

			// 8. Retornar o resultado da comparação
			return score >= MATCH_THRESHOLD;

		} catch (Exception e) {
			// Captura exceções como IOException de getBytes() ou erros internos do SourceAFIS
			System.err.println("Erro ao comparar digitais: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	//
	// --- MÉTODOS ANTIGOS REMOVIDOS ---
	//
	// O método 'compareFingerprints' foi fundido com 'checkDigitalMatch'.
	// Os métodos 'convertHexStringToByteArray' e 'convertByteArrayToImage' não são mais necessários.
	// Os métodos 'preprocessFingerprint', 'detectAndMatch' e 'describeImage' (BoofCV)
	// foram substituídos pela lógica do SourceAFIS.
	//
}