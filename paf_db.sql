-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 12:07 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('vinuri', '12345678'),
('Thilini', '123456'),
('Thilini', '123456'),
('Udara Vinuri', '123456999'),
('ddd', 'ddddd'),
('vunuri', 'janitha'),
('udara', '123456'),
('udara', '12345'),
('Laskh', 'lasl'),
('romini', 'r123456'),
('owini', 'o12345'),
('owini', 'ow123456');

-- --------------------------------------------------------

--
-- Table structure for table `registereddoctor`
--

CREATE TABLE `registereddoctor` (
  `doctorid` int(4) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(40) NOT NULL,
  `phonenumber` varchar(10) NOT NULL,
  `specalization` varchar(50) NOT NULL,
  `charges` decimal(8,2) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registereddoctor`
--

INSERT INTO `registereddoctor` (`doctorid`, `firstname`, `lastname`, `email`, `password`, `phonenumber`, `specalization`, `charges`, `type`) VALUES
(15, 'romini', 'Fernando', 'wijekoonvinuri97@gmail.com', 'r123456', '0714567876', 'eye', '900.00', 'internal');

--
-- Triggers `registereddoctor`
--
DELIMITER $$
CREATE TRIGGER `insertlogin` AFTER INSERT ON `registereddoctor` FOR EACH ROW INSERT INTO login VALUES(NEW.firstname,NEW.password)
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `registereddoctor`
--
ALTER TABLE `registereddoctor`
  ADD PRIMARY KEY (`doctorid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `registereddoctor`
--
ALTER TABLE `registereddoctor`
  MODIFY `doctorid` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
