package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence;

import cl.programadoreschile.adrian.userpet.application.ports.output.UserOutputPort;
import cl.programadoreschile.adrian.userpet.domain.exception.APIException;
import cl.programadoreschile.adrian.userpet.domain.model.User;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.PetEntity;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity.UserEntity;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.repository.PetRepository;
import cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final UserPersistenceMapper mapper;
    private final MessageSource messages;

    @Override
    public Optional<User> getById(String id) {
        return userRepository.findById(id).map(mapper::toUser);
    }

    @Override
    public User save(User user) {
        final Optional<User> optionalUser = getById(user.getIdUser());
        if (optionalUser.isPresent()) {
            final String rspFormatMsg = messages.getMessage("user.duplicated.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, user.getIdUser());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            UserEntity userEntity = mapper.toUserEntity(user);
            return mapper.toUser(userRepository.save(userEntity));
        }
    }

    @Override
    public void deleteById(String id) {
        final Optional<List<PetEntity>> pets = petRepository.findByIdUserEqualsIgnoreCase(id);
        if (pets.isPresent()) {
            final boolean petInTreatment = pets.get().stream().anyMatch(PetEntity::getTreatmentActive);
            if (petInTreatment) {
                final String responseMessage = messages.getMessage("user.pet.in.treatment.message", null, LocaleContextHolder.getLocale());
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            } else {
                userRepository.deleteById(id);
            }
        } else {
            final String rspFormatMsg = messages.getMessage("user.not.exist.message", null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }
}
