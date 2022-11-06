package cl.programadoreschile.adrian.userpet.application.ports.output;

import cl.programadoreschile.adrian.userpet.domain.model.User;

import java.util.Optional;

public interface UserOutputPort {

    Optional<User> getById(String id);

    User save(User user);

    void deleteById(String id);

}
