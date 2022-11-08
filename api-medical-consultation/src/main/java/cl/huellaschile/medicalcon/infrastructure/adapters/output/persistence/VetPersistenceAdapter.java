package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence;

import cl.huellaschile.medicalcon.application.ports.output.VetOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.Vet;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.VetEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.VetPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VetPersistenceAdapter implements VetOutputPort {

    private final VetRepository vetRepository;
    private final VetPersistenceMapper vetPersistenceMapper;
    private final MessageSource messages;

    @Override
    public Optional<Vet> getById(String id) {
        return vetRepository.findById(id).map(vetPersistenceMapper::toVet);
    }

    @Override
    public Vet save(Vet vet) {
        final Optional<Vet> optionalVeterinary = getById(vet.getNationalRegister());
        if (optionalVeterinary.isPresent()) {
            final String rspFormatMsg = messages.getMessage("vet.duplicated.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, vet.getNationalRegister());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            VetEntity vetEntity = vetPersistenceMapper.toVetEntity(vet);
            return vetPersistenceMapper.toVet(vetRepository.save(vetEntity));
        }
    }

    @Override
    public void deleteById(String id) {
        final Optional<Vet> optionalVet = getById(id);
        if (optionalVet.isPresent()) {
            vetRepository.deleteById(id);
        } else {
            final String rspFormatMsg = messages.getMessage("vet.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }
}
