package cl.huellaschile.medicalcon.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class PetID implements Serializable {

    private String idUser;
    private String namePet;

    public PetID() {
    }

    public String getIdUser() {
        return idUser;
    }

    public PetID setIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getNamePet() {
        return namePet;
    }

    public PetID setNamePet(String namePet) {
        this.namePet = namePet;
        return this;
    }

    public PetID(String idPerson, String namePet) {
        this.idUser = idPerson;
        this.namePet = namePet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetID that)) return false;
        return namePet == that.namePet && idUser.equals(that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, namePet);
    }
}
