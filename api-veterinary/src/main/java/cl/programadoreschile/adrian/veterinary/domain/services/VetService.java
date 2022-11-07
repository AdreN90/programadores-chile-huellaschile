package cl.programadoreschile.adrian.veterinary.domain.services;

import cl.programadoreschile.adrian.veterinary.domain.entities.VetDTO;
import cl.programadoreschile.adrian.veterinary.domain.gateways.VetGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VetService {

    private final VetGateway vetGateway;

    public VetDTO findById(long id) {
        return vetGateway.findById(id);
    }

    public VetDTO getByNationalRegister(String nationalRegister) {
        return vetGateway.getByNationalRegister(nationalRegister);
    }

    public VetDTO save(VetDTO vet) {
        return vetGateway.save(vet);
    }

    public VetDTO addVeterinaryToVet(long idVet, long idVeterinary) {
        return vetGateway.addVeterinaryToVet(idVet, idVeterinary);
    }

    public void deleteById(long id) {
        vetGateway.deleteById(id);
    }

}
