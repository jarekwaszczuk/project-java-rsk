package pl.r80.rsk.Firm;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "stowarzyszenie")
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "nazwa")
    String name;

    @Column(name = "pelna_nazwa")
    String fullName;

    String nip;
    String regon;

    @Column(name = "ulica")
    String street;

    @Column(name = "nr_lokalu")
    String placeNumber;

    @Column(name = "kod_pocztowy")
    String postalCode;

    @Column(name = "poczta")
    String post;

    @Column(name = "skladka_wypadkowa")
    Double accidentDues;

    @Column(name = "data_skladki_wypadkowej")
    LocalDate accidentDuesDate;

    @Column(name = "log_zmian")
    Timestamp changeLog;


}
