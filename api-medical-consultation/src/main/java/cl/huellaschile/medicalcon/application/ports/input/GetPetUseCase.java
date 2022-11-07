package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.Pet;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetID;

import java.util.List;

public interface GetPetUseCase {

    Pet getById(PetID id);

    List<Pet> getByIdUser(String idUser);

    List<Pet> getByRace(String race);

    List<Pet> getByName(String name);
}
