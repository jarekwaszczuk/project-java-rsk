package pl.r80.rsk.Firm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.r80.rsk.Employment.Employment;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "stowarzyszenie")
public class Firm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotEmpty(message = "Nazwa nie może być pusta")
    @Size(message = "Musi mieć odpowiednią długość", min = 3, max = 30)
    @Column(name = "nazwa")
    String name;

    @NotEmpty(message = "Pełna nazwa nie może być pusta")
    @Size(message = "Musi mieć odpowiednią długość", min = 5, max = 30)
    @Column(name = "pelna_nazwa")
    String fullName;

    @NotEmpty(message = "NIP nie może być pusty")
    String nip;
    @NotEmpty(message = "Regon nie może być pusty")
    String regon;

    @NotEmpty(message = "Ulica nie może być pusta")
    @Column(name = "ulica")
    String street;

    @NotEmpty(message = "Nr lokalu nie może być pusty")
    @Column(name = "nr_lokalu")
    String placeNumber;

    @NotEmpty(message = "Kod pocztowy nie może być pusty")
    @Column(name = "kod_pocztowy")
    String postalCode;

    @NotEmpty(message = "Poczta nie może być pusta")
    @Column(name = "poczta")
    String post;

    @NotEmpty(message = "Miejscowość nie może być pusta")
    @Column(name = "miejscowosc")
    String city;

    @Column(name = "skladka_wypadkowa")
    Double accidentDues;

    @Column(name = "data_skladki_wypadkowej")
    LocalDate accidentDuesDate;

    @Column(name = "log_zmian")
    Timestamp changeLog;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "firm", fetch = FetchType.EAGER)
    Set<Employment> employmentSet;
}
