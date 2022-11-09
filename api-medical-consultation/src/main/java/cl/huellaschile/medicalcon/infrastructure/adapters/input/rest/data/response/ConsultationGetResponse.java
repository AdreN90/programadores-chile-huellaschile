package cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response;

import cl.huellaschile.medicalcon.domain.model.Pet;
import cl.huellaschile.medicalcon.domain.model.User;
import cl.huellaschile.medicalcon.domain.model.Vet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ConsultationGetResponse {
    private String consultationId;
    private User user;
    private Pet pet;
    private Vet vet;
    private String status;
    private Integer price;
    private String methodPayment;
    private Boolean paidOut;
    private String process;
}
