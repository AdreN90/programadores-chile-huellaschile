package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper;

import cl.huellaschile.medicalcon.domain.model.Veterinary;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.VeterinaryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeterinaryPersistenceMapper {

    VeterinaryEntity toVeterinaryEntity(Veterinary veterinary);

    Veterinary toVeterinary(VeterinaryEntity veterinaryEntity);
}
