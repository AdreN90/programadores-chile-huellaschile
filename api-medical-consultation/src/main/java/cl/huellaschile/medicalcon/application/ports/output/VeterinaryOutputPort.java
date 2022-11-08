package cl.huellaschile.medicalcon.application.ports.output;

import cl.huellaschile.medicalcon.domain.model.Veterinary;

import java.util.Optional;

public interface VeterinaryOutputPort {

    Optional<Veterinary> getById(String id);

    Veterinary save(Veterinary veterinary);

    void deleteById(String id);
}
