package cl.programadoreschile.adrian.veterinary.persistence.repository;

import cl.programadoreschile.adrian.veterinary.domain.gateways.VeterinaryGateway;
import cl.programadoreschile.adrian.veterinary.error.APIException;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import cl.programadoreschile.adrian.veterinary.persistence.repository.mongo.VeterinaryMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VeterinaryRepository implements VeterinaryGateway {

    private final VeterinaryMongoRepository veterinaryMongoRepository;
    @Override
    public VeterinaryDAO createVeterinary(VeterinaryDAO newVeterinary) {
        VeterinaryDAO veterinary = findByProfessionalLicenseNumber(newVeterinary.getProfessionalLicenseNumber());
        if(veterinary != null){
            throw new APIException("El veterinario ya existe", HttpStatus.CONFLICT);
        }else{
            return veterinaryMongoRepository.save(newVeterinary);
        }
    }

    @Override
    public VeterinaryDAO findByProfessionalLicenseNumber(String professionalLicenseNumber){
        return veterinaryMongoRepository.findByProfessionalLicenseNumberEqualsIgnoreCase(professionalLicenseNumber);
    }

    @Override
    public void delete(String professionalLicenseNumber){
        VeterinaryDAO veterinary = findByProfessionalLicenseNumber(professionalLicenseNumber);
        if(veterinary == null){
            throw new APIException("El veterinario no existe", HttpStatus.CONFLICT);
        }else{
            veterinaryMongoRepository
                    .deleteVeterinaryDAOByProfessionalLicenseNumberEqualsIgnoreCase(professionalLicenseNumber);
        }
    }
}
