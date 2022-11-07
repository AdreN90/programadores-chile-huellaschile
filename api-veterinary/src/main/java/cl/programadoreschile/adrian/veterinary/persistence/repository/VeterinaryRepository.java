package cl.programadoreschile.adrian.veterinary.persistence.repository;

import cl.programadoreschile.adrian.veterinary.domain.gateways.VeterinaryGateway;
import cl.programadoreschile.adrian.veterinary.error.APIException;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import cl.programadoreschile.adrian.veterinary.persistence.repository.mongo.VeterinaryMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VeterinaryRepository implements VeterinaryGateway {

    private final VeterinaryMongoRepository veterinaryMongoRepository;
    private final MessageSource messages;

    @Override
    public VeterinaryDAO createVeterinary(VeterinaryDAO newVeterinary) {
        VeterinaryDAO veterinary = findByProfessionalLicenseNumber(newVeterinary.getProfessionalLicenseNumber());
        if (veterinary != null) {
            final String rspFormatMsg = messages.getMessage("veterinary.duplicated.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, newVeterinary.getProfessionalLicenseNumber());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            return veterinaryMongoRepository.save(newVeterinary);
        }
    }

    @Override
    public VeterinaryDAO findByProfessionalLicenseNumber(String professionalLicenseNumber) {
        return veterinaryMongoRepository.findByProfessionalLicenseNumberEqualsIgnoreCase(professionalLicenseNumber);
    }

    @Override
    public void delete(String professionalLicenseNumber) {
        VeterinaryDAO veterinary = findByProfessionalLicenseNumber(professionalLicenseNumber);
        if (veterinary == null) {
            final String rspFormatMsg = messages.getMessage("veterinary.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, professionalLicenseNumber);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            veterinaryMongoRepository
                    .deleteVeterinaryDAOByProfessionalLicenseNumberEqualsIgnoreCase(professionalLicenseNumber);
        }
    }
}
