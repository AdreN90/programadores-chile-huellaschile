package cl.programadoreschile.adrian.userpet.application.ports.input;

import cl.programadoreschile.adrian.userpet.domain.model.Pet;

public interface CreatePetUseCase {

    Pet save(Pet pet);
}
