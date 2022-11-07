package cl.programadoreschile.adrian.veterinary.persistence.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@Document("vet")
public class VetDAO {

    @Id
    private long idVet;
    private String nationalRegister;
    private String name;
    private boolean certified;
    @DocumentReference(lazy=true)
    List<VeterinaryDAO> veterinarians;
}
