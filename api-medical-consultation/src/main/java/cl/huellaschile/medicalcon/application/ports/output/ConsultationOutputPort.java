package cl.huellaschile.medicalcon.application.ports.output;

import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationPaymentRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationGetResponse;

public interface ConsultationOutputPort {

    ConsultationGetResponse findById(String id);

    ConsultationCreateResponse save(ConsultationCreateRequest consultationCreateRequest);

    ConsultationCreateResponse closeMedicalConsultation(String id);

    ConsultationCreateResponse consultationInTreatment(String id);

    ConsultationCreateResponse payment(String id, ConsultationPaymentRequest request);

    void deleteById(String id);
}
