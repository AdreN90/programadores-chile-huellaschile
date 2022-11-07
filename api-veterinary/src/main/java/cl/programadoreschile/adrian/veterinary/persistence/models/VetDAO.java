package cl.programadoreschile.adrian.veterinary.persistence.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("vet")
public class VetDAO {

    @Id
    private String nationalRegister;
    private String name;
    private boolean certified;
    @DBRef
    List<VeterinaryDAO> books;
}
