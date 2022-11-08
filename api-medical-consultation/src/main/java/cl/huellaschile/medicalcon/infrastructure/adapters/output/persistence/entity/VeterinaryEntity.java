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
@Table(name = "veterinary")
public class VeterinaryEntity {

    @Id
    @Column(name = "professional_license_number")
    private String professionalLicenseNumber;
    @Column(name = "name")
    @NonNull
    private String name;
}
