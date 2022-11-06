package cl.programadoreschile.adrian.userpet.application.ports.input;

import cl.programadoreschile.adrian.userpet.domain.model.Pet;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.PetID;

import java.util.List;

public interface GetPetUseCase {

    Pet getById(PetID id);

    List<Pet> getByIdUser(String idUser);

    List<Pet> getByRace(String race);

    List<Pet> getByName(String name);
}
