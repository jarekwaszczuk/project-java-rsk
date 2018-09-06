package pl.r80.rsk.Person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.r80.rsk.Employment.Employment;
import pl.r80.rsk.PayrolPositions.PayrollPositions;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "osoby")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotEmpty(message = "Imię nie może być puste")
    String imie;
    @Column(name = "drugie_imie")
    String drugieImie;
    @NotEmpty(message = "Nazwisko nie może być puste")
    String nazwisko;
    @Digits(message = "Musi zawierać 11 cyfr", integer = 11, fraction = 0)
    String pesel;
    String ulica;
    @Column(name = "nr_domu")
    String nrDomu;
    @Column(name = "nr_lokalu")
    String nrLokalu;
    @NotEmpty(message = "Kod pocztowy nie może być pusty")
    @Column(name = "kod_pocztowy")
    String kodPocztowy;
    @NotEmpty(message = "Miejscowość nie może być pusta")
    String miejscowosc;
    @NotEmpty(message = "Poczta nie może być pusta")
    String poczta;
    @Column(name = "nr_telefonu")
    String nrTelefonu;
    String email;
    @Column(name = "log_zmian")
    Timestamp changeLog;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    Set<Employment> employmentSet;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    Set<PayrollPositions> payrollPositionsSet;
}
