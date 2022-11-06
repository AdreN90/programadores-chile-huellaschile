package cl.programadoreschile.adrian.userpet.infrastructure.adapters.output.persistence.entity;

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
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "id_user")
    private String idUser;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "email")
    @NonNull
    private String email;
    @Column(name = "address")
    @NonNull
    private String address;
    @Column(name = "city")
    @NonNull
    private String city;
    @Column(name = "country")
    @NonNull
    private String country;

}
