package cl.huellaschile.medicalcon.domain.service;

import cl.huellaschile.medicalcon.application.ports.input.CreateConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.input.DeleteConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.input.GetConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.input.UpdateConsultationUseCase;
import cl.huellaschile.medicalcon.application.ports.output.ConsultationOutputPort;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationPaymentRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationGetResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultationService implements GetConsultationUseCase, CreateConsultationUseCase, UpdateConsultationUseCase, DeleteConsultationUseCase {

    private final ConsultationOutputPort consultationOutputPort;

    @Override
    public ConsultationCreateResponse save(ConsultationCreateRequest consultationCreateRequest) {
        return consultationOutputPort.save(consultationCreateRequest);
    }

    @Override
    public void deleteById(String id) {
        consultationOutputPort.deleteById(id);
    }

    @Override
    public ConsultationGetResponse findById(String id) {
        return consultationOutputPort.findById(id);
    }

    @Override
    public ConsultationCreateResponse closeMedicalConsultation(String id) {
        return consultationOutputPort.closeMedicalConsultation(id);
    }

    @Override
    public ConsultationCreateResponse consultationInTreatment(String id) {
        return consultationOutputPort.consultationInTreatment(id);
    }

    @Override
    public ConsultationCreateResponse payment(String id, ConsultationPaymentRequest request) {
        return consultationOutputPort.payment(id, request);
    }
}
