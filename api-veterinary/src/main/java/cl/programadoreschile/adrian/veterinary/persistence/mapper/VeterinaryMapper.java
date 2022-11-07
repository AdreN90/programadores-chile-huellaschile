package cl.programadoreschile.adrian.veterinary.persistence.mapper;

import cl.programadoreschile.adrian.veterinary.domain.entities.VeterinaryDTO;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeterinaryMapper {

    VeterinaryDAO toVeterinaryDAO(VeterinaryDTO veterinary);

    VeterinaryDTO toVeterinaryDTO(VeterinaryDAO veterinary);
}
