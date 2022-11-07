package cl.huellaschile.medicalcon.application.ports.output;

import cl.huellaschile.medicalcon.domain.model.User;

import java.util.Optional;

public interface UserOutputPort {

    Optional<User> getById(String id);

    User save(User user);

    void deleteById(String id);

}
