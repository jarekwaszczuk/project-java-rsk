package pl.r80.rsk.Firm;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "stowarzyszenie")
public class Firm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotEmpty
    @Size(min=3, max=30)
    @Column(name = "nazwa")
    String name;

    @NotEmpty
    @Size(min=3, max=30)
    @Column(name = "pelna_nazwa")
    String fullName;

    @NotEmpty
    String nip;
    @NotEmpty
    String regon;

    @NotEmpty
    @Column(name = "ulica")
    String street;

    @NotEmpty
    @Column(name = "nr_lokalu")
    String placeNumber;

    @NotEmpty
    @Column(name = "kod_pocztowy")
    String postalCode;

    @NotEmpty
    @Column(name = "poczta")
    String post;

    @Column(name = "skladka_wypadkowa")
    Double accidentDues;

    @Column(name = "data_skladki_wypadkowej")
    LocalDate accidentDuesDate;

    @Column(name = "log_zmian")
    Timestamp changeLog;


}
