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
@Table(name = "consultation")
public class ConsultationEntity {

    @Id
    @Column(name = "id_consultation")
    private String consultationId;
    @Column(name = "id_user")
    @NonNull
    private String idUser;
    @Column(name = "name_pet")
    @NonNull
    private String namePet;
    @Column(name = "national_register")
    @NonNull
    private String nationalRegister;
    @Column(name = "status")
    @NonNull
    private String status;
    @Column(name = "price")
    @NonNull
    private Integer price;
    @Column(name = "method_payment")
    @NonNull
    private String methodPayment;
    @Column(name = "process")
    @NonNull
    private String process;
}
