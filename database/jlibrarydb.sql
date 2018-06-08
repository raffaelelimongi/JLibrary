-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 08, 2018 alle 19:16
-- Versione del server: 10.1.32-MariaDB
-- Versione PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jlibrarydb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `categoria`
--

CREATE TABLE `categoria` (
  `ID` int(11) UNSIGNED NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `categoria`
--

INSERT INTO `categoria` (`ID`, `nome`) VALUES
(5, 'action'),
(6, 'adventure'),
(4, 'crime'),
(7, 'drama'),
(2, 'fantasy'),
(3, 'historical'),
(1, 'horror');

-- --------------------------------------------------------

--
-- Struttura della tabella `immagine`
--

CREATE TABLE `immagine` (
  `ID` int(10) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `formato` char(4) NOT NULL,
  `image` varchar(100) NOT NULL,
  `IDopera` int(10) UNSIGNED DEFAULT NULL,
  `IDoperatrascritta` int(10) UNSIGNED DEFAULT NULL,
  `accept` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `opera`
--

CREATE TABLE `opera` (
  `ID` int(10) UNSIGNED NOT NULL,
  `titolo` varchar(100) NOT NULL,
  `autore` varchar(45) NOT NULL,
  `data_pubb` date NOT NULL,
  `IDoperatrascritta` int(10) UNSIGNED DEFAULT NULL,
  `IDcategoria` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `opera`
--

INSERT INTO `opera` (`ID`, `titolo`, `autore`, `data_pubb`, `IDoperatrascritta`, `IDcategoria`) VALUES
(2, 'sonounpanda', 'edjnjed', '2018-06-19', 1, 1),
(3, 'prova2', 'bimbo', '2018-06-13', 2, 3),
(5, 'sonobuefbew', 'cccc', '2018-06-19', NULL, 3),
(39, 'prova', 'prova1', '2018-06-01', NULL, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `opera_trascritta`
--

CREATE TABLE `opera_trascritta` (
  `ID` int(10) UNSIGNED NOT NULL,
  `testo` text,
  `accept` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `opera_trascritta`
--

INSERT INTO `opera_trascritta` (`ID`, `testo`, `accept`) VALUES
(1, 'ciao,questa è la mia prima prova.', 1),
(2, 'questa,invece è la seconda', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `ruolo`
--

CREATE TABLE `ruolo` (
  `ID` int(11) NOT NULL,
  `privilegio` varchar(45) NOT NULL DEFAULT 'utente base',
  `IDutente` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ruolo`
--

INSERT INTO `ruolo` (`ID`, `privilegio`, `IDutente`) VALUES
(15, 'utente base', 15),
(18, 'utente base', 18),
(19, 'supervisor', 19),
(21, 'admin', 21);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `ID` int(10) UNSIGNED NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(35) NOT NULL,
  `cognome` varchar(40) NOT NULL,
  `livello` float NOT NULL DEFAULT '0',
  `trascrittore` tinyint(1) NOT NULL DEFAULT '0',
  `vip` tinyint(1) NOT NULL DEFAULT '0',
  `ric_trascrittore` tinyint(1) NOT NULL DEFAULT '0',
  `IDtrascrizione` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`ID`, `username`, `password`, `email`, `nome`, `cognome`, `livello`, `trascrittore`, `vip`, `ric_trascrittore`, `IDtrascrizione`) VALUES
(15, 'bsedb', 'dio', 'dbhebd', 'edbhedb', 'bsehdb', 0, 0, 0, 2, NULL),
(18, 'etde', 'dio', 'sss', 'deyveydh', 'vydvyvy', 0, 0, 0, 2, NULL),
(19, 'fafa', 'fafed', 'ki', 'jdikjnd', 'idni', 0, 0, 0, 0, NULL),
(21, 'raffa', 'bomba', 'dede', 'Raffaele', 'lImongi', 0, 1, 0, 1, NULL);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Indici per le tabelle `immagine`
--
ALTER TABLE `immagine`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `nome` (`nome`),
  ADD KEY `immagine_opera` (`IDopera`),
  ADD KEY `immagine_operatrascritta` (`IDoperatrascritta`);

--
-- Indici per le tabelle `opera`
--
ALTER TABLE `opera`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `opera_categoria` (`IDcategoria`),
  ADD KEY `opera_operatrascritta` (`IDoperatrascritta`);

--
-- Indici per le tabelle `opera_trascritta`
--
ALTER TABLE `opera_trascritta`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `ruolo`
--
ALTER TABLE `ruolo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ruolo_utente` (`IDutente`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `utente_operatrascritta` (`IDtrascrizione`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `categoria`
--
ALTER TABLE `categoria`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `immagine`
--
ALTER TABLE `immagine`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `opera`
--
ALTER TABLE `opera`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT per la tabella `opera_trascritta`
--
ALTER TABLE `opera_trascritta`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `immagine`
--
ALTER TABLE `immagine`
  ADD CONSTRAINT `immagine_opera` FOREIGN KEY (`IDopera`) REFERENCES `opera` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `immagine_operatrascritta` FOREIGN KEY (`IDoperatrascritta`) REFERENCES `opera_trascritta` (`ID`) ON DELETE CASCADE;

--
-- Limiti per la tabella `opera`
--
ALTER TABLE `opera`
  ADD CONSTRAINT `opera_categoria` FOREIGN KEY (`IDcategoria`) REFERENCES `categoria` (`ID`),
  ADD CONSTRAINT `opera_operatrascritta` FOREIGN KEY (`IDoperatrascritta`) REFERENCES `opera_trascritta` (`ID`) ON DELETE SET NULL;

--
-- Limiti per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  ADD CONSTRAINT `ruolo_utente` FOREIGN KEY (`IDutente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE;

--
-- Limiti per la tabella `utente`
--
ALTER TABLE `utente`
  ADD CONSTRAINT `utente_operatrascritta` FOREIGN KEY (`IDtrascrizione`) REFERENCES `opera_trascritta` (`ID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
