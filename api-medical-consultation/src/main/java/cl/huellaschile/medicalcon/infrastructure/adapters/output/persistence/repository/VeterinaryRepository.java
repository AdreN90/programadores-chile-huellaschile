package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository;

import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.VeterinaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryRepository extends CrudRepository<VeterinaryEntity, String> {
}
