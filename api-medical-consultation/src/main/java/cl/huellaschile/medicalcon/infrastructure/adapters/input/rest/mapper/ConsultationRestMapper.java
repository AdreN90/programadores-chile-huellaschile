package cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.mapper;

import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request.ConsultationCreateRequest;
import cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response.ConsultationCreateResponse;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.ConsultationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationRestMapper {

    ConsultationEntity toConsultationEntity(ConsultationCreateRequest consultationCreateRequest);
    ConsultationCreateResponse toConsultationCreateResponse(ConsultationEntity consultationEntity);
}
