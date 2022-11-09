package cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationCreateResponse {
    private String consultationId;
    private String idUser;
    private String namePet;
    private String nationalRegister;
    private String status;
    private Integer price;
    private String methodPayment;
    private Boolean paidOut;
    private String process;
}
