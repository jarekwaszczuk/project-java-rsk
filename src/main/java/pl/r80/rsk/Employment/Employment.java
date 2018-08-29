package pl.r80.rsk.Employment;

import lombok.Data;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "zatrudnienie")
public class Employment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "data_zatrudnienia")
    LocalDate hireDate;

    @Column(name = "rodzaj_umowy")
    @Enumerated(EnumType.STRING)
    AgreementType agreementType;

    @Column(name = "wynagrodzenie_brutto")
    BigDecimal salary;

    @Column(name = "data_zakonczenia_umowy")
    LocalDate hireEndDate;

    @Column(name = "log_zmian")
    Timestamp changeLog;

    @ManyToOne
    @JoinColumn(name = "id_stowarzyszenia")
    Firm firm;

    @ManyToOne
    @JoinColumn(name = "id_osoby")
    Person person;

}
