-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               5.7.11 - MySQL Community Server (GPL)
-- Serwer OS:                    Win32
-- HeidiSQL Wersja:              9.5.0.5280
-- --------------------------------------------------------

INSERT INTO `dostep` (`id`, `uzytkownik`, `haslo`, `imie`, `nazwisko`, `domyslny_kontekst`, `log_zmian`) VALUES
(3, 'demo', '2a97516c354b68848cdbd8f54a226a0a55b21ed138e207ad6c5cbb9c00aa5aea', 'demo', 'demo', 72, '2018-09-24 11:37:20');
/*!40000 ALTER TABLE `dostep` ENABLE KEYS */;

INSERT INTO `stowarzyszenie` (`id`, `nazwa`, `pelna_nazwa`, `nip`, `regon`, `ulica`, `nr_lokalu`, `kod_pocztowy`, `poczta`, `miejscowosc`, `skladka_wypadkowa`, `data_skladki_wypadkowej`, `log_zmian`) VALUES
(72, 'testowa', 'Zażółć gęślą jaźń', '1', '1', 'Wygoda, 34', '1', '21-580', '21-580', 'Wisznice', 1.22, '2018-08-01', '2018-08-31 20:51:15');
