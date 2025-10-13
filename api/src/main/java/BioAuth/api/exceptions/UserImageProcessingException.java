package BioAuth.api.exceptions;

public class UserImageProcessingException extends ImageProcessingException {

	private static final long serialVersionUID = 1L;

	public UserImageProcessingException() {
		super("Falha ao ler os bytes da imagem do usuário");
	}

	public UserImageProcessingException(String customMessage) {
		super(customMessage);
	}
}