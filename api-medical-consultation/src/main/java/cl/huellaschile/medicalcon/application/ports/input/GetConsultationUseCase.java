package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationGetResponse;

public interface GetConsultationUseCase {
    ConsultationGetResponse findById(String id);
}
