package cl.huellaschile.medicalcon.application.ports.output;

import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;

public interface ConsultationOutputPort {

    ConsultationCreateResponse save(ConsultationCreateRequest consultationCreateRequest);

    void deleteById(String id);
}
