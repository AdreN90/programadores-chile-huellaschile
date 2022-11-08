package cl.huellaschile.medicalcon.domain.service;

import cl.huellaschile.medicalcon.application.ports.input.CreateVetUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteVetUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetVetUseCase;
import cl.huellaschile.medicalcon.application.ports.output.VetOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.Vet;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VetService implements GetVetUseCase, CreateVetUseCase, DeleteVetUseCase {

    private final VetOutputPort vetOutputPort;
    private final MessageSource messages;

    public Vet getById(String id) {
        final String rspFormatMsg = messages.getMessage("vet.not.exist.message", null, LocaleContextHolder.getLocale());
        final String responseMessage = String.format(rspFormatMsg, id);
        return vetOutputPort.
                getById(id)
                .orElseThrow(() -> new APIException(responseMessage, HttpStatus.NOT_FOUND));
    }

    public Vet save(Vet vet) {
        return vetOutputPort.save(vet);
    }

    public void deleteById(String id) {
        vetOutputPort.deleteById(id);
    }
}
