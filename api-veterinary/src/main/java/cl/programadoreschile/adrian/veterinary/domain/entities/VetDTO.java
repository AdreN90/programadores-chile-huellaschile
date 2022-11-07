package cl.programadoreschile.adrian.veterinary.domain.entities;

import cl.programadoreschile.adrian.veterinary.persistence.models.VeterinaryDAO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VetDTO extends RepresentationModel<VetDTO> {
    @NotNull
    private long idVet;
    @NotBlank
    private String nationalRegister;
    @NotBlank
    private String name;
    @NotNull
    private boolean certified;
    private List<VeterinaryDAO> veterinarians;
}
