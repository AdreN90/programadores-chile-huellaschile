package cl.programadoreschile.adrian.veterinary.persistence.repository.mongo;

import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryMongoRepository extends MongoRepository<VeterinaryDAO, String> {
    VeterinaryDAO findByProfessionalLicenseNumberEqualsIgnoreCase(String professionalLicenseNumber);

    VeterinaryDAO deleteVeterinaryDAOByProfessionalLicenseNumberEqualsIgnoreCase(String professionalLicenseNumber);
}
