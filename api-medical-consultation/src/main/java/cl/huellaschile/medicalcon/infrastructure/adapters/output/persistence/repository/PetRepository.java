package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository;

import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, PetID> {

    Optional<List<PetEntity>> findByIdUserEqualsIgnoreCase(String idUser);

    Optional<List<PetEntity>> findByRaceEqualsIgnoreCase(String race);

    Optional<List<PetEntity>> findByNamePetEqualsIgnoreCase(String name);

}
