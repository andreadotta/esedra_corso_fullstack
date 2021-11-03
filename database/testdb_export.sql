-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: mysqlcorso
-- Creato il: Nov 03, 2021 alle 19:51
-- Versione del server: 8.0.17
-- Versione PHP: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `corsi`
--

CREATE TABLE `corsi` (
  `id_corso` int(11) NOT NULL,
  `nome_corso` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `corsi`
--

INSERT INTO `corsi` (`id_corso`, `nome_corso`) VALUES
(1, 'Letteratura Italiana'),
(2, 'Lingue e letturatura Inglese'),
(3, 'Matematica');

-- --------------------------------------------------------

--
-- Struttura della tabella `studente`
--

CREATE TABLE `studente` (
  `matricola` int(11) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `data_di_nascita` date NOT NULL,
  `comune_di_nascita` varchar(100) NOT NULL,
  `comune_di_residenza` varchar(100) NOT NULL,
  `corso` int(11) DEFAULT NULL,
  `esami_sostenuti` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `studente`
--

INSERT INTO `studente` (`matricola`, `cognome`, `nome`, `data_di_nascita`, `comune_di_nascita`, `comune_di_residenza`, `corso`, `esami_sostenuti`) VALUES
(1, 'Baldi', 'Roberto', '1990-11-09', 'FIRENZE', 'PISTOIA', 1, 12),
(2, 'Rossi', 'Franco', '2000-03-16', 'GENOVA', 'MILANO', 3, 9),
(3, 'Verdi', 'Giuseppe', '1975-03-05', 'VERONA', 'ROMA', 1, 3),
(4, 'Bruni', 'Giuseppe', '2001-03-05', 'MILANO', 'MILANO', 2, 0),
(5, 'Bianchi', 'Giorgio', '1985-03-05', 'VERONA', 'ROMA', 1, 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `corsi`
--
ALTER TABLE `corsi`
  ADD PRIMARY KEY (`id_corso`);

--
-- Indici per le tabelle `studente`
--
ALTER TABLE `studente`
  ADD PRIMARY KEY (`matricola`),
  ADD KEY `corso` (`corso`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `corsi`
--
ALTER TABLE `corsi`
  MODIFY `id_corso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `studente`
--
ALTER TABLE `studente`
  MODIFY `matricola` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `studente`
--
ALTER TABLE `studente`
  ADD CONSTRAINT `studente_ibfk_1` FOREIGN KEY (`corso`) REFERENCES `corsi` (`id_corso`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
