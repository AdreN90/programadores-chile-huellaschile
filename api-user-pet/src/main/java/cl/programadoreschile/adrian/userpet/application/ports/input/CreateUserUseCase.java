package cl.programadoreschile.adrian.userpet.application.ports.input;

import cl.programadoreschile.adrian.userpet.domain.model.User;

public interface CreateUserUseCase {

    User save(User user);
}
