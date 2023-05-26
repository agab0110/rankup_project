-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Creato il: Mag 26, 2023 alle 08:54
-- Versione del server: 10.9.5-MariaDB-1:10.9.5+maria~ubu2204
-- Versione PHP: 8.1.18

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
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id_user`, `email`, `name`, `password`, `photo`, `surname`, `username`) VALUES
(1, 'gianlu@gmail.com', 'Gianluca', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', NULL, 'Minichetti', 'gianlux'),
(2, 'mike@gmail.com', 'Michele', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', NULL, 'Colangelo', 'mike'),
(3, 'paolo@gmail.com', 'Paolo', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', NULL, 'Di Biase', 'paolo'),
(4, 'alessandroN@gmail.com', 'Alessandro', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', NULL, 'Notte', 'alessandroN'),
(5, 'alessandroG@gmail.com', 'Alessandro', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', NULL, 'Gabriele', 'alessandroG'),
(7, 'fasano@gmail.com', 'fausto', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', 'https://ionicframework.com/docs/img/demos/avatar.svg', 'fasano', 'fausto');

--
-- Dump dei dati per la tabella `team`
--

INSERT INTO `team` (`id_team`, `name`, `photo`, `point_visibility`, `privacy`) VALUES
(1, 'Universita', NULL, b'1', b'1'),
(2, 'Palestra', NULL, b'1', b'0'),
(3, 'Figli', NULL, b'1', b'1'),
(4, 'Scuola', NULL, b'1', b'0'),
(5, 'Ingegneria', NULL, b'1', b'1'),
(6, 'Revisione', 'this.blobURL?.toString()', b'0', b'1'),
(8, 'temp', 'https://t3.ftcdn.net/jpg/00/64/67/52/240_F_64675209_7ve2XQANuzuHjMZXP3aIYIpsDKEbF5dD.jpg', b'0', b'1');


--
-- Dump dei dati per la tabella `admin-manage-team`
--
INSERT INTO `admin_manage_team` (`id_admin`, `id_team`, `id_user`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5);

--
-- Dump dei dati per la tabella `admin_recive_notification`
--

INSERT INTO `admin_recive_notification` (`id`, `id_admin`, `id_notification`) VALUES
(1, 1, 2),
(2, 1, 3);

--
-- Dump dei dati per la tabella `notification`
--

INSERT INTO `notification` (`id_notification`, `date`, `description`, `title`, `id_team`, `id_user`) VALUES
(1, '2023-05-15', 'Prova notifica', 'Inserita nuova regola nel Team Università', 1, 2),
(2, '2023-05-15', 'Prova notifica', 'Inserito nuovo task nel Team Palestra', 5, 3),
(3, '2023-05-15', 'Prova notifica', 'Inserita nuova regola nel Team Università', 2, 1),
(4, '2023-05-23', 'Prova notifica', 'Inserita nuovo Task nel Team Figli', 3, 2),
(5, '2023-05-23', 'Prova notifica', 'Inserita nuovo Task nel Team Figli', 3, 2);

--
-- Dump dei dati per la tabella `prize`
--

INSERT INTO `prize` (`id_prize`, `name`, `price`, `id_admin`, `id_team`) VALUES
(1, 'Voto in più', 50, 1, 1),
(2, 'Pranzo offerto', 21, 2, 2),
(3, 'Vacanza', 30, 3, 3),
(4, 'Gita', 40, 4, 4),
(5, '30 a Ingegneria', 1, 5, 5),
(6, '30 e lode', 15, NULL, 6);

--
-- Dump dei dati per la tabella `rule`
--

INSERT INTO `rule` (`id_rule`, `description`, `name`, `points`, `id_admin`, `id_team`) VALUES
(1, 'Fare spesa ogni giorno', 'Spesa', 20, 3, 3),
(2, 'Allenarsi 3 volte a settimana', 'Allenamento', 50, 2, 2),
(3, 'Fare compiti ogni martedì', 'Fare compiti', 30, 3, 3),
(4, 'Passare tutti gli esoneri ', 'Passare esoneri', 100, 5, 5),
(5, 'Passare gli esami ', 'Passare esami', 100, 5, 1),
(6, 'Fare revisione entro le 13', 'Fare revisione', 20, NULL, 6),
(7, 'Fare revisione entro le 13', 'Fare revisione', 20, 1, 1),
(8, 'Prendere caffe', 'Andare al bar', 100, 5, 1),
(10, 'dfv', 'boh', 11, NULL, NULL);

--
-- Dump dei dati per la tabella `rule_completed`
--

INSERT INTO `rule_completed` (`id_rule_completed`, `attached`, `bonus_points`, `comment`, `revision_date`, `status`, `timestamp`, `id_admin`, `id_rule`, `id_user`) VALUES
(1, NULL, 4, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 1, 1, 2),
(2, NULL, 2, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 2, 2, 1),
(3, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 5, 5, 4),
(4, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 1, 1, 5),
(5, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 2, '2023-05-15 15:45:26.000000', 1, 3, 3),
(6, NULL, 4, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 1, 1, 1),
(7, NULL, 0, NULL, NULL, 1, '2023-05-21 12:06:07.687535', NULL, 1, 2),
(8, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 1, 3, 3),
(9, NULL, 1, 'bravo', NULL, 1, '2023-05-15 15:50:44.000000', 1, 1, 2),
(11, NULL, 1, 'bravo', '2023-05-23 15:48:10.000000', 2, '2023-05-08 15:51:02.000000', 1, 7, 2),
(12, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 2, '2023-05-15 15:45:26.000000', 5, 5, 3),
(13, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 5, 5, 3),
(16, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 2, '2023-05-15 15:45:26.000000', 5, 2, 3),
(17, NULL, 6, 'bravo', '2023-05-15 15:45:26.000000', 1, '2023-05-15 15:45:26.000000', 5, 8, 4);

--
-- Dump dei dati per la tabella `task`
--

INSERT INTO `task` (`id_task`, `description`, `end_date`, `name`, `points`, `start_date`, `id_admin`, `id_team`) VALUES
(1, 'Studiare per esonero ', '2023-05-10 15:51:57.000000', 'Studiare per esonero', 5, '2023-05-01 15:51:57.000000', 1, 1),
(2, 'Fare 20 push-up', '2023-05-10 15:51:57.000000', 'Fare 20 push-up', 7, '2023-05-01 15:51:57.000000', 2, 2),
(3, 'Pulire casa', '2023-05-10 15:51:57.000000', 'Pulire casa', 3, '2023-05-01 15:51:57.000000', 3, 3),
(4, 'Prendere sufficiente alla verifica', '2023-05-10 15:51:57.000000', 'Prendere sufficiente alla verifica', 1, '2023-05-01 15:51:57.000000', 4, 4),
(5, 'Passare esonero', '2023-05-10 15:51:57.000000', 'Passare esonero', 10, '2023-05-01 15:51:57.000000', 5, 5),
(6, 'Andare al bar', '2023-05-10 15:51:57.000000', 'Andare al bar', 5, '2023-05-01 15:51:57.000000', 1, 1);

--
-- Dump dei dati per la tabella `task_completed`
--

INSERT INTO `task_completed` (`id_task_completed`, `attached`, `bonus_points`, `comment`, `revision_date`, `status`, `timestamp`, `id_admin`, `id_task`, `id_user`) VALUES
(1, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 1, '2023-05-20 15:55:40.000000', 1, 1, 1),
(2, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 0, '2023-05-20 15:55:40.000000', 2, 2, 1),
(3, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 1, '2023-05-20 15:55:40.000000', 3, 3, 2),
(4, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 1, '2023-05-20 15:55:40.000000', 4, 4, 5),
(5, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 0, '2023-05-20 15:55:40.000000', 5, 5, 3),
(6, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 2, '2023-05-20 15:55:40.000000', 1, 1, 1),
(7, NULL, 1, 'bravo', '2023-05-19 15:55:40.000000', 1, '2023-05-20 15:55:40.000000', 1, 6, 1);

--
-- Dump dei dati per la tabella `task_for_specific_user`
--

INSERT INTO `task_for_specific_user` (`id_task`, `id_user`) VALUES
(1, 1),
(5, 3);


--
-- Dump dei dati per la tabella `user`
--


--
-- Dump dei dati per la tabella `user_get_prize`
--

INSERT INTO `user_get_prize` (`id_prize`, `id_user`, `date`) VALUES
(1, 1, '2023-05-20 15:58:32.000000'),
(4, 1, '2023-05-20 15:58:32.000000'),
(5, 3, '2023-05-20 15:58:32.000000');

--
-- Dump dei dati per la tabella `user_joins_team`
--

INSERT INTO `user_joins_team` (`id_team`, `id_user`, `points`, `accepted`, `id`) VALUES
(1, 2, 100, 1, 0),
(1, 4, 160, 1, 0),
(2, 1, 200, 1, 0),
(2, 3, 90, 1, 0),
(3, 2, 40, 1, 0),
(4, 5, 160, 1, 0),
(5, 3, 10, 1, 0);

--
-- Dump dei dati per la tabella `user_recive_notification`
--

INSERT INTO `user_recive_notification` (`id`, `id_notification`, `id_user`) VALUES
(1, 1, 2),
(2, 3, 7);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
