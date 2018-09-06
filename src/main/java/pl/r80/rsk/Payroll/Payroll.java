package pl.r80.rsk.Payroll;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.r80.rsk.Employment.AgreementType;
import pl.r80.rsk.Firm.Firm;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "listy_plac")
public class Payroll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "nr_listy")
    String payrollNumber;

    @Column(name = "typ_umowy")
    @Enumerated(EnumType.STRING)
    AgreementType agreementType;

    @Column(name="wynagrodzenie_za_miesiac")
    String payrollMonth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_utworzenia")
    LocalDate createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_wyplacenia")
    LocalDate paycheckDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_zamkniecia")
    LocalDate closeDate;

    @Column(name = "suma_do_wyplaty")
    BigDecimal sumToPay;

    @Column(name = "suma_zaliczka_pit4r")
    BigDecimal sumPIT4R;

    @Column(name = "suma_ubezpieczenia_spoleczne")
    BigDecimal sumSocialInsurance;

    @Column(name = "suma_ubezpieczenia_zdrowotne")
    BigDecimal sumHealthInsurance;

    @Column(name = "suma_fp_fgsp")
    BigDecimal sumFpFgsp;

    @Column(name = "suma_skladek_ZUS")
    BigDecimal sumZus;

    @Column(name = "log_zmian")
    Timestamp changeLog;

    @ManyToOne
    @JoinColumn(name = "id_stowarzyszenia")
    Firm firm;

}
