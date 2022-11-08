package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.Veterinary;

public interface GetVeterinaryUseCase {
    Veterinary getById(String id);
}
