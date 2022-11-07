package cl.programadoreschile.adrian.veterinary.persistence.repository.mongo;

import cl.programadoreschile.adrian.veterinary.persistence.models.VetDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetMongoRepository extends MongoRepository<VetDAO, Long> {

    VetDAO findByNationalRegisterEqualsIgnoreCase(String nationalRegister);
}
