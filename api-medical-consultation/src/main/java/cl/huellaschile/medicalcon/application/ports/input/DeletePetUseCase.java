package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetID;

public interface DeletePetUseCase {

    void deleteById(PetID id);
}
