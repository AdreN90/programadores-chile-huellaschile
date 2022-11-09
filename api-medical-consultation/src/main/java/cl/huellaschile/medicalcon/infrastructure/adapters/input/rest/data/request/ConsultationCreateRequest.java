package cl.huellaschile.medicalcon.infrastructure.adapters.input.rest.data.request;

import cl.huellaschile.medicalcon.validate.ValidRut;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ConsultationCreateRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String consultationId;
    @NotBlank
    @ValidRut
    @Size(min = 1, max = 11)
    private String idUser;
    @NotBlank
    @Size(min = 1, max = 50)
    private String namePet;
    @NotBlank
    @Size(min = 1, max = 50)
    private String nationalRegister;
    @NotBlank
    @Size(min = 1, max = 11)
    @Pattern(regexp = "open|treatment|close")
    private String status;
    @NotNull
    private Integer price;
    @NotBlank
    @Size(min = 1, max = 50)
    private String methodPayment;
    @NotNull
    private Boolean paidOut;
    @NotBlank
    @Size(min = 1, max = 50)
    private String process;
}
