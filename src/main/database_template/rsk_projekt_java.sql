-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               5.7.11 - MySQL Community Server (GPL)
-- Serwer OS:                    Win32
-- HeidiSQL Wersja:              9.5.0.5280
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych rsk_projekt_java
CREATE DATABASE IF NOT EXISTS `rsk_projekt_java` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `rsk_projekt_java`;

-- Zrzut struktury tabela rsk_projekt_java.dostep
CREATE TABLE IF NOT EXISTS `dostep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uzytkownik` text COLLATE utf8_polish_ci NOT NULL,
  `haslo` text COLLATE utf8_polish_ci NOT NULL,
  `imie` text COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8_polish_ci NOT NULL,
  `domyslny_kontekst` int(11) NOT NULL,
  `log_zmian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci COMMENT='Dostęp do programu RSK';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.dostep_log
CREATE TABLE IF NOT EXISTS `dostep_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_dostep` int(11) NOT NULL,
  `uzytkownik` text COLLATE utf8_polish_ci NOT NULL,
  `haslo` text COLLATE utf8_polish_ci NOT NULL,
  `imie` text COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8_polish_ci NOT NULL,
  `domyslny_kontekst` int(11) NOT NULL,
  `log_zmian` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci ROW_FORMAT=DYNAMIC COMMENT='Log zmian tabeli dostep';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.listy_plac
CREATE TABLE IF NOT EXISTS `listy_plac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stowarzyszenia` int(11) NOT NULL,
  `typ_umowy` varchar(2) COLLATE utf8_polish_ci NOT NULL,
  `wynagrodzenie_za_miesiac` varchar(7) COLLATE utf8_polish_ci NOT NULL,
  `nr_listy` varchar(50) COLLATE utf8_polish_ci NOT NULL,
  `data_utworzenia` datetime NOT NULL,
  `data_wyplacenia` datetime NOT NULL,
  `data_zamkniecia` datetime NOT NULL,
  `suma_do_wyplaty` decimal(10,2) NOT NULL,
  `suma_zaliczka_pit4r` decimal(10,2) NOT NULL,
  `suma_ubezpieczenia_spoleczne` decimal(10,2) NOT NULL,
  `suma_ubezpieczenia_zdrowotne` decimal(10,2) NOT NULL,
  `suma_fp_fgsp` decimal(10,2) NOT NULL,
  `suma_skladek_ZUS` decimal(10,2) NOT NULL,
  `log_zmian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nr_listy` (`nr_listy`),
  KEY `FK_listy_plac_stowarzyszenie` (`id_stowarzyszenia`),
  CONSTRAINT `FK_listy_plac_stowarzyszenie` FOREIGN KEY (`id_stowarzyszenia`) REFERENCES `stowarzyszenie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci COMMENT='Tabela z listami płac';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.osoby
