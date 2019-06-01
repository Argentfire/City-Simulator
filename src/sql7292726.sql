-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql7.freemysqlhosting.net
-- Generation Time: May 27, 2019 at 09:13 AM
-- Server version: 5.5.58-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql7292726`
--

-- --------------------------------------------------------

--
-- Table structure for table `businesses`
--

CREATE TABLE `businesses` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `level` int(12) DEFAULT NULL,
  `workers` int(12) DEFAULT NULL,
  `type` int(12) DEFAULT NULL,
  `vault` double DEFAULT NULL,
  `debt` double DEFAULT NULL,
  `stocks` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `level` int(32) DEFAULT NULL,
  `workers` int(32) DEFAULT NULL,
  `type` int(32) DEFAULT NULL,
  `vault` double DEFAULT NULL,
  `debt` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `name`, `level`, `workers`, `type`, `vault`, `debt`) VALUES
(1, 'Asenovgrad', 1, 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `economy`
--

CREATE TABLE `economy` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `type` int(12) DEFAULT NULL,
  `value` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `economy`
--

INSERT INTO `economy` (`id`, `name`, `type`, `value`) VALUES
(1, 'Vault', 1, 0),
(2, 'Debt', 1, 0),
(3, 'WaterTax', 2, 0),
(4, 'MinimalWage', 3, 560);

-- --------------------------------------------------------

--
-- Table structure for table `humans`
--

CREATE TABLE `humans` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(64) NOT NULL,
  `lastName` varchar(64) NOT NULL,
  `age` int(12) NOT NULL,
  `ethnicity` varchar(64) NOT NULL,
  `religion` varchar(64) NOT NULL,
  `height` float NOT NULL,
  `weight` float NOT NULL,
  `elementary` tinyint(1) NOT NULL,
  `highschool` tinyint(1) NOT NULL,
  `master` tinyint(1) NOT NULL,
  `employed` tinyint(1) NOT NULL,
  `salary` double NOT NULL,
  `alive` tinyint(1) NOT NULL,
  `healthy` tinyint(1) NOT NULL,
  `gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `population`
--

CREATE TABLE `population` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `alive` int(12) DEFAULT NULL,
  `deceased` int(12) DEFAULT NULL,
  `Healthy` int(12) DEFAULT NULL,
  `Sick` int(12) DEFAULT NULL,
  `Total` int(12) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `population`
--

INSERT INTO `population` (`id`, `alive`, `deceased`, `Healthy`, `Sick`, `Total`, `name`) VALUES
(0, 0, 0, 0, 0, 0, 'Asenovgrad');
