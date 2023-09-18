-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Set-2023 às 11:43
-- Versão do servidor: 10.4.27-MariaDB
-- versão do PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `versbosaapi`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `products`
--

CREATE TABLE `products` (
  `Id` int(11) NOT NULL,
  `NameProduct` int(11) NOT NULL,
  `IdCompra` longtext NOT NULL,
  `Description` longtext DEFAULT NULL,
  `Created` longtext NOT NULL,
  `MesPremium` int(11) NOT NULL,
  `IdServe` longtext NOT NULL,
  `NameServe` longtext DEFAULT NULL,
  `IdUserServe` longtext NOT NULL,
  `NameUserDiscord` longtext DEFAULT NULL,
  `Priority` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `products`
--

INSERT INTO `products` (`Id`, `NameProduct`, `IdCompra`, `Description`, `Created`, `MesPremium`, `IdServe`, `NameServe`, `IdUserServe`, `NameUserDiscord`, `Priority`) VALUES
(4, 1, '953665', 'UpdateAndroid', '2023-09-15 22:49:12', 5, '3456789', 'UpdateAndroid', '3456789', 'UpdateAndroid', 1),
(5, 1, '08765', 'UpdateWithTimeOut', '2023-09-17 23:54:50', 1, '4657869', 'Server', '097683', 'ServerClient', 1),
(7, 1, '126213', 'Bot Musica', '2023-09-17 23:17:50', 12, '6466732', 'VerbsoaServer', '567849', 'JusteClient', 1),
(8, 1, '469518', 'VerbosaBot', '2023-09-17 23:00:29', 6, '74652', 'ServerClient', '79324', 'ClientUser', 1),
(11, 1, '982425', 'Test', '2023-09-18 10:27:39', 6, '45673', 'Server', '87654', 'Alex', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `__efmigrationshistory`
--

CREATE TABLE `__efmigrationshistory` (
  `MigrationId` varchar(150) NOT NULL,
  `ProductVersion` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `__efmigrationshistory`
--

INSERT INTO `__efmigrationshistory` (`MigrationId`, `ProductVersion`) VALUES
('20230915224254_createdatabase', '7.0.9');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`Id`);

--
-- Índices para tabela `__efmigrationshistory`
--
ALTER TABLE `__efmigrationshistory`
  ADD PRIMARY KEY (`MigrationId`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `products`
--
ALTER TABLE `products`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
