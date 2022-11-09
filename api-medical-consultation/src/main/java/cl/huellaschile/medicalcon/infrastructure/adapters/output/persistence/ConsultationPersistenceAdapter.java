package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence;

import cl.huellaschile.medicalcon.application.ports.output.ConsultationOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.mapper.ConsultationRestMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.ConsultationEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsultationPersistenceAdapter implements ConsultationOutputPort {

    private final ConsultationRepository consultationRepository;
    private final ConsultationRestMapper consultationRestMapper;
    private final MessageSource messages;

    @Override
    public ConsultationCreateResponse save(ConsultationCreateRequest consultation) {
        final boolean petExistInOpenConsultation = consultationRepository
                .existsByNamePetEqualsIgnoreCaseAndStatusEqualsIgnoreCase(consultation.getNamePet(), "open");
        if (petExistInOpenConsultation) {
            final String rspFormatMsg = messages.getMessage("consultation.pet.exist", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, consultation.getConsultationId(), consultation.getNamePet());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            final Optional<List<ConsultationEntity>> consultationEntityList = consultationRepository.findByNamePetEqualsIgnoreCase(consultation.getNamePet());
            if (consultationEntityList.isPresent() && !consultationEntityList.get().isEmpty()) {
                ConsultationEntity consultationEntity = consultationEntityList.get().stream().findFirst().get();
                if (consultationEntity.getNationalRegister().equalsIgnoreCase(consultation.getNationalRegister())) {
                    return doSave(consultation);
                } else {
                    final String rspFormatMsg = messages.getMessage("consultation.cant.create.other.vet", null, LocaleContextHolder.getLocale());
                    throw new APIException(rspFormatMsg, HttpStatus.CONFLICT);
                }
            } else {
                return doSave(consultation);
            }
        }
    }

    @Override
    public void deleteById(String id) {
        Optional<ConsultationEntity> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            if (consultation.get().getStatus().equalsIgnoreCase("open") || consultation.get().getStatus().equalsIgnoreCase("treatment")) {
                final String rspFormatMsg = messages.getMessage("consultation.not.exist.message", null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, id);
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                consultationRepository.deleteById(id);
            }
        } else {
            final String rspFormatMsg = messages.getMessage("consultation.cant.delete.status", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    private ConsultationCreateResponse doSave(ConsultationCreateRequest consultation) {
        ConsultationEntity consultationEntity = consultationRestMapper.toConsultationEntity(consultation);
        return consultationRestMapper.toConsultationCreateResponse(consultationRepository.save(consultationEntity));
    }
}
