package cl.huellaschile.medicalcon.domain.model;

import cl.huellaschile.medicalcon.validate.ValidRut;
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
@EqualsAndHashCode(callSuper=false)
public class Pet extends RepresentationModel<Pet> {

    @NotBlank
    @ValidRut
    @Size(min = 1, max = 11, message = "idPerson size must be between {min} and {max}")
    private String idUser;
    @NotBlank
    @Size(min = 1, max = 50, message = "namePet size must be between {min} and {max}")
    private String namePet;
    @NotBlank
    @Size(min = 1, max = 50, message = "race size must be between {min} and {max}")
    private String race;
    @NotBlank
    @Size(min = 1, max = 50, message = "treatmentName size must be between {min} and {max}")
    private String treatmentName;
    @NotNull
    private Boolean treatmentActive;

}
