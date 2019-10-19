-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Окт 12 2019 г., 12:46
-- Версия сервера: 10.1.9-MariaDB
-- Версия PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `web`
--

-- --------------------------------------------------------

--
-- Структура таблицы `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `content` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `flagFix` tinyint(4) NOT NULL,
  `flagRecycle` tinyint(4) NOT NULL,
  `fileName` varchar(50) NOT NULL DEFAULT 'no file'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `tasks`
--

INSERT INTO `tasks` (`id`, `idUser`, `content`, `date`, `flagFix`, `flagRecycle`, `fileName`) VALUES
(2, 25, 'Say', '2019-05-09', 0, 1, 'no file'),
(5, 25, 'asdfasdf', '2019-03-11', 1, 1, 'no file'),
(11, 25, 'asdfsafd', '2019-12-20', 0, 0, 'no file'),
(23, 75, 'today1234', '2019-05-01', 0, 0, 'webProjectFormulation.docx'),
(24, 75, 'qqqqqq', '2019-03-30', 0, 1, 'no file'),
(31, 75, 'today', '2019-03-31', 1, 0, 'no file'),
(32, 75, 'today today today today today today today today today ', '2019-04-06', 0, 1, 'web3.war'),
(33, 25, 'tomorrow', '2019-04-07', 1, 0, 'I:epamlab2practicefinalTaskOnline_shop_task.txt'),
(34, 76, 'erw', '2019-04-14', 0, 0, 'I:epamlabwebjee-eclipse.docx'),
(45, 25, 'asdf', '2019-04-21', 1, 0, 'no file'),
(48, 25, 'asdfasdf asdf', '2019-06-25', 1, 0, 'no file'),
(49, 25, 'asdf', '2019-04-22', 0, 0, 'no file'),
(50, 25, 'asdf', '2019-04-22', 0, 0, 'no file'),
(51, 25, 'asdf', '2019-12-12', 0, 0, 'no file'),
(53, 25, 'asdf asdfasdf', '2019-05-18', 0, 0, 'no file');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `name`, `email`, `password`) VALUES
(1, 'sys', 'sys', 'sys@mail.com', '111'),
(2, 'user', 'user', 'user@mail.com', '000'),
(25, 'asdf', 'asdf', 'asdf', 'asdf'),
(75, 't', 't', 't', 't'),
(76, 'r', 'r', 'r', 'r');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `date` (`date`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
