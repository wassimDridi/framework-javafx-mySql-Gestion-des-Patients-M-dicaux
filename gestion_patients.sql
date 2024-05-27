-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 27 mai 2024 à 22:52
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_patients`
--

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

CREATE TABLE `medicament` (
  `ref` int(11) NOT NULL,
  `libelle` varchar(30) DEFAULT NULL,
  `prix` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`ref`, `libelle`, `prix`) VALUES
(123, 'adol', 200),
(3304, 'Aspirin', 20),
(9987, 'Amoxicillin', 99);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `cin` varchar(20) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `sexe` varchar(15) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `cinPersonnel` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`cin`, `nom`, `prenom`, `sexe`, `tel`, `cinPersonnel`) VALUES
(' 02668746', ' zahra', ' jaloul', ' F', ' 20214876', '30225700'),
(' 11163910', ' jeber', ' smii', ' m', ' 25447996', '33033000'),
(' 13225588', ' farah', ' manaai', ' F', ' 33665544', '30225700'),
('dj', 'dfgh', 'fg', 'ch', '2222', '30225700');

-- --------------------------------------------------------

--
-- Structure de la table `patientmed`
--

CREATE TABLE `patientmed` (
  `refmed` int(11) NOT NULL,
  `cinPat` varchar(20) NOT NULL,
  `cinPer` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patientmed`
--

INSERT INTO `patientmed` (`refmed`, `cinPat`, `cinPer`) VALUES
(123, ' 11163910', '30225700'),
(123, ' 13225588', '30225700'),
(3304, ' 11163910', '30225700'),
(9987, ' 11163910', '30225700');

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

CREATE TABLE `personnel` (
  `cin` varchar(20) NOT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `login` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `fonction` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`cin`, `nom`, `prenom`, `login`, `password`, `fonction`) VALUES
('11578964', 'amira', 'boukhriss', '22', '22', 'doctor'),
('14885776', 'donya', 'sherny', '33', '33', 'doctor'),
('30225700', 'ahmedali', 'bou3a', 'admin', 'admin', 'admin'),
('33033000', 'ali', 'Slim', '11', '11', 'person'),
('33661188', 'Dridi', 'Wassim', '123', '123', 'doctor');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `medicament`
--
ALTER TABLE `medicament`
  ADD PRIMARY KEY (`ref`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`cin`),
  ADD KEY `fk_cinPersonnel` (`cinPersonnel`);

--
-- Index pour la table `patientmed`
--
ALTER TABLE `patientmed`
  ADD PRIMARY KEY (`refmed`,`cinPat`),
  ADD KEY `cinPer` (`cinPer`),
  ADD KEY `fk_patientmed_cinPat` (`cinPat`);

--
-- Index pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD PRIMARY KEY (`cin`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `fk_cinPersonnel` FOREIGN KEY (`cinPersonnel`) REFERENCES `personnel` (`cin`) ON DELETE CASCADE,
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`cinPersonnel`) REFERENCES `personnel` (`cin`);

--
-- Contraintes pour la table `patientmed`
--
ALTER TABLE `patientmed`
  ADD CONSTRAINT `fk_patientmed_cinPat` FOREIGN KEY (`cinPat`) REFERENCES `patient` (`cin`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_patientmed_refmed` FOREIGN KEY (`refmed`) REFERENCES `medicament` (`ref`) ON DELETE CASCADE,
  ADD CONSTRAINT `patientmed_ibfk_1` FOREIGN KEY (`refmed`) REFERENCES `medicament` (`ref`),
  ADD CONSTRAINT `patientmed_ibfk_2` FOREIGN KEY (`cinPat`) REFERENCES `patient` (`cin`),
  ADD CONSTRAINT `patientmed_ibfk_3` FOREIGN KEY (`cinPer`) REFERENCES `personnel` (`cin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
