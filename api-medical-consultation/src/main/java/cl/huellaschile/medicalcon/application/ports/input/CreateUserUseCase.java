package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.User;

public interface CreateUserUseCase {

    User save(User user);
}
