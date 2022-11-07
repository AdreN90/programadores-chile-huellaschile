package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.Pet;

public interface CreatePetUseCase {

    Pet save(Pet pet);
}
