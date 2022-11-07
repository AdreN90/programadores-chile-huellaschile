package cl.huellaschile.medicalcon.domain.service;

import cl.huellaschile.medicalcon.application.ports.input.CreateUserUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteUserUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetUserUseCase;
import cl.huellaschile.medicalcon.application.ports.output.UserOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements GetUserUseCase, CreateUserUseCase, DeleteUserUseCase {

    private final UserOutputPort userOutputPort;
    private final MessageSource messages;

    public User getById(String idUser) {
        return userOutputPort
                .getById(idUser)
                .orElseThrow(() -> new APIException(
                        String.format(messages.getMessage(
                                "user.not.exist.message",
                                null,
                                LocaleContextHolder.getLocale()), idUser),
                        HttpStatus.NOT_FOUND)
                );
    }

    public User save(User user) {
        return userOutputPort.save(user);
    }

    public void deleteById(String idUser) {
        userOutputPort.deleteById(idUser);
    }
}
