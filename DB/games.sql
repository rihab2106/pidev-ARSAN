-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2022 at 08:12 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `games`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `ID_cart` int(11) NOT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `SUM` float(8,2) DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `ID_CATEGORY` int(11) NOT NULL,
  `CATEGORY` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID_CATEGORY`, `CATEGORY`) VALUES
(1, 'Action');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `ID_COMMENT` int(11) NOT NULL,
  `ID_NEWS` int(11) DEFAULT NULL,
  `COMMENT` varchar(512) DEFAULT NULL,
  `LIKES` int(11) DEFAULT NULL,
  `DISLIKES` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `competitions`
--

CREATE TABLE `competitions` (
  `ID_COMPETION` int(11) NOT NULL,
  `ID_GAME` int(11) DEFAULT NULL,
  `BEGIN` date DEFAULT NULL,
  `GAME_NAME` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `ID_GAME` int(11) NOT NULL,
  `ID_CATEGORY` int(11) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `RATE` decimal(8,4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`ID_GAME`, `ID_CATEGORY`, `NAME`, `DESCRIPTION`, `RATE`) VALUES
(4, 1, 'acc', 'Descripion', '4.9000');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `ID_GROUP` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `IMG` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `ID_USER` int(11) NOT NULL,
  `ID_TEAM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `ID_NEWS` int(11) NOT NULL,
  `HEADLINE` varchar(100) DEFAULT NULL,
  `CONTENT` text DEFAULT NULL,
  `IMG` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ID_PRODUCT` int(11) NOT NULL,
  `ID_CATEGORY` int(11) DEFAULT NULL,
  `ID_cart` int(11) DEFAULT NULL,
  `PROD_NAME` varchar(100) DEFAULT NULL,
  `PRICE` float(8,2) DEFAULT NULL,
  `DISCOUNT` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `ID_TEAM` int(11) NOT NULL,
  `ID_COMPETION` int(11) DEFAULT NULL,
  `TEAM_NAME` varchar(50) DEFAULT NULL,
  `CREATOR` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `trophies`
--

CREATE TABLE `trophies` (
  `ID_TROPHY` int(11) NOT NULL,
  `ID_GAME` int(11) DEFAULT NULL,
  `TITLE` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `PLATFORM` varchar(20) DEFAULT NULL,
  `DIFFICULITY` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trophies`
--

INSERT INTO `trophies` (`ID_TROPHY`, `ID_GAME`, `TITLE`, `DESCRIPTION`, `PLATFORM`, `DIFFICULITY`) VALUES
(2, 4, 'sff', 'sdfsdfdsf  sf', 'dsfdsf', 'hard');

-- --------------------------------------------------------

--
-- Table structure for table `tutorials`
--

CREATE TABLE `tutorials` (
  `ID_TUTORIAL` int(11) NOT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `ID_TROPHY` int(11) DEFAULT NULL,
  `PATH` varchar(200) DEFAULT NULL,
  `CONTENT` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID_USER` int(11) NOT NULL,
  `ID_GROUP` int(11) DEFAULT NULL,
  `FULL_NAME` varchar(100) DEFAULT NULL,
  `IMG` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  `ISACTIVE` tinyint(1) DEFAULT NULL,
  `PRIVILEGE_` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `userteams`
--

CREATE TABLE `userteams` (
  `ID_TEAM` int(11) NOT NULL,
  `ID_USER` int(11) NOT NULL,
  `ID_GAME` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`ID_cart`),
  ADD KEY `FK_CART_REFERENCE_USERS` (`ID_USER`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ID_CATEGORY`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`ID_COMMENT`),
  ADD KEY `FK_COMMENTS_REFERENCE_NEWS` (`ID_NEWS`);

