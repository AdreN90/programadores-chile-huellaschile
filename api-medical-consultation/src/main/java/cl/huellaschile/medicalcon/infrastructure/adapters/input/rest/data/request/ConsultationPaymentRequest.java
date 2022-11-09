package cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ConsultationPaymentRequest {

    @NotNull
    private Integer price;
    @NotBlank
    @Size(min = 1, max = 50)
    private String methodPayment;

}
