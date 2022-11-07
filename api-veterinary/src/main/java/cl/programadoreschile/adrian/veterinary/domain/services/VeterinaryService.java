package cl.programadoreschile.adrian.veterinary.domain.services;

import cl.programadoreschile.adrian.veterinary.domain.gateways.VeterinaryGateway;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VeterinaryService {

    private final VeterinaryGateway veterinaryGateway;

    public VeterinaryDAO createVeterinary(VeterinaryDAO veterinary) {
        return veterinaryGateway.createVeterinary(veterinary);
    }

    public VeterinaryDAO findByProfessionalLicenseNumber(String professionalLicenseNumber) {
        return veterinaryGateway.findByProfessionalLicenseNumber(professionalLicenseNumber);
    }

    public void delete(String professionalLicenseNumber) {
        veterinaryGateway.delete(professionalLicenseNumber);
    }

}
