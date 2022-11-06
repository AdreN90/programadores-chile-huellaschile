package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.repository;

import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
