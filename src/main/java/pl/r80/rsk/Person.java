package pl.r80.rsk;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "osoby")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String imie;
    @Column(name = "drugie_imie")
    String drugieImie;
    String nazwisko;
    String pesel;
    String ulica;
    @Column(name = "nr_domu")
    String nrDomu;
    @Column(name = "nr_lokalu")
    String nrLokalu;
    @Column(name = "kod_pocztowy")
    String kodPocztowy;
    String miejscowosc;
    @Column(name = "nr_telefonu")
    String nrTelefonu;
    String email;
    @Column(name = "log_zmian")
    LocalDate logZmian;

}
