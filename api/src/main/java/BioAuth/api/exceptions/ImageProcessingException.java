package BioAuth.api.exceptions;

public class ImageProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImageProcessingException() {
		super();
	}

	public ImageProcessingException(String message) {
		super(message);
	}
}