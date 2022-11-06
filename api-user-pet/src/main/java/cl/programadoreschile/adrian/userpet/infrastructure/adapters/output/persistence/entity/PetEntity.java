package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pet")
@IdClass(PetID.class)
public class PetEntity {

    @Id
    @Column(name = "id_user")
    private String idUser;

    @Id
    @Column(name = "name_pet")
    private String namePet;

    @Column(name = "race")
    @NonNull
    private String race;

    @Column(name = "treatment_name")
    @NonNull
    private String treatmentName;

    @Column(name = "treatment_active")
    @NonNull
    private Boolean treatmentActive;

}
