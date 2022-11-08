package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence;

import cl.huellaschile.medicalcon.application.ports.output.VeterinaryOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.Veterinary;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.VeterinaryEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.VeterinaryPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.VeterinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VeterinaryPersistenceAdapter implements VeterinaryOutputPort {

    private final VeterinaryRepository veterinaryRepository;
    private final VeterinaryPersistenceMapper veterinaryPersistenceMapper;
    private final MessageSource messages;

    @Override
    public Optional<Veterinary> getById(String id) {
        return veterinaryRepository.findById(id).map(veterinaryPersistenceMapper::toVeterinary);
    }

    @Override
    public Veterinary save(Veterinary veterinary) {
        final Optional<Veterinary> optionalVeterinary = getById(veterinary.getProfessionalLicenseNumber());
        if (optionalVeterinary.isPresent()) {
            final String rspFormatMsg = messages.getMessage("veterinary.duplicated.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, veterinary.getProfessionalLicenseNumber());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            VeterinaryEntity veterinaryEntity = veterinaryPersistenceMapper.toVeterinaryEntity(veterinary);
            return veterinaryPersistenceMapper.toVeterinary(veterinaryRepository.save(veterinaryEntity));
        }
    }

    @Override
    public void deleteById(String id) {
        final Optional<Veterinary> optionalVeterinary = getById(id);
        if (optionalVeterinary.isPresent()) {
            veterinaryRepository.deleteById(id);
        } else {
            final String rspFormatMsg = messages.getMessage("veterinary.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }
}
