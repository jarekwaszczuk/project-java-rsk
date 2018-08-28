package pl.r80.rsk.Person;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "osoby")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotEmpty
    String imie;
    @Column(name = "drugie_imie")
    String drugieImie;
    @NotEmpty
    String nazwisko;
    String pesel;
    String ulica;
    @Column(name = "nr_domu")
    String nrDomu;
    @Column(name = "nr_lokalu")
    String nrLokalu;
    @NotEmpty
    @Column(name = "kod_pocztowy")
    String kodPocztowy;
    @NotEmpty
    String miejscowosc;
    @NotEmpty
    String poczta;
    @Column(name = "nr_telefonu")
    String nrTelefonu;
    String email;
    @Column(name = "log_zmian")
    Timestamp changeLog;
}