CREATE TABLE IF NOT EXISTS `osoby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` text COLLATE utf8_polish_ci NOT NULL,
  `drugie_imie` text COLLATE utf8_polish_ci,
  `nazwisko` text COLLATE utf8_polish_ci NOT NULL,
  `pesel` varchar(11) COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci,
  `nr_domu` text COLLATE utf8_polish_ci NOT NULL,
  `nr_lokalu` text COLLATE utf8_polish_ci,
  `kod_pocztowy` text COLLATE utf8_polish_ci NOT NULL,
  `miejscowosc` text COLLATE utf8_polish_ci NOT NULL,
  `poczta` text COLLATE utf8_polish_ci NOT NULL,
  `nr_telefonu` text COLLATE utf8_polish_ci,
  `email` text COLLATE utf8_polish_ci,
  `log_zmian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci COMMENT='Osoby zatrudnione';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.pozycje_listy_plac
CREATE TABLE IF NOT EXISTS `pozycje_listy_plac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stowarzyszenia` int(11) NOT NULL,
  `id_listy_plac` int(11) NOT NULL,
  `id_osoby` int(11) NOT NULL,
  `wynagrodzenie_brutto` decimal(10,2) NOT NULL,
  `wynagrodzenie_zasadnicze` decimal(10,2) NOT NULL,
  `wynagrodzenie_dodatek_nagroda` decimal(10,2) NOT NULL,
  `wynagrodzenie_dodatek_godziny` decimal(10,2) NOT NULL,
  `wynagrodzenie_chorobowe` decimal(10,2) NOT NULL,
  `swiadczenia_zwolnione_od_skladek` decimal(10,2) NOT NULL,
  `fundusz_emerytalny` decimal(10,2) NOT NULL,
  `fundusz_rentowy` decimal(10,2) NOT NULL,
  `fundusz_chorobowy` decimal(10,2) NOT NULL,
  `koszty_uzyskania_przychodu` decimal(10,2) NOT NULL,
  `kwota_wolna_od_PDOF` decimal(10,2) NOT NULL DEFAULT '46.33',
  `stawka_PDOF` decimal(3,2) NOT NULL DEFAULT '0.18',
  `podstawa_wymiaru_ubezpieczenia_zdrowotnego` decimal(10,2) NOT NULL,
  `ubezpieczenie_zdrowotne_7_75` decimal(10,2) NOT NULL,
  `ubezpieczenie_zdrowotne_1_25` decimal(10,2) NOT NULL,
  `skladka_ubezpieczenie_zdrowotne` decimal(10,2) NOT NULL,
  `pdof_naliczony` decimal(10,2) NOT NULL,
  `zaliczka_PDOF_US` decimal(10,2) NOT NULL,
  `potracenia_dodatki` decimal(10,2) NOT NULL,
  `potracenia_dodatki_PKZP` decimal(10,2) NOT NULL,
  `do_wyplaty` decimal(10,2) NOT NULL,
  `podstawa` decimal(10,2) NOT NULL,
  `fundusz_emerytalny_ 9_76` decimal(10,2) NOT NULL,
  `fundusz_rentowy_6_50` decimal(10,2) NOT NULL,
  `fundusz_wypadkowy` decimal(10,2) NOT NULL,
  `fundusz_pracy_2_45` decimal(10,2) NOT NULL,
  `FGSP_0_10` decimal(10,2) NOT NULL,
  `log_zmian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fundusz_emerytalny_9_76` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pozycje_listy_plac_listy_plac` (`id_listy_plac`),
  KEY `FK_pozycje_listy_plac_stowarzyszenie` (`id_stowarzyszenia`),
  KEY `FK_pozycje_listy_plac_osoby` (`id_osoby`),
  CONSTRAINT `FK_pozycje_listy_plac_listy_plac` FOREIGN KEY (`id_listy_plac`) REFERENCES `listy_plac` (`id`),
  CONSTRAINT `FK_pozycje_listy_plac_osoby` FOREIGN KEY (`id_osoby`) REFERENCES `osoby` (`id`),
  CONSTRAINT `FK_pozycje_listy_plac_stowarzyszenie` FOREIGN KEY (`id_stowarzyszenia`) REFERENCES `stowarzyszenie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci COMMENT='Konkretne pozycje z listy płac';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.stowarzyszenie
CREATE TABLE IF NOT EXISTS `stowarzyszenie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL,
  `pelna_nazwa` text COLLATE utf8_polish_ci NOT NULL,
  `nip` text COLLATE utf8_polish_ci NOT NULL,
  `regon` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `nr_lokalu` text COLLATE utf8_polish_ci NOT NULL,
  `kod_pocztowy` text COLLATE utf8_polish_ci NOT NULL,
  `poczta` text COLLATE utf8_polish_ci NOT NULL,
  `miejscowosc` text COLLATE utf8_polish_ci NOT NULL,
  `skladka_wypadkowa` decimal(3,2) NOT NULL,
  `data_skladki_wypadkowej` date NOT NULL,
  `log_zmian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci COMMENT='Lista stowarzyszeń';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.stowarzyszenie_log
CREATE TABLE IF NOT EXISTS `stowarzyszenie_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stowarzyszenia` int(11) DEFAULT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL,
  `pelna_nazwa` text COLLATE utf8_polish_ci NOT NULL,
  `nip` text COLLATE utf8_polish_ci NOT NULL,
  `regon` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `nr_lokalu` text COLLATE utf8_polish_ci NOT NULL,
  `kod_pocztowy` text COLLATE utf8_polish_ci NOT NULL,
  `poczta` text COLLATE utf8_polish_ci NOT NULL,
  `skladka_wypadkowa` decimal(3,2) NOT NULL,
  `data_skladki_wypadkowej` date NOT NULL,
  `log_zmian` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci ROW_FORMAT=DYNAMIC COMMENT='Log zmian tabeli stowarzyszenie';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.zatrudnienie
CREATE TABLE IF NOT EXISTS `zatrudnienie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stowarzyszenia` int(11) NOT NULL,
  `id_osoby` int(11) NOT NULL,
  `data_zatrudnienia` date NOT NULL,
  `rodzaj_umowy` text COLLATE utf8_polish_ci NOT NULL,
  `wynagrodzenie_brutto` decimal(10,2) NOT NULL,
  `data_zakonczenia_umowy` date DEFAULT NULL,
  `log_zmian` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_zatrudnienie_stowarzyszenie` (`id_stowarzyszenia`),
  KEY `FK_zatrudnienie_osoby` (`id_osoby`),
  CONSTRAINT `FK_zatrudnienie_osoby` FOREIGN KEY (`id_osoby`) REFERENCES `osoby` (`id`),
  CONSTRAINT `FK_zatrudnienie_stowarzyszenie` FOREIGN KEY (`id_stowarzyszenia`) REFERENCES `stowarzyszenie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci COMMENT='Osoby zatrudnione w konkretnych stowarzyszeniach';

-- Data exporting was unselected.
-- Zrzut struktury tabela rsk_projekt_java.zatrudnienie_log
CREATE TABLE IF NOT EXISTS `zatrudnienie_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_zatrudnienie` int(11) NOT NULL DEFAULT '0',
  `id_stowarzyszenia` int(11) NOT NULL,
  `id_osoby` int(11) NOT NULL,
  `data_zatrudnienia` date NOT NULL,
  `rodzaj_umowy` text COLLATE utf8_polish_ci NOT NULL,
  `wynagrodzenie_brutto` decimal(10,2) NOT NULL,
  `data_zakonczenia_umowy` date DEFAULT NULL,
  `log_zmian` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_zatrudnienie_stowarzyszenie` (`id_stowarzyszenia`),
  KEY `FK_zatrudnienie_osoby` (`id_osoby`),
  CONSTRAINT `zatrudnienie_log_ibfk_1` FOREIGN KEY (`id_osoby`) REFERENCES `osoby` (`id`),
  CONSTRAINT `zatrudnienie_log_ibfk_2` FOREIGN KEY (`id_stowarzyszenia`) REFERENCES `stowarzyszenie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci ROW_FORMAT=DYNAMIC COMMENT='Osoby zatrudnione w konkretnych stowarzyszeniach';

-- Data exporting was unselected.
-- Zrzut struktury wyzwalacz rsk_projekt_java.dostep_after_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `dostep_after_delete` AFTER DELETE ON `dostep` FOR EACH ROW BEGIN
INSERT INTO dostep_log (id_dostep, uzytkownik, haslo, imie, nazwisko, domyslny_kontekst, log_zmian) VALUES
(OLD.id, OLD.uzytkownik, OLD.haslo, OLD.imie, OLD.nazwisko, OLD.domyslny_kontekst, OLD.log_zmian) ;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz rsk_projekt_java.dostep_after_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `dostep_after_update` AFTER UPDATE ON `dostep` FOR EACH ROW BEGIN
INSERT INTO dostep_log (id_dostep, uzytkownik, haslo, imie, nazwisko, domyslny_kontekst, log_zmian) VALUES
(OLD.id, OLD.uzytkownik, OLD.haslo, OLD.imie, OLD.nazwisko, OLD.domyslny_kontekst, OLD.log_zmian) ;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz rsk_projekt_java.stowarzyszenie_after_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `stowarzyszenie_after_delete` AFTER DELETE ON `stowarzyszenie` FOR EACH ROW BEGIN
INSERT INTO stowarzyszenie_log (id_stowarzyszenia, nazwa, pelna_nazwa, nip, regon, ulica, nr_lokalu, kod_pocztowy, poczta, skladka_wypadkowa, data_skladki_wypadkowej, log_zmian)
VALUES (OLD.id, OLD.nazwa, OLD.pelna_nazwa, OLD.nip, OLD.regon, OLD.ulica, OLD.nr_lokalu, OLD.kod_pocztowy, OLD.poczta, OLD.skladka_wypadkowa, OLD.data_skladki_wypadkowej, OLD.log_zmian);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz rsk_projekt_java.stowarzyszenie_after_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `stowarzyszenie_after_update` AFTER UPDATE ON `stowarzyszenie` FOR EACH ROW BEGIN
INSERT INTO stowarzyszenie_log (id_stowarzyszenia, nazwa, pelna_nazwa, nip, regon, ulica, nr_lokalu, kod_pocztowy, poczta, skladka_wypadkowa, data_skladki_wypadkowej, log_zmian)
VALUES (OLD.id, OLD.nazwa, OLD.pelna_nazwa, OLD.nip, OLD.regon, OLD.ulica, OLD.nr_lokalu, OLD.kod_pocztowy, OLD.poczta, OLD.skladka_wypadkowa, OLD.data_skladki_wypadkowej, OLD.log_zmian);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz rsk_projekt_java.zatrudnienie_after_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `zatrudnienie_after_delete` AFTER DELETE ON `zatrudnienie` FOR EACH ROW BEGIN
INSERT INTO zatrudnienie_log (id_zatrudnienie,id_stowarzyszenia,id_osoby,data_zatrudnienia,rodzaj_umowy,wynagrodzenie_brutto,data_zakonczenia_umowy,log_zmian)
VALUES (OLD.id,OLD.id_stowarzyszenia,OLD.id_osoby,OLD.data_zatrudnienia,OLD.rodzaj_umowy,OLD.wynagrodzenie_brutto,OLD.data_zakonczenia_umowy,OLD.log_zmian);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Zrzut struktury wyzwalacz rsk_projekt_java.zatrudnienie_after_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `zatrudnienie_after_update` AFTER UPDATE ON `zatrudnienie` FOR EACH ROW BEGIN
INSERT INTO zatrudnienie_log (id_zatrudnienie,id_stowarzyszenia,id_osoby,data_zatrudnienia,rodzaj_umowy,wynagrodzenie_brutto,data_zakonczenia_umowy,log_zmian)
VALUES (OLD.id,OLD.id_stowarzyszenia,OLD.id_osoby,OLD.data_zatrudnienia,OLD.rodzaj_umowy,OLD.wynagrodzenie_brutto,OLD.data_zakonczenia_umowy,OLD.log_zmian);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
