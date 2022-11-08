package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "vet")
public class VetEntity {

    @Id
    @Column(name = "national_register")
    private String nationalRegister;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "certified")
    @NonNull
    private Boolean certified;
    @Column(name = "professional_license_number")
    @NonNull
    private String professionalLicenseNumber;

}
