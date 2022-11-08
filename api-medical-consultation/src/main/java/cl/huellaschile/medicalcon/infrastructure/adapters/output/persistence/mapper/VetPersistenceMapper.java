package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper;

import cl.huellaschile.medicalcon.domain.model.Vet;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.VetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VetPersistenceMapper {

    VetEntity toVetEntity(Vet vet);

    Vet toVet(VetEntity vetEntity);
}
