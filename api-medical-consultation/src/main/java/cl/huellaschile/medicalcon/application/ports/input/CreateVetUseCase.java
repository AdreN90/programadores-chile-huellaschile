package cl.huellaschile.medicalcon.application.ports.input;

import cl.huellaschile.medicalcon.domain.model.Vet;

public interface CreateVetUseCase {

    Vet save(Vet vet);
}
