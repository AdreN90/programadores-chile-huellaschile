package cl.programadoreschile.adrian.veterinary.persistence.mapper;

import cl.programadoreschile.adrian.veterinary.domain.entities.VetDTO;
import cl.programadoreschile.adrian.veterinary.persistence.models.VetDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VetMapper {

    VetDAO toVetDAO(VetDTO vet);

    VetDTO toVetDTO(VetDAO vet);
}
