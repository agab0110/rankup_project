-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Creato il: Mag 15, 2023 alle 19:37
-- Versione del server: 10.9.5-MariaDB-1:10.9.5+maria~ubu2204
-- Versione PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `RankUpDB`
--
--
-- Dump dei dati per la tabella `team`
--

INSERT INTO `team` (`id_team`, `name`, `photo`, `point_visibility`, `privacy`) VALUES
(1, 'universita', NULL, b'1', b'1'),
(2, 'palestra', NULL, b'1', b'0');

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id_user`, `email`, `name`, `password`, `photo`, `surname`, `username`) VALUES
(1, 'g.minichetti@studenti.unimol.it', 'gianluca', '795c5652628e1eba6430b578b169b44ff1785f24b139526ae66c7c49d5e689dc2e401048c30bc37eb63f537e5fb5856b25f7b9c0b486fe8524ddc2d9d86f1be7', NULL, 'minichetti', 'gianlux');

--
--
-- Dump dei dati per la tabella `admin_manage_team`
--

INSERT INTO `admin_manage_team` (`id_admin`, `id_team`, `id_user`) VALUES
(1, 1, 1);

--
-- Dump dei dati per la tabella `notification`
--

INSERT INTO `notification` (`id_notification`, `date`, `description`, `title`, `id_team`) VALUES
(1, '2023-05-09', 'accettato nel team', 'richiesta partecipazione team', 1);

--
-- Dump dei dati per la tabella `prize`
--

INSERT INTO `prize` (`id_prize`, `name`, `price`, `id_admin`, `id_team`) VALUES
(1, 'caffe offerto', 10, 1, 1),
(2, 'pranzo offerto', 16, 1, 1);

--
-- Dump dei dati per la tabella `rule`
--

INSERT INTO `rule` (`id_rule`, `description`, `name`, `points`, `id_admin`, `id_team`) VALUES
(1, 'fare compiti', 'compiti', 10, 1, 1),
(2, 'studiare per esame', 'studio', 10, 1, 1);

--
-- Dump dei dati per la tabella `rule_completed`
--

INSERT INTO `rule_completed` (`id_rule_completed`, `attached`, `bonus_points`, `comment`, `revision_date`, `status`, `timestamp`, `id_admin`, `id_rule`, `id_user`) VALUES
(1, NULL, 2, 'rule completata', '2023-05-15 21:03:05.000000', 1, '2023-05-11 21:03:05.000000', 1, 1, 1);

--
-- Dump dei dati per la tabella `task`
--

INSERT INTO `task` (`id_task`, `description`, `end_date`, `name`, `points`, `start_date`, `id_admin`, `id_team`) VALUES
(1, 'fare esonero ingegneria', '2023-05-19 17:30:57.000000', NULL, 20, '2023-05-14 17:30:57.000000', 1, 1),
(2, 'fare esonero ricerca operativa', '2023-05-19 17:30:57.000000', NULL, 20, '2023-05-14 17:30:57.000000', 1, 1);

--
-- Dump dei dati per la tabella `task_completed`
--

INSERT INTO `task_completed` (`id_task_completed`, `attached`, `bonus_points`, `comment`, `revision_date`, `status`, `timestamp`, `id_admin`, `id_task`, `id_user`) VALUES
(1, NULL, 2, 'task accettato', '2023-05-18 17:33:08.000000', 1, '2023-05-15 17:33:08.000000', 1, 1, 1),
(2, NULL, 2, 'task accettato', '2023-05-18 17:33:08.000000', 0, '2023-05-15 17:33:08.000000', 1, 1, 1);

--
-- Dump dei dati per la tabella `task_for_specific_user`
--

INSERT INTO `task_for_specific_user` (`id_task`, `id_user`) VALUES
(1, 1);

-- Dump dei dati per la tabella `user_get_prize`
--

INSERT INTO `user_get_prize` (`id_prize`, `id_user`, `date`) VALUES
(1, 1, '2023-05-22 21:03:48.000000');

--
-- Dump dei dati per la tabella `user_joins_team`
--

INSERT INTO `user_joins_team` (`id_team`, `id_user`, `accepted`, `points`) VALUES
(1, 1, b'0', 110);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
