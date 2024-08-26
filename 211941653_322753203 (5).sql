-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: אוגוסט 01, 2023 בזמן 07:46 PM
-- גרסת שרת: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `211941653_322753203`
--

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `lists`
--

CREATE TABLE `lists` (
  `list_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `owner_email` varchar(255) NOT NULL,
  `shared_users` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `lists`
--

INSERT INTO `lists` (`list_id`, `title`, `create_date`, `owner_email`, `shared_users`) VALUES
(24, 'testing', '2023-07-23 14:32:23', 'abed@abed.com', 'taha'),
(25, 'coking', '2023-07-23 14:45:38', 'taha@taha.com', 'abed@abed.com'),
(26, 'cleaning', '2023-07-24 18:29:16', 'test@test.test', 'abed'),
(27, 'testing', '2023-07-24 18:29:56', 'abed@abed.com', 'taha@taha.com'),
(28, 'gym', '2023-07-24 18:31:03', 'taha@taha.com', 'abed@abed.com,test@test.test'),
(29, 'cardio', '2023-08-01 16:32:15', 'abed@abed.com', 'taha,abed,test'),
(30, 'running', '2023-08-01 16:33:22', 'abed@abed.com', 'abed@abed.com,taha@taha.com'),
(31, 'testing10', '2023-08-01 16:44:21', 'taha@taha.com', 'abed,taha'),
(32, 'testing11', '2023-08-01 16:45:28', 'abed@abed.com', 'abed,taha'),
(33, 'testing13', '2023-08-01 16:46:56', 'abed@abed.com', 'test,taha'),
(34, 'test1', '2023-08-01 16:51:03', 'taha@taha.com', 'taha,abed'),
(35, 'final_test', '2023-08-01 17:08:38', 'test@test.test', 'taha,test'),
(36, 'test100', '2023-08-01 17:11:52', 'taha@taha.com', 'taha,abed,test'),
(37, 'test101', '2023-08-01 17:13:46', 'taha@taha.com', 'taha,abed,test'),
(38, 'test1000', '2023-08-01 17:22:58', 'taha@taha.com', 'taha@taha.com,test@test.test'),
(39, 'test1', '2023-08-01 17:40:08', 'user1@test.test', 'user1@test.test,user2@test.test');

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `password_reset_tokens`
--

CREATE TABLE `password_reset_tokens` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expiration` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `password_reset_tokens`
--

INSERT INTO `password_reset_tokens` (`id`, `email`, `token`, `expiration`) VALUES
(2, 'r@gmail.com', '$2y$10$WrJQgK/WHQMu3ue.16zPa.8BNu63N7TU7ZcsT4oshYUYhRGiytcsa', '2023-07-23 18:51:30'),
(23, 'test@test.test', '$2y$10$FBtt7Bi/gINGBNZ1McOwiuCwmrTFKHV4.tmo2GxwWoKghx9q0b1We', '2023-07-23 20:17:03'),
(24, 'taha@taha.com', '$2y$10$4GmdyCn/X0W9pVkX3cb1Oe2rawKEMXZu7U/fcD5yHHDefuw6FDhte', '2023-07-23 20:19:17'),
(25, 'test@test.test', 'f1076918c10595a9a03787620988e7fd85209fa7fd6d991bf1eabd9ab8c3a352', '2023-07-23 20:19:27'),
(26, 'test@test.test', '291ba20aabad0e760310664de361800e9779c9c29c5987ad0c32e39a7acfc215', '2023-07-23 20:20:53'),
(28, 'abed@abed.com', '$2y$10$1PUUqTsa9oG3DKg9PI4ZKufrxqg96o3tT61Xor8cRmorEXfEX7lU2', '2023-07-23 20:25:35'),
(30, 'maryam@gmail.com', '$2y$10$552Mq2O1.9I6TH2hPEkzXOhEBjxfrF6fFQkvJDDfmEOzZJB9WH8vC', '2023-07-23 20:27:47'),
(31, 't1.s2.h3.21@gmail.com', '$2y$10$nGQonhOpn.5oW40NZGtRUu/n5HimkJirqayqp5AH0fxlIsy2aGAr2', '2023-07-23 20:30:57'),
(32, 'abed@abed.com', '$2y$10$lafMs90lB4vjEJvbXPTxbOscO9NgWdpnJNPDRG0dx9eAapAJ9Z2xW', '2023-07-23 20:33:32'),
(33, 'taha@taha.com', '$2y$10$WmI0.7xRd5g3ioF0VoT9veOGQgtP8NTEaIjqd5DGP4wd0J0iC3sBO', '2023-07-23 20:35:10'),
(34, 'abed@abed.com', '$2y$10$DuUEKIlTIRQCmH1Zm.y7juQ8tiWw9Tplatq2tn0KElxN4ajRhr6sq', '2023-07-23 20:35:59'),
(35, 'abed@abed.com', '$2y$10$dXNKamozT/eTUhZXjDP0juxOhn8Ujvn/yeldfihZn7Pb38AepvZ3q', '2023-07-23 20:42:57'),
(36, 'maryam@gmail.com', '$2y$10$kUEllJWd5/E4EFyq6Vzm/ugaZEE33YKrMVoLm58NU1EPfzrNzG9LC', '2023-07-23 20:43:06'),
(37, 't1.s2.h3.21@gmail.com', '$2y$10$4N30ezBtUgciaCRAFIaNFOP6ig6A5oVECNuAIT8/spiViZ5VmyhFO', '2023-07-23 20:43:20'),
(38, 'taha@taha.com', '$2y$10$Xos/251.xeJhyPrLJ8ISluAwha/hI4HzX2DwYZjU.4x7BLq7qSnrC', '2023-07-23 20:45:52'),
(39, 't1.s2.h3.21@gmail.com', '$2y$10$6EcOaGM5bQIzWAXtb9.avO0WIcnqLGSh7sArfmRN1651e5Eabwd2G', '2023-07-23 20:49:44'),
(40, 'abed@abed.com', '$2y$10$G8sV0ceSC1lXify0OzrLped.4tLHThpqbFq76KLrTMO3MhMMKkItm', '2023-07-23 20:49:55'),
(43, 'test@test.test', '372aa98409224963ffd52df4ae0bcf6a1507ddd70a280bcf51fdc52fa3daa13c', '2023-07-23 21:43:21'),
(44, 'taha@taha.com', 'ac43dc7526476fafa2747f0f29a59d5d99085b1a6ceff50c270723222cb1f230', '2023-07-24 18:33:43');

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `tasks`
--

CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `Responsible_User` varchar(255) NOT NULL,
  `list_id` int(11) DEFAULT NULL,
  `done` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `tasks`
--

INSERT INTO `tasks` (`task_id`, `title`, `date`, `Responsible_User`, `list_id`, `done`) VALUES
(2, 'hgjkh', '0000-00-00', 'abed', 17, 0),
(3, 'food', '0000-00-00', 'abed', 17, 0),
(4, 'deadlift', '0000-00-00', 'abed', 17, 0),
(8, 'swiming', '0000-00-00', 'azmi', 4, 0),
(9, 'swiming', '0000-00-00', 'azmi', 4, 0),
(10, 'running', '0000-00-00', 'abed@abed.com', 21, 0),
(11, 'deadlift', '0000-00-00', 'taha', 20, 0),
(12, 'running', '0000-00-00', 'taha@taha.com', 12, 0),
(13, 'running', '0000-00-00', 'taha@taha.com', 12, 0),
(15, 'swiming', '0000-00-00', 'abed', 18, 0),
(20, 'test2', '0000-00-00', 'taha', 7, 0),
(25, 'test3', '0000-00-00', 'taha', 7, 0),
(28, 'food', '0000-00-00', 'abed@abed.com', 15, 0),
(29, 'food', '0000-00-00', 'abed@abed.com', 15, 0),
(30, 'food', '0000-00-00', 'abed@abed.com', 15, 0),
(31, 'food', '0000-00-00', 'abed@abed.com', 15, 0),
(32, 'test4', '0000-00-00', 'taha', 7, 0),
(33, 'test5', '0000-00-00', 'taha', 7, 0),
(34, 'test6', '0000-00-00', 'taha', 7, 0),
(35, 'test7', '0000-00-00', 'taha', 7, 0),
(36, 'running', '0000-00-00', 'abed', 23, 0),
(43, 'running', '0000-00-00', 'taha', 1, 0),
(51, 'test5', '0000-00-00', 'abed@abed.com', 25, 0),
(52, 'deadlift', '0000-00-00', 'abed@abed.com', 25, 0),
(53, 'task1', '0000-00-00', 'user1@test.test', 39, 0);

-- --------------------------------------------------------

--
-- מבנה טבלה עבור טבלה `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `fname` varchar(10) NOT NULL,
  `lname` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- הוצאת מידע עבור טבלה `users`
--

INSERT INTO `users` (`ID`, `fname`, `lname`, `email`, `password`) VALUES
(211941655, 'maryam', 'khalil', 'maryam@gmail.com', '123'),
(211941659, 'test', 'test', 'test@test.test', '$2y$10$m.YR8JxuiL/Ubjd03X1sT.N3pgMWxQJHes2uCnE5pno.GI.OvYYtO'),
(211941661, 'taha', 'hammody', 'taha@taha.com', '$2y$10$kVtgvoJAd1mnW1V.whzZguocxmZ0FqoiAUIlb1GxIvPnFZFOBqxCG'),
(211941662, 'abed', 'sadt', 'abed@abed.com', '$2y$10$Rp.yPiY1sIl9A.6dypWsuOgTT4beuh3eMZtupR1m5ZlvLe435hFJu'),
(211941663, 'user1', 'user1', 'user1@test.test', '$2y$10$6Q/.21kUMUjE8PtlWH0Rxuqt1g9jEvyM94EPgmq6eMdZtJ.7ezMOq'),
(211941664, 'user2', 'user2', 'user2@test.test', '$2y$10$UtnTU4NQLjEAcLZEkjCm8O73iD8uLeLHT32hcPagHL85YHLtaz9me'),
(211941665, 'user3', 'user3', 'user3@test.test', '$2y$10$ZG8ruiSxVjIRlT/dKktYR.xoO95gMqDY30n8emcdnGJKdfydQvM5.');

--
-- Indexes for dumped tables
--

--
-- אינדקסים לטבלה `lists`
--
ALTER TABLE `lists`
  ADD PRIMARY KEY (`list_id`);

--
-- אינדקסים לטבלה `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD PRIMARY KEY (`id`);

--
-- אינדקסים לטבלה `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`task_id`);

--
-- אינדקסים לטבלה `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lists`
--
ALTER TABLE `lists`
  MODIFY `list_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211941666;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
