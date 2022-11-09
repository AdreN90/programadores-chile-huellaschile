package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;

public interface CreateConsultationUseCase {

    ConsultationCreateResponse save(ConsultationCreateRequest consultation);
}
