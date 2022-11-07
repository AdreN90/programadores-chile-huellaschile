package cl.programadoreschile.adrian.veterinary.domain.gateways;

import cl.programadoreschile.adrian.veterinary.domain.entities.VeterinaryDTO;

public interface VeterinaryGateway {

    VeterinaryDTO createVeterinary(VeterinaryDTO veterinary);

    VeterinaryDTO findByProfessionalLicenseNumber(String professionalLicenseNumber);

    VeterinaryDTO delete(String professionalLicenseNumber);

}
