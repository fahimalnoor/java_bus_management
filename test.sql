-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2018 at 08:11 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `borrowinfo`
--

CREATE TABLE `borrowinfo` (
  `BorrowId` varchar(10) NOT NULL,
  `BusId` varchar(10) NOT NULL,
  `UserId` varchar(10) NOT NULL,
  `BorrowDate` varchar(10) NOT NULL,
  `ReturnDate` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrowinfo`
--

INSERT INTO `borrowinfo` (`BorrowId`, `BusId`, `UserId`, `BorrowDate`, `ReturnDate`) VALUES
('borrow01', 'bus01', 'driver01', '', '12/12/2018'),
('borrow02', 'bus02', 'driver02', '1/1/2018', '2/1/2018');

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `BusId` varchar(10) NOT NULL,
  `Model` varchar(10) NOT NULL,
  `Price` int(20) NOT NULL,
  `Quantity` int(20) NOT NULL,
  `Route` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`BusId`, `Model`, `Price`, `Quantity`, `Route`) VALUES
('bus01', 'x01utuyt', 2000, 3, 'Malibagh to Kuril'),
('bus02', 'y01', 3000, 5, 'Kuril to uttara');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `UserId` varchar(10) NOT NULL,
  `Drivername` varchar(15) NOT NULL,
  `Phonenumber` varchar(11) NOT NULL,
  `Address` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`UserId`, `Drivername`, `Phonenumber`, `Address`) VALUES
('driver01', 'abcdd', '1801010101', 'Badda'),
('driver02', 'cba', '1601010101', 'Malibagh'),
('driver03', 'abcd', '1768456758', 'uttara');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `UserId` varchar(10) NOT NULL,
  `Employeename` varchar(30) NOT NULL,
  `Phonenumber` varchar(20) NOT NULL,
  `Role` varchar(10) NOT NULL,
  `Salary` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`UserId`, `Employeename`, `Phonenumber`, `Role`, `Salary`) VALUES
('employee01', 'xxxy', '01910101010', 'Employee', 20000),
('employee02', 'yyyz', '01900110011', 'Employee', 10000),
('manager01', 'xyzzzzz', '01910101011', 'Manager', 20000),
('manager02', 'zyx', '01700110010', 'Manager', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `UserId` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Status` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`UserId`, `Password`, `Status`) VALUES
('driver01', 'driver01', 2),
('driver02', 'driver02', 2),
('driver03', '620', 2),
('employee01', 'employee01', 0),
('employee02', 'employee02', 0),
('manager01', 'manager01', 1),
('manager02', 'manager02', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `borrowinfo`
--
ALTER TABLE `borrowinfo`
  ADD PRIMARY KEY (`BusId`);

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`BusId`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`UserId`),
  ADD UNIQUE KEY `UserID` (`UserId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
