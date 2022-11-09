package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationPaymentRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;

public interface UpdateConsultationUseCase {

    ConsultationCreateResponse closeMedicalConsultation(String id);

    ConsultationCreateResponse consultationInTreatment(String id);

    ConsultationCreateResponse payment(String id, ConsultationPaymentRequest request);
}
