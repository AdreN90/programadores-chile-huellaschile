package cl.programadoreschile.adrian.veterinary.persistence.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("veterinary")
public class VeterinaryDAO {
    @Id
    private long idVeterinary;
    private String professionalLicenseNumber;
    private String name;
}
