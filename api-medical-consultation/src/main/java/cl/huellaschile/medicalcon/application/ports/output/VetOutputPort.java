package cl.huellaschile.medicalcon.application.ports.output;

import cl.huellaschile.medicalcon.domain.model.Vet;

import java.util.Optional;

public interface VetOutputPort {

    Optional<Vet> getById(String id);

    Vet save(Vet vet);

    void deleteById(String id);
}
