package pl.r80.rsk.PayrolPositions;

import lombok.Data;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Payroll.Payroll;
import pl.r80.rsk.Person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "pozycje_listy_plac")
public class PayrollPositions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "wynagrodzenie_brutto")
    BigDecimal wynagrodzenie_brutto;

    @Column(name = "wynagrodzenie_zasadnicze")
    BigDecimal wynagrodzenie_zasadnicze;

    @Column(name = "wynagrodzenie_dodatek_nagroda")
    BigDecimal wynagrodzenie_dodatek_nagroda;

    @Column(name = "wynagrodzenie_dodatek_godziny")
    BigDecimal wynagrodzenie_dodatek_godziny;

    @Column(name = "wynagrodzenie_chorobowe")
    BigDecimal wynagrodzenie_chorobowe;

    @Column(name = "swiadczenia_zwolnione_od_skladek")
    BigDecimal swiadczenia_zwolnione_od_skladek;

    @Column(name = "fundusz_emerytalny")
    BigDecimal fundusz_emerytalny;

    @Column(name = "fundusz_rentowy")
    BigDecimal fundusz_rentowy;

    @Column(name = "fundusz_chorobowy")
    BigDecimal fundusz_chorobowy;

    @Column(name = "koszty_uzyskania_przychodu")
    BigDecimal koszty_uzyskania_przychodu;

    @Column(name = "kwota_wolna_od_PDOF")
    BigDecimal kwota_wolna_od_PDOF;

    @Column(name = "stawka_PDOF")
    BigDecimal stawka_PDOF;

    @Column(name = "podstawa_wymiaru_ubezpieczenia_zdrowotnego")
    BigDecimal podstawa_wymiaru_ubezpieczenia_zdrowotnego;

    @Column(name = "ubezpieczenie_zdrowotne_7_75")
    BigDecimal ubezpieczenie_zdrowotne_7_75;

    @Column(name = "ubezpieczenie_zdrowotne_1_25")
    BigDecimal ubezpieczenie_zdrowotne_1_25;

    @Column(name = "skladka_ubezpieczenie_zdrowotne")
    BigDecimal skladka_ubezpieczenie_zdrowotne;

    @Column(name = "pdof_naliczony")
    BigDecimal pdof_naliczony;

    @Column(name = "zaliczka_PDOF_US")
    BigDecimal zaliczka_PDOF_US;

    @Column(name = "potracenia_dodatki")
    BigDecimal potracenia_dodatki;

    @Column(name = "potracenia_dodatki_PKZP")
    BigDecimal potracenia_dodatki_PKZP;

    @Column(name = "do_wyplaty")
    BigDecimal do_wyplaty;

    @Column(name = "podstawa")
    BigDecimal podstawa;

    @Column(name = "fundusz_emerytalny_9_76")
    BigDecimal fundusz_emerytalny_9_76;

    @Column(name = "fundusz_rentowy_6_50")
    BigDecimal fundusz_rentowy_6_50;

    @Column(name = "fundusz_wypadkowy")
    BigDecimal fundusz_wypadkowy;

    @Column(name = "fundusz_pracy_2_45")
    BigDecimal fundusz_pracy_2_45;

    @Column(name = "FGSP_0_10")
    BigDecimal fgsp_0_10;

    @Column(name = "log_zmian")
    Timestamp changeLog;

    @ManyToOne
    @JoinColumn(name = "id_stowarzyszenia")
    Firm firm;

    @ManyToOne
    @JoinColumn(name = "id_listy_plac")
    Payroll payroll;

    @ManyToOne
    @JoinColumn(name = "id_osoby")
    Person person;

}