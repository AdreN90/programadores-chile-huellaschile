package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.mapper;

import cl.programadoreschile.adrian.userpet.domain.model.Pet;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.PetEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetPersistenceMapper {

    PetEntity toPetEntity(Pet technology);

    Pet toPet(PetEntity petEntity);

    List<Pet> toPets(List<PetEntity> technologies);

}
