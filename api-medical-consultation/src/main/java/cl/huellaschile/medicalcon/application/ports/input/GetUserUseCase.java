package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.User;

public interface GetUserUseCase {
    User getById(String idUser);
}
