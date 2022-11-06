package cl.programadoreschile.adrian.userpet.domain.model;

import cl.programadoreschile.adrian.userpet.validate.ValidRut;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
public class User extends RepresentationModel<User> {

    @NotBlank
    @ValidRut
    @Size(min = 1, max = 11)
    private String idUser;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @NotBlank
    @Email
    @Size(min = 1, max = 100)
    private String email;
    @NotBlank
    @Size(min = 1, max = 150)
    private String address;
    @NotBlank
    @Size(min = 1, max = 50)
    private String city;
    @NotBlank
    @Size(min = 1, max = 50)
    private String country;

}