--
-- Indexes for table `competitions`
--
ALTER TABLE `competitions`
  ADD PRIMARY KEY (`ID_COMPETION`),
  ADD KEY `FK_COMPETIT_REFERENCE_GAMES` (`ID_GAME`);

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`ID_GAME`),
  ADD KEY `FK_GAMES_REFERENCE_CATEGORY` (`ID_CATEGORY`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`ID_GROUP`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`ID_USER`,`ID_TEAM`),
  ADD KEY `FK_MEMBERS_REFERENCE_TEAMS` (`ID_TEAM`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`ID_NEWS`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID_PRODUCT`),
  ADD KEY `FK_PRODUCT_REFERENCE_CART` (`ID_cart`),
  ADD KEY `FK_PRODUCT_REFERENCE_CATEGORY` (`ID_CATEGORY`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`ID_TEAM`),
  ADD KEY `FK_TEAMS_REFERENCE_COMPETIT` (`ID_COMPETION`);

--
-- Indexes for table `trophies`
--
ALTER TABLE `trophies`
  ADD PRIMARY KEY (`ID_TROPHY`),
  ADD KEY `FK_TROPHIES_REFERENCE_GAMES` (`ID_GAME`);

--
-- Indexes for table `tutorials`
--
ALTER TABLE `tutorials`
  ADD PRIMARY KEY (`ID_TUTORIAL`),
  ADD KEY `FK_TUTORIAL_REFERENCE_TROPHIES` (`ID_TROPHY`),
  ADD KEY `FK_TUTORIAL_REFERENCE_USERS` (`ID_USER`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID_USER`),
  ADD KEY `FK_USERS_REFERENCE_GROUPS` (`ID_GROUP`);

--
-- Indexes for table `userteams`
--
ALTER TABLE `userteams`
  ADD PRIMARY KEY (`ID_TEAM`,`ID_USER`,`ID_GAME`),
  ADD KEY `FK_USERTEAM_REFERENCE_USERS` (`ID_USER`),
  ADD KEY `FK_USERTEAM_REFERENCE_GAMES` (`ID_GAME`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `ID_GAME` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `trophies`
--
ALTER TABLE `trophies`
  MODIFY `ID_TROPHY` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tutorials`
--
ALTER TABLE `tutorials`
  MODIFY `ID_TUTORIAL` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK_CART_REFERENCE_USERS` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`ID_USER`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK_COMMENTS_REFERENCE_NEWS` FOREIGN KEY (`ID_NEWS`) REFERENCES `news` (`ID_NEWS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `competitions`
--
ALTER TABLE `competitions`
  ADD CONSTRAINT `FK_COMPETIT_REFERENCE_GAMES` FOREIGN KEY (`ID_GAME`) REFERENCES `games` (`ID_GAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `FK_GAMES_REFERENCE_CATEGORY` FOREIGN KEY (`ID_CATEGORY`) REFERENCES `category` (`ID_CATEGORY`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `FK_MEMBERS_REFERENCE_TEAMS` FOREIGN KEY (`ID_TEAM`) REFERENCES `teams` (`ID_TEAM`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_MEMBERS_REFERENCE_USERS` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`ID_USER`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_PRODUCT_REFERENCE_CART` FOREIGN KEY (`ID_cart`) REFERENCES `cart` (`ID_cart`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PRODUCT_REFERENCE_CATEGORY` FOREIGN KEY (`ID_CATEGORY`) REFERENCES `category` (`ID_CATEGORY`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `FK_TEAMS_REFERENCE_COMPETIT` FOREIGN KEY (`ID_COMPETION`) REFERENCES `competitions` (`ID_COMPETION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trophies`
--
ALTER TABLE `trophies`
  ADD CONSTRAINT `FK_TROPHIES_REFERENCE_GAMES` FOREIGN KEY (`ID_GAME`) REFERENCES `games` (`ID_GAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tutorials`
--
ALTER TABLE `tutorials`
  ADD CONSTRAINT `FK_TUTORIAL_REFERENCE_TROPHIES` FOREIGN KEY (`ID_TROPHY`) REFERENCES `trophies` (`ID_TROPHY`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_TUTORIAL_REFERENCE_USERS` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`ID_USER`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_USERS_REFERENCE_GROUPS` FOREIGN KEY (`ID_GROUP`) REFERENCES `groups` (`ID_GROUP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `userteams`
--
ALTER TABLE `userteams`
  ADD CONSTRAINT `FK_USERTEAM_REFERENCE_GAMES` FOREIGN KEY (`ID_GAME`) REFERENCES `games` (`ID_GAME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_USERTEAM_REFERENCE_TEAMS` FOREIGN KEY (`ID_TEAM`) REFERENCES `teams` (`ID_TEAM`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_USERTEAM_REFERENCE_USERS` FOREIGN KEY (`ID_USER`) REFERENCES `users` (`ID_USER`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
