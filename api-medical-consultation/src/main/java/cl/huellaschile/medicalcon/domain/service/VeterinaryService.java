package cl.huellaschile.medicalcon.domain.service;

import cl.huellaschile.medicalcon.application.ports.input.CreateVeterinaryUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteVeterinaryUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetVeterinaryUseCase;
import cl.huellaschile.medicalcon.application.ports.output.VeterinaryOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.Veterinary;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VeterinaryService implements GetVeterinaryUseCase, CreateVeterinaryUseCase, DeleteVeterinaryUseCase {

    private final VeterinaryOutputPort veterinaryOutputPort;
    private final MessageSource messages;

    public Veterinary getById(String id) {
        return veterinaryOutputPort.
                getById(id)
                .orElseThrow(() -> new APIException(
                        messages.getMessage("veterinary.not.exist.message", null, LocaleContextHolder.getLocale()),
                        HttpStatus.NOT_FOUND));
    }

    public Veterinary save(Veterinary veterinary) {
        return veterinaryOutputPort.save(veterinary);
    }

    public void deleteById(String id) {
        veterinaryOutputPort.deleteById(id);
    }
}
