package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence;

import cl.huellaschile.medicalcon.application.ports.output.ConsultationOutputPort;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.mapper.ConsultationRestMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.ConsultationEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConsultationPersistenceAdapter implements ConsultationOutputPort {

    private final ConsultationRepository consultationRepository;
    private final ConsultationRestMapper consultationRestMapper;

    @Override
    public ConsultationCreateResponse save(ConsultationCreateRequest consultation) {
        ConsultationEntity consultationEntity = consultationRestMapper.toConsultationEntity(consultation);
        return consultationRestMapper.toConsultationCreateResponse(consultationRepository.save(consultationEntity));
    }

    @Override
    public void deleteById(String id) {
        consultationRepository.deleteById(id);
    }
}
