package pl.r80.rsk.Access;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "dostep")
public class Access implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "uzytkownik")
    String user;

    @Column(name="haslo")
    String password;

    @Column(name="imie")
    String firstName;

    @Column(name = "nazwisko")
    String lastName;

    @Column(name = "domyslny_kontekst")
    Integer defaultContext;

    @Column(name = "log_zmian")
    Timestamp changeLog;

}
