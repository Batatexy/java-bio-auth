package BioAuth.api.exceptions;

public class RuralPropertiesImageProcessingException extends ImageProcessingException {

	private static final long serialVersionUID = 1L;

	public RuralPropertiesImageProcessingException() {
		super("Falha ao ler os bytes da imagem da propriedade rural");
	}

	public RuralPropertiesImageProcessingException(String customMessage) {
		super(customMessage);
	}
}