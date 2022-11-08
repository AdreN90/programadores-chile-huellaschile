package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.Vet;

public interface GetVetUseCase {
    Vet getById(String id);
}
