package BioAuth.api.exceptions;

public class UserNotFound extends NotFoundException {

	private static final long serialVersionUID = 1L;

	public UserNotFound() {
		super("Usuário não encontrado");
	}
}
