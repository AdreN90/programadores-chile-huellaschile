package cl.programadoreschile.adrian.veterinary.persistence.repository;

import cl.programadoreschile.adrian.veterinary.domain.entities.VetDTO;
import cl.programadoreschile.adrian.veterinary.domain.gateways.VetGateway;
import cl.programadoreschile.adrian.veterinary.error.APIException;
import cl.programadoreschile.adrian.veterinary.persistence.mapper.VetMapper;
import cl.programadoreschile.adrian.veterinary.persistence.models.VetDAO;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import cl.programadoreschile.adrian.veterinary.persistence.repository.mongo.VetMongoRepository;
import cl.programadoreschile.adrian.veterinary.persistence.repository.mongo.VeterinaryMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cl.programadoreschile.adrian.veterinary.constants.MessageConstants.*;

@Repository
@RequiredArgsConstructor
public class VetRepository implements VetGateway {

    private final VetMongoRepository vetMongoRepository;
    private final VeterinaryMongoRepository veterinaryMongoRepository;
    private final VetMapper vetMapper;
    private final MessageSource messages;


    @Override
    public VetDTO findById(long id) {
        Optional<VetDAO> vet = vetMongoRepository.findById(id);
        if (vet.isPresent()) {
            return vetMapper.toVetDTO(vet.get());
        } else {
            final String rspFormatMsg = messages.getMessage(MESSAGE_VET_NOT_EXIST, null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, id);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public VetDTO getByNationalRegister(String nationalRegister) {
        final VetDAO vet = vetMongoRepository.findByNationalRegisterEqualsIgnoreCase(nationalRegister);
        if (vet == null) {
            final String rspFormatMsg = messages.getMessage(MESSAGE_VET_NOT_EXIST, null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, nationalRegister);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        } else {
            return vetMapper.toVetDTO(vet);
        }
    }

    @Override
    public VetDTO save(VetDTO newVet) {
        final VetDAO vet = vetMongoRepository.findByNationalRegisterEqualsIgnoreCase(newVet.getNationalRegister());
        if (vet == null) {
            if (newVet.isCertified()) {
                return vetMapper.toVetDTO(vetMongoRepository.save(vetMapper.toVetDAO(newVet.setVeterinarians(new ArrayList<>()))));
            } else {
                final String rspFormatMsg = messages.getMessage(MESSAGE_VET_IS_NOT_CERTIFIED, null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, newVet.getNationalRegister());
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            }
        } else {
            final String rspFormatMsg = messages.getMessage(MESSAGE_VET_DUPLICATED, null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, newVet.getNationalRegister());
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public VetDTO addVeterinaryToVet(long idVet, long idVeterinary) {
        final Optional<VeterinaryDAO> veterinary = veterinaryMongoRepository.findById(idVeterinary);
        if (veterinary.isPresent()) {
            final Optional<VetDAO> vet = vetMongoRepository.findById(idVet);
            if (vet.isPresent()) {
                List<VeterinaryDAO> veterinarians = vet.get().getVeterinarians();
                veterinarians.add(veterinary.get());
                vet.get().setVeterinarians(veterinarians);
                vetMongoRepository.deleteById(idVet);
                return vetMapper.toVetDTO(vetMongoRepository.save(vet.get()));
            } else {
                final String rspFormatMsg = messages.getMessage(MESSAGE_VET_NOT_EXIST, null, LocaleContextHolder.getLocale());
                final String responseMessage = String.format(rspFormatMsg, idVeterinary);
                throw new APIException(responseMessage, HttpStatus.CONFLICT);
            }
        } else {
            final String rspFormatMsg = messages.getMessage(MESSAGE_VETERINARY_NOT_EXIST, null, LocaleContextHolder.getLocale());
            final String responseMessage = String.format(rspFormatMsg, idVeterinary);
            throw new APIException(responseMessage, HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteById(long id) {
        vetMongoRepository.deleteById(id);
    }
}
