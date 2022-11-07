package cl.programadoreschile.adrian.veterinary.domain.gateways;

import cl.programadoreschile.adrian.veterinary.domain.entities.VetDTO;

public interface VetGateway {

    VetDTO findById(long id);

    VetDTO getByNationalRegister(String nationalRegister);

    VetDTO save(VetDTO vet);

    VetDTO addVeterinaryToVet(long idVet, long idVeterinary);

    void deleteById(long id);
}
