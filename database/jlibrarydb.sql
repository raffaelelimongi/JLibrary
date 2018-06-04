-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 04, 2018 alle 16:46
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
(4, 'action'),
(5, 'adventure'),
(3, 'drama'),
(2, 'fantasy'),
(1, 'horror');

-- --------------------------------------------------------

--
-- Struttura della tabella `immagine`
--

CREATE TABLE `immagine` (
  `ID` int(11) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `formato` char(4) NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `opera`
--

CREATE TABLE `opera` (
  `ID` int(11) NOT NULL,
  `titolo` varchar(100) NOT NULL,
  `autore` varchar(45) NOT NULL,
  `data_pubb` date NOT NULL,
  `IDcategoria` int(10) UNSIGNED DEFAULT NULL,
  `IDimmagine` int(10) UNSIGNED DEFAULT NULL,
  `IDoperatrascritta` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `opera`
--

INSERT INTO `opera` (`ID`, `titolo`, `autore`, `data_pubb`, `IDcategoria`, `IDimmagine`, `IDoperatrascritta`) VALUES
(3, 'iononsonounabomba', 'bombolo', '0000-00-00', 2, NULL, NULL),
(4, 'iosonounpanda', 'panda', '0000-00-00', 4, NULL, NULL),
(5, 'prova', 'test', '2018-06-14', 1, 8, NULL),
(8, 'prova', 'test', '2018-06-14', 1, 9, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `opera_trascritta`
--

CREATE TABLE `opera_trascritta` (
  `ID` int(10) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `testo` text NOT NULL,
  `IDimmagine` int(10) UNSIGNED NOT NULL,
  `IDutente` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `ruolo`
--

CREATE TABLE `ruolo` (
  `ID` int(11) NOT NULL,
  `privilegio` varchar(45) NOT NULL,
  `IDutente` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ruolo`
--

INSERT INTO `ruolo` (`ID`, `privilegio`, `IDutente`) VALUES
(1, 'utente base', 72),
(3, 'utente base', 76);

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
  `livello` tinyint(4) NOT NULL DEFAULT '0',
  `trascrittore` tinyint(1) NOT NULL DEFAULT '0',
  `vip` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`ID`, `username`, `password`, `email`, `nome`, `cognome`, `livello`, `trascrittore`, `vip`) VALUES
(40, 'raffa', 'bomba', 'prova@email.it', 'Raffaele', 'Limongi', 0, 1, 1),
(67, 'dico', 'test', 'ciao', 'Dicono', 'qualcosa', 0, 1, 0),
(68, 'savic', 'bomba', 'segejSavic@gmail.com', 'Sergej', 'Milinkovic', 0, 0, 1),
(72, 'pogbi', 'bombi', 'ss', 'Paul', 'Pogba', 0, 0, 0),
(76, 'pobba', 'nullo', 'porva', 'groote', 'niagara', 0, 0, 0);

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
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Indici per le tabelle `opera`
--
ALTER TABLE `opera`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `opera_categoria` (`IDcategoria`),
  ADD KEY `opera_immagine` (`IDimmagine`),
  ADD KEY `opera_operatrascritta` (`IDoperatrascritta`);

--
-- Indici per le tabelle `opera_trascritta`
--
ALTER TABLE `opera_trascritta`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `operatrascritta_immagine` (`IDimmagine`),
  ADD KEY `operatrascritta_utente` (`IDutente`);

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
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `categoria`
--
ALTER TABLE `categoria`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `immagine`
--
ALTER TABLE `immagine`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `opera`
--
ALTER TABLE `opera`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT per la tabella `opera_trascritta`
--
ALTER TABLE `opera_trascritta`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `opera`
--
ALTER TABLE `opera`
  ADD CONSTRAINT `opera_categoria` FOREIGN KEY (`IDcategoria`) REFERENCES `categoria` (`ID`),
  ADD CONSTRAINT `opera_immagine` FOREIGN KEY (`IDimmagine`) REFERENCES `immagine` (`ID`),
  ADD CONSTRAINT `opera_operatrascritta` FOREIGN KEY (`IDoperatrascritta`) REFERENCES `opera_trascritta` (`ID`);

--
-- Limiti per la tabella `opera_trascritta`
--
ALTER TABLE `opera_trascritta`
  ADD CONSTRAINT `operatrascritta_immagine` FOREIGN KEY (`IDimmagine`) REFERENCES `immagine` (`ID`),
  ADD CONSTRAINT `operatrascritta_utente` FOREIGN KEY (`IDutente`) REFERENCES `utente` (`ID`);

--
-- Limiti per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  ADD CONSTRAINT `ruolo_utente` FOREIGN KEY (`IDutente`) REFERENCES `utente` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
