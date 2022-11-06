package cl.programadoreschile.adrian.userpet.application.ports.input;

import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.PetID;

public interface DeletePetUseCase {

    void deleteById(PetID id);
}
