package cl.programadoreschile.adrian.veterinary.persistence.repository;

import cl.programadoreschile.adrian.veterinary.domain.entities.VeterinaryDTO;
import cl.programadoreschile.adrian.veterinary.domain.gateways.VeterinaryGateway;
import cl.programadoreschile.adrian.veterinary.error.APIException;
import cl.programadoreschile.adrian.veterinary.persistence.mapper.VeterinaryMapper;
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
    private final VeterinaryMapper veterinaryMapper;
    private final MessageSource messages;

    @Override
    public VeterinaryDTO createVeterinary(VeterinaryDTO newVeterinary) {
        VeterinaryDTO veterinary = findByProfessionalLicenseNumber(newVeterinary.getProfessionalLicenseNumber());
        if (veterinary != null) {
            final String rspFormatMsg = messages.getMessage("veterinary.duplicated.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, newVeterinary.getProfessionalLicenseNumber());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            return veterinaryMapper.toVeterinaryDTO(veterinaryMongoRepository
                    .save(veterinaryMapper.toVeterinaryDAO(newVeterinary)));
        }
    }

    @Override
    public VeterinaryDTO findByProfessionalLicenseNumber(String professionalLicenseNumber) {
        final VeterinaryDAO veterinary = veterinaryMongoRepository.findByProfessionalLicenseNumberEqualsIgnoreCase(professionalLicenseNumber);
        if (veterinary == null) {
            final String rspFormatMsg = messages.getMessage("veterinary.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, professionalLicenseNumber);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            return veterinaryMapper.toVeterinaryDTO(veterinary);
        }

    }

    @Override
    public VeterinaryDTO delete(String professionalLicenseNumber) {
        findByProfessionalLicenseNumber(professionalLicenseNumber);
        return veterinaryMapper.toVeterinaryDTO(veterinaryMongoRepository
                .deleteVeterinaryDAOByProfessionalLicenseNumberEqualsIgnoreCase(professionalLicenseNumber));

    }
}
