package cl.programadoreschile.adrian.veterinary.domain.gateways;

import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;

public interface VeterinaryGateway {

    VeterinaryDAO createVeterinary(VeterinaryDAO veterinary);

    VeterinaryDAO findByProfessionalLicenseNumber(String professionalLicenseNumber);

    void delete(String professionalLicenseNumber);

}
