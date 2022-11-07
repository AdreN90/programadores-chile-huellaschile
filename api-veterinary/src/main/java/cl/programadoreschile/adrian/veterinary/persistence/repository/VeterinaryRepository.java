package cl.programadoreschile.adrian.veterinary.persistence.repository;

import cl.programadoreschile.adrian.veterinary.domain.gateways.VeterinaryGateway;
import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import cl.programadoreschile.adrian.veterinary.persistence.repository.mongo.VeterinaryMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VeterinaryRepository implements VeterinaryGateway {

    private final VeterinaryMongoRepository veterinaryMongoRepository;
    @Override
    public VeterinaryDAO createVeterinary(VeterinaryDAO veterinary) {
        return veterinaryMongoRepository.save(veterinary);
    }
}
