package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository;

import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
