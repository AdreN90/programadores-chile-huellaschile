package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository;

import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.ConsultationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends CrudRepository<ConsultationEntity, String> {
    Boolean existsByNamePetEqualsIgnoreCaseAndStatusEqualsIgnoreCase(String namePet, String status);

    Optional<List<ConsultationEntity>> findByNamePetEqualsIgnoreCase(String namePet);
}
