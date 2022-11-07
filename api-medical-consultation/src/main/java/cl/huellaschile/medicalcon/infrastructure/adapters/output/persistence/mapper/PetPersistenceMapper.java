package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper;

import cl.huellaschile.medicalcon.domain.model.Pet;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPersistenceMapper {

    PetEntity toPetEntity(Pet technology);

    Pet toPet(PetEntity petEntity);

    List<Pet> toPets(List<PetEntity> technologies);

}
