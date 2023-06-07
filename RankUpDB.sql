SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


INSERT INTO `user` (`id_user`, `email`, `name`, `password`, `photo`, `surname`, `username`) VALUES
(1, 'alessandroG@gmail.com', 'Alessandro', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Forig00.deviantart.net%2F141d%2Ff%2F2016%2F151%2F7%2F6%2Foshawott_by_crystal_ribbon-da4g4jw.png&f=1&nofb=1&ipt=23ed5d634a002d0e9387ee5c9e0e58d9737a8a4f3affd9680d787eeb2a887715&ipo=images', 'Gabriele', 'alessandroG'),
(2, 'gianluca@gmail.com', 'Gianluca', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.kf3rbDAdmf-urpG-Z05n-gHaH8%26pid%3DApi&f=1&ipt=67d2e19bcba93f6a71aa8c21592d4d03bc1e79261268e15cae9a4d336a8c1ff3&ipo=images', 'Minichetti', 'gianlux'),
(3, 'mike@gmail.com', 'Michele', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', 'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fpm1.narvii.com%2F5713%2F68203dffd35bec6c8812d76ecb1beb52f52d1338_hq.jpg&f=1&nofb=1&ipt=ae9ca9c1d3b8b2a70b989b184c4ea4618f3d2f0bce0b1fb59f2e21a9cd8007d0&ipo=images', 'Colangelo', 'mike'),
(4, 'paolo@gmail.com', 'Paolo', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.yymcxcWAYTVHAei-kXxiVAHaHa%26pid%3DApi&f=1&ipt=2611fe104410be7a9a8b38bb25d270e172a728582f3b2d248bace6786bf3ec57&ipo=images', 'Di Biase', 'paolo'),
(5, 'alessandroN@gmail.com', 'Alessandro', 'a9c39dc9078d3b9b8dc32f716ceba51aaada9c569cb8b02754ebeb02723b2a822dfeb212b563af5d2ff704dcc4d02228770b66c6ea39df3680b771d89d84af99', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimg.pokemondb.net%2Fartwork%2Flarge%2Frowlet.jpg&f=1&nofb=1&ipt=fda9a5a235f423c5bb08723cda158d9068b28b2e5ae16b2a5a20c2171ac12eb2&ipo=images', 'Notte', 'alessandroN');

INSERT INTO `team` (`id_team`, `code`, `name`, `photo`, `point_visibility`, `privacy`) VALUES
(1, '1772701268', 'Palestra', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.overloadgym.it%2Fwp-content%2Fuploads%2F2018%2F04%2FAttrezzi.jpg&f=1&nofb=1&ipt=7222e82270f5f4bf05dcb4987fcb4b28b30aa2ca49ebd4a7d837f9093aa037d4&ipo=images', b'1', b'1'),
(2, '1772490974', 'Due Figli', 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.kindpng.com%2Fpicc%2Fm%2F410-4108064_transparent-groups-of-people-clipart-team-icon-png.png&f=1&nofb=1&ipt=7e6d77faf7d2d967292fd2c9900358d6078b1dad3e041cc7d26632084638e101&ipo=images', b'0', b'0'),
(3, '1772611147', 'Università', 'https://www.informamolise.com/wp-content/uploads/2015/05/unimol.png', b'0', b'1'),
(4, '1772130888', 'Corso Ingegneria', 'https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fleadingedgeleadership.com%2Fwp-content%2Fuploads%2F2017%2F08%2Fmanagement-dev-2.jpg&f=1&nofb=1&ipt=7f723fe128b79e1025e235d75247d45f40d0ea9f312c22664346074ab2ea3522&ipo=images', b'1', b'1'),
(5, '1771948511', 'Coro', 'https://www.citynow.it/wp-content/uploads/2015/09/coro.jpg', b'1', b'0');

INSERT INTO `admin_manage_team` (`id_admin`, `id_team`, `id_user`) VALUES
(2, 1, 2),
(4, 2, 4),
(6, 3, 4),
(8, 4, 5),
(10, 5, 1);


INSERT INTO `user_joins_team` (`id`, `points`, `accepted`, `id_team`, `id_user`) VALUES
(2, 0, 1, 1, 4),
(3, 0, 1, 1, 1),
(4, 0, 1, 1, 5),
(5, 0, 1, 3, 3),
(6, 0, 1, 3, 1);

INSERT INTO `prize` (`id_prize`, `name`, `price`, `id_admin`, `id_team`) VALUES
(1, 'Pranzo', 5, 10, 5),
(2, 'Cena', 10, 10, 5),
(3, '+1 al voto finale', 50, 8, 4),
(4, '+0,5 al voto finale', 25, 8, 4),
(5, 'Menù gratis', 100, 6, 3),
(6, 'Esenzione tasse', 1000, 6, 3),
(7, 'Vacanza', 5, 4, 2),
(8, 'Un mese di Game Pass', 5, 4, 2),
(9, 'Caffè offerto', 2, 2, 1),
(10, '3 mesi gratis', 10, 2, 1);

INSERT INTO `rule` (`id_rule`, `description`, `name`, `points`, `id_admin`, `id_team`) VALUES
(2, 'Allenarsi 3 volte a settimana', 'Allenamento', 20, 2, 1),
(3, 'Vivi sano', 'Non fumare', 30, 2, 1),
(4, 'Fare compiti a casa', 'Fare compiti', 40, 4, 2),
(5, 'Ordinare la camera', 'Ordinare la camera', 30, 4, 2),
(6, 'Conoscere la programmazione ricorsiva (a\' ricorsion)', 'Conoscere la ricorsione', 100, 6, 3),
(7, 'Passare gli esami', 'Passare gli esami', 50, 6, 3),
(8, 'Partecipare alla sperimentazione', 'Partecipare all sperimentazione', 30, 8, 4),
(9, 'Conoscere le note', 'Conoscere le note', 50, 10, 5);

INSERT INTO `task` (`id_task`, `description`, `end_date`, `name`, `points`, `start_date`, `id_admin`, `id_team`) VALUES
(1, 'Fare 20 push', '2023-09-27 00:00:00.000000', 'Fare 20 push-up', 10, '2023-06-07 15:21:32.606000', 2, 1),
(2, '40 minuti di corsa', '2023-10-13 00:00:00.000000', '40 minuti di corsa', 30, '2023-06-07 15:21:32.606000', 2, 1),
(3, 'Comprare la frutta', '2023-12-01 00:00:00.000000', 'Fare la spesa', 5, '2023-06-07 15:27:56.157000', 4, 2),
(4, 'Portare fuori il cane', '2023-10-15 00:00:00.000000', 'Portare fuori il cane', 10, '2023-06-07 15:35:45.270000', 4, 2),
(5, 'Passare l\'esonero', '2023-08-24 00:00:00.000000', 'Passare esonero', 5, '2023-06-07 15:42:18.204000', 6, 3),
(6, 'Scrivere un paper', '2023-08-15 00:00:00.000000', 'Scrivere un paper', 15, '2023-06-07 15:45:14.227000', 6, 3),
(7, 'Passare esonero', '2023-09-12 00:00:00.000000', 'Passare esonero', 50, '2023-06-07 15:48:32.910000', 8, 4),
(8, 'Fare le prove', '2023-11-09 00:00:00.000000', 'Fare le prove', 40, '2023-06-07 15:49:47.281000', 10, 5);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
