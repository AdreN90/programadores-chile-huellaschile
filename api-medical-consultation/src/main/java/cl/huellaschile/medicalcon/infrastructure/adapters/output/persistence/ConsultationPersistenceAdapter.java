package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence;

import cl.huellaschile.medicalcon.application.ports.output.ConsultationOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.Pet;
import cl.huellaschile.medicalcon.domain.model.User;
import cl.huellaschile.medicalcon.domain.model.Vet;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationPaymentRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationGetResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.mapper.ConsultationRestMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.ConsultationEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.PetPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.VetPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.ConsultationRepository;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.PetRepository;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.UserRepository;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsultationPersistenceAdapter implements ConsultationOutputPort {

    private final ConsultationRepository consultationRepository;
    private final ConsultationRestMapper consultationRestMapper;
    private final PetRepository petRepository;
    private final PetPersistenceMapper petPersistenceMapper;
    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    private final VetRepository vetRepository;
    private final VetPersistenceMapper vetPersistenceMapper;
    private final MessageSource messages;

    @Override
    public ConsultationGetResponse findById(String id) {
        Optional<ConsultationEntity> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            final Pet pet = getPetByName(consultation.get());
            final User user = getUserById(consultation.get().getIdUser());
            final Vet vet = getVetById(consultation.get().getNationalRegister());
            return new ConsultationGetResponse()
                    .setConsultationId(id)
                    .setUser(user)
                    .setPet(pet)
                    .setVet(vet)
                    .setStatus(consultation.get().getStatus())
                    .setPrice(consultation.get().getPrice())
                    .setMethodPayment(consultation.get().getMethodPayment())
                    .setPaidOut(consultation.get().getPaidOut())
                    .setProcess(consultation.get().getProcess());
        } else {
            final String rspFormatMsg = messages.getMessage("consultation.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

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
    public ConsultationCreateResponse closeMedicalConsultation(String id) {
        Optional<ConsultationEntity> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            return validateCloseMedicalConsultation(consultation.get());

        } else {
            final String rspFormatMsg = messages.getMessage("consultation.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public ConsultationCreateResponse consultationInTreatment(String id) {
        Optional<ConsultationEntity> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            return updateStatus(consultation.get(), "treatment");
        } else {
            final String rspFormatMsg = messages.getMessage("consultation.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public ConsultationCreateResponse payment(String id, ConsultationPaymentRequest request) {
        Optional<ConsultationEntity> consultationEntity = consultationRepository.findById(id);
        if (consultationEntity.isPresent()) {
            if (consultationEntity.get().getStatus().equalsIgnoreCase("close")) {
                final String rspFormatMsg = messages.getMessage("consultation.cant.pay.status.close", null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, id);
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                consultationEntity.get().setMethodPayment(request.getMethodPayment());
                consultationEntity.get().setPrice(request.getPrice());
                consultationEntity.get().setPaidOut(true);
                return consultationRestMapper.toConsultationCreateResponse(consultationRepository.save(consultationEntity.get()));
            }
        } else {
            final String rspFormatMsg = messages.getMessage("consultation.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteById(String id) {
        Optional<ConsultationEntity> consultation = consultationRepository.findById(id);
        if (consultation.isPresent()) {
            if (consultation.get().getStatus().equalsIgnoreCase("open") || consultation.get().getStatus().equalsIgnoreCase("treatment")) {
                final String rspFormatMsg = messages.getMessage("consultation.cant.delete.status", null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, id);
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                consultationRepository.deleteById(id);
            }
        } else {
            final String rspFormatMsg = messages.getMessage("consultation.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    private ConsultationCreateResponse updateStatus(ConsultationEntity consultationEntity, String status) {
        consultationEntity.setStatus(status);
        return consultationRestMapper.toConsultationCreateResponse(consultationRepository.save(consultationEntity));
    }

    private ConsultationCreateResponse doSave(ConsultationCreateRequest consultation) {
        ConsultationEntity consultationEntity = consultationRestMapper.toConsultationEntity(consultation);
        return consultationRestMapper.toConsultationCreateResponse(consultationRepository.save(consultationEntity));
    }

    private ConsultationCreateResponse validateCloseMedicalConsultation(ConsultationEntity consultationEntity) {
        if (consultationEntity.getStatus().equalsIgnoreCase("close")) {
            final String rspFormatMsg = messages.getMessage("consultation.already.paid.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, consultationEntity.getConsultationId());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else if (!consultationEntity.getPaidOut()) {
            final String rspFormatMsg = messages.getMessage("consultation.not.be.paid.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, consultationEntity.getConsultationId());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            final Pet pet = getPetByName(consultationEntity);
            if (pet.getTreatmentActive()) {
                final String rspFormatMsg = messages.getMessage("consultation.pet.in.treatment.message", null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, consultationEntity.getConsultationId(), pet.getNamePet());
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                return updateStatus(consultationEntity, "close");
            }
        }
    }

    private Pet getPetByName(ConsultationEntity consultationEntity) {
        final Optional<List<PetEntity>> pets = petRepository.findByNamePetEqualsIgnoreCase(consultationEntity.getNamePet());
        final List<Pet> petList = pets.map(petPersistenceMapper::toPets).orElse(new ArrayList<>());
        return petList.stream().findFirst().orElse(new Pet());
    }

    private User getUserById(String id) {
        return userRepository.findById(id).map(userPersistenceMapper::toUser).orElse(new User());
    }

    public Vet getVetById(String id) {
        return vetRepository.findById(id).map(vetPersistenceMapper::toVet).orElse(new Vet());
    }
}
