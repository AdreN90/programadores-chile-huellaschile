package cl.programadoreschile.adrian.veterinary.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class VeterinaryDTO extends RepresentationModel<VeterinaryDTO> {
    @NotNull
    private long idVeterinary;
    @NotBlank
    private String professionalLicenseNumber;
    @NotBlank
    private String name;
}
