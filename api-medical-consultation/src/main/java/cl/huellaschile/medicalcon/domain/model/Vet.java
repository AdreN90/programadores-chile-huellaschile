package cl.huellaschile.medicalcon.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Vet extends RepresentationModel<Vet> {

    @NotBlank
    @Size(min = 1, max = 50, message = "nationalRegister size must be between {min} and {max}")
    private String nationalRegister;
    @NotBlank
    @Size(min = 1, max = 50, message = "name size must be between {min} and {max}")
    private String name;
    @NotNull
    private Boolean certified;
    @NotBlank
    @Size(min = 1, max = 50, message = "professionalLicenseNumber size must be between {min} and {max}")
    private String professionalLicenseNumber;

}
