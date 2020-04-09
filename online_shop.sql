-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Янв 18 2020 г., 21:04
-- Версия сервера: 10.1.9-MariaDB
-- Версия PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `online_shop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `authority`
--

CREATE TABLE `authority` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `authority`
--

INSERT INTO `authority` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `category` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `image`, `category`) VALUES
(1, 'asdfsad', 'Some quick example text to build on the card title and make up the bulk of the card''s content.', '12.22', 'cake1.jpg', 0),
(2, 'asdf', 'Some quick example text to build on the card title and make up the bulk of the card''s content.', '12.55', 'cake3.jpg', 0),
(3, 'asdf', 'Some quick example text to build on the card title and make up the bulk of the card''s content.', '12.55', 'cake7.jpg', 0),
(4, 'asdf laskdf', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '10.20', 'cake4.jpg', 0),
(5, 'asdf laskdf', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '5.70', 'cake9.jpg', 0),
(6, 'asdf laskdf', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '10.20', 'cake26.jpg', 0),
(8, 'pie', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '10.20', 'pie.jpg', 1),
(7, 'baked-pie', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '10.20', 'baked-pie.jpg', 1),
(9, 'pie1', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '15.20', 'pie1.jpg', 1),
(10, 'pie2', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '18.20', 'pie2.jpg', 1),
(11, 'pie3', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '19.20', 'pie3.jpg', 1),
(12, 'pie4', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '21.20', 'pie4.jpg', 1),
(13, 'pie5', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '24.20', 'pie5.jpg', 1),
(14, 'pie6', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '27.20', 'pie6.jpg', 1),
(15, 'Bread1', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '19.20', 'Bread1.jpg', 2),
(16, 'bread2', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '11.20', 'bread2.jpg', 2),
(17, 'bread-vicky', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '15.20', 'bread-vicky.jpg', 2),
(18, 'header-bread', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '17.20', 'header-bread.jpg', 2),
(19, 'kamut', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '17.20', 'kamut.jpg', 2),
(20, 'seed-bread', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '13.20', 'seed-bread.jpg', 2),
(21, 'pie7', 'Are you looking for a massage pillow? This massage pillow would be a best choice for you. It helps to promote the body blood circulation and relieve your whole body effectively. With 16 massage balls and its heating function, kneading and penetrating moti', '19.20', 'pie7.jpeg', 1),
(22, 'xcbxcvb', 'xcvbxcv', '1.21', 'xcvbxc', 0),
(24, 'hui', 'Demo project for Spring Boot', '2.12', 'sdfgsdfg', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`) VALUES
(1, 'u', '$2a$10$FlrosqT/gaQ8iw2D3n2kROFBgZu3r8FMKc6ldBbPS1m3z7PVesgbS', 'serg@gmail.com', 1),
(3, 'qwer', '$2y$12$QsRRibwYF9lQnGnFiyykxOt9YPdHTPB4mVoR3E7UnrkM/Yr7hhYQe', 'qwer@gmail.com', 0),
(4, 'a', '$2y$12$RBE3rlsFUUKLwEvAkEtzI.XIzpY4GGEnmCmfEgoBUOhFTMjmeprjm', 'asdf@gmail.com', 1),
(18, 'user', '$2a$10$LiUfVnHLQS0crl2EY1R3W.lWdl8rejNdRmPrAWB8Q.mOMNeYojGOa', 'shebatula@gmail.com', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user_authority`
--

CREATE TABLE `user_authority` (
  `user_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `user_authority`
--

INSERT INTO `user_authority` (`user_id`, `authority_id`) VALUES
(1, 2),
(3, 2),
(4, 2),
(4, 1),
(18, 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Индексы таблицы `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `authority`
--
ALTER TABLE `authority`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
