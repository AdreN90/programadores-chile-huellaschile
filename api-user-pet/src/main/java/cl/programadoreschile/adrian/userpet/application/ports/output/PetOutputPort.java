package cl.programadoreschile.adrian.userpet.application.ports.output;

import cl.programadoreschile.adrian.userpet.domain.model.Pet;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.PetID;

import java.util.List;
import java.util.Optional;

public interface PetOutputPort {

    Optional<Pet> getById(PetID id);

    List<Pet> getByIdUser(String idUser);

    List<Pet> getByRace(String race);

    List<Pet> getByName(String name);

    Pet save(Pet pet);

    void deleteById(PetID id);

}
