package cl.programadoreschile.adrian.veterinary.domain.services;

import cl.programadoreschile.adrian.veterinary.domain.entities.VeterinaryDTO;
import cl.programadoreschile.adrian.veterinary.domain.gateways.VeterinaryGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VeterinaryService {

    private final VeterinaryGateway veterinaryGateway;

    public VeterinaryDTO createVeterinary(VeterinaryDTO veterinary) {
        return veterinaryGateway.createVeterinary(veterinary);
    }

    public VeterinaryDTO findByProfessionalLicenseNumber(String professionalLicenseNumber) {
        return veterinaryGateway.findByProfessionalLicenseNumber(professionalLicenseNumber);
    }

    public VeterinaryDTO delete(String professionalLicenseNumber) {
        return veterinaryGateway.delete(professionalLicenseNumber);
    }

}
