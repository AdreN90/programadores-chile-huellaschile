package cl.huellaschile.medicalcon.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Veterinary extends RepresentationModel<Veterinary> {

    @NotBlank
    @Size(min = 1, max = 50)
    private String professionalLicenseNumber;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}
