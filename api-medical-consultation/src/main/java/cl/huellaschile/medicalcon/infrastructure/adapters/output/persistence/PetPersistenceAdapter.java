package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence;

import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.PetRepository;
import cl.huellaschile.medicalcon.application.ports.output.PetOutputPort;
import cl.huellaschile.medicalcon.domain.exception.APIException;
import cl.huellaschile.medicalcon.domain.model.Pet;
import cl.huellaschile.medicalcon.domain.model.User;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetEntity;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity.PetID;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.PetPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetPersistenceAdapter implements PetOutputPort {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetPersistenceMapper petPersistenceMapper;
    private final UserPersistenceMapper userPersistenceMapper;
    private final MessageSource messages;

    @Override
    public Optional<Pet> getById(PetID id) {
        return petRepository.findById(id).map(petPersistenceMapper::toPet);
    }

    @Override
    public List<Pet> getByIdUser(String idUser) {
        final Optional<List<PetEntity>> pets = petRepository.findByIdUserEqualsIgnoreCase(idUser);
        return pets.map(petPersistenceMapper::toPets).orElse(new ArrayList<>());
    }

    @Override
    public List<Pet> getByRace(String race) {
        final Optional<List<PetEntity>> pets = petRepository.findByRaceContainingIgnoreCase(race);
        return pets.map(petPersistenceMapper::toPets).orElse(new ArrayList<>());
    }

    @Override
    public List<Pet> getByName(String name) {
        final Optional<List<PetEntity>> pets = petRepository.findByNamePetContainingIgnoreCase(name);
        return pets.map(petPersistenceMapper::toPets).orElse(new ArrayList<>());
    }

    @Override
    public Pet save(Pet pet) {
        final Optional<User> optionalUser = userRepository.findById(pet.getIdUser()).map(userPersistenceMapper::toUser);
        if (optionalUser.isPresent()) {
            final PetID petID = new PetID().setIdUser(pet.getIdUser()).setNamePet(pet.getNamePet());
            final Optional<Pet> optionalPet = getById(petID);
            if (optionalPet.isPresent()) {
                final String rspFormatMsg = messages.getMessage("pet.duplicated.message", null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, pet.getNamePet());
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                PetEntity petEntity = petPersistenceMapper.toPetEntity(pet);
                return petPersistenceMapper.toPet(petRepository.save(petEntity));
            }
        } else {
            final String rspFormatMsg = messages.getMessage("user.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, pet.getIdUser());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteById(PetID id) {
        Optional<Pet> pet = getById(id);
        if (pet.isPresent()) {
            if (pet.get().getTreatmentActive()) {
                final String responseMessage = messages.getMessage("user.pet.in.treatment.message", null, LocaleContextHolder.getLocale());
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                petRepository.deleteById(id);
            }
        } else {
            final String responseMessage = messages.getMessage("pet.not.exist.message", null, LocaleContextHolder.getLocale());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }
}
