package cl.programadoreschile.adrian.userpet.domain.service;

import cl.programadoreschile.adrian.userpet.application.ports.input.CreatePetUseCase;
import cl.programadoreschile.adrian.userpet.application.ports.input.DeletePetUseCase;
import cl.programadoreschile.adrian.userpet.application.ports.input.GetPetUseCase;
import cl.programadoreschile.adrian.userpet.application.ports.output.PetOutputPort;
import cl.programadoreschile.adrian.userpet.domain.exception.APIException;
import cl.programadoreschile.adrian.userpet.domain.model.Pet;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.PetID;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService implements GetPetUseCase, CreatePetUseCase, DeletePetUseCase {

    private final PetOutputPort petOutputPort;
    private final MessageSource messages;

    public Pet getById(PetID id) {
        return petOutputPort.
                getById(id)
                .orElseThrow(() -> new APIException(
                        messages.getMessage("pet.not.exist.message", null, LocaleContextHolder.getLocale()),
                        HttpStatus.NOT_FOUND));
    }

    public List<Pet> getByIdUser(String idUser) {
        return petOutputPort.getByIdUser(idUser);
    }

    public List<Pet> getByRace(String race) {
        return petOutputPort.getByRace(race);
    }

    public List<Pet> getByName(String name) {
        return petOutputPort.getByName(name);
    }

    public Pet save(Pet pet) {
        return petOutputPort.save(pet);
    }

    public void deleteById(PetID id) {
        petOutputPort.deleteById(id);
    }
}
