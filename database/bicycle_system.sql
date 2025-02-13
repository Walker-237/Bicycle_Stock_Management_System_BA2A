-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2025 at 05:21 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bicycle_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `bicycle`
--

CREATE TABLE `bicycle` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` varchar(30) DEFAULT NULL,
  `size` varchar(30) DEFAULT NULL,
  `Type` varchar(30) NOT NULL,
  `NoAvailable` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bicycle`
--

INSERT INTO `bicycle` (`id`, `name`, `description`, `price`, `size`, `Type`, `NoAvailable`) VALUES
(2, 'Trek Marlin 5', 'A versati mountain bike ideal for both trails and commuting', '150000', 'XS, SM, MD, LG, XL', 'Mountain Bicycle', 14),
(3, 'Cannondale Quick 4', 'A fitness bicycle that combines speed and confort for urban riding', '150000', 'S, M, L, XL', 'Hybrid Bicycle', 16),
(4, 'Specialized Allez', 'A lightweight road bike designed for speed and performance in paved roads', '200000', 'S, M, MD, LG', 'Road Bike', 11),
(5, 'Giant Escape 3', 'A versatile bike perfect for community and recreational rides.', '480000', 'XS, S, M, L, XL', 'Hybrid Bicycle', 20),
(6, 'Bianchi Pista', 'A sleek track bike designed for speed and agiity on the velodrome', '96000', 'XS, L, X, MD', 'Track Bicycle', 19),
(7, 'Scott Aspect 950', 'A robust mountain bike designed for trail and off-road riding.', '70000', 'S, M, L, XL', 'Mountain Bike', 0);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `User_ID` int(11) NOT NULL,
  `Product_id` int(11) DEFAULT NULL,
  `Product_name` varchar(30) DEFAULT NULL,
  `Product_Price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `User_ID`, `Product_id`, `Product_name`, `Product_Price`) VALUES
(23, 10, 4, 'Specialized Allez', 200000);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `Id` int(11) NOT NULL,
  `User_ID` int(11) NOT NULL,
  `totalAmount` int(11) DEFAULT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`Id`, `User_ID`, `totalAmount`, `Date`) VALUES
(13, 12, 780000, '2025-02-11'),
(14, 12, 680000, '2025-02-11'),
(15, 12, 200000, '2025-02-11'),
(16, 12, 150000, '2025-02-11'),
(17, 10, 200000, '2025-02-13');

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

CREATE TABLE `statistics` (
  `id` int(11) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `month` varchar(30) DEFAULT NULL,
  `NoOfSales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `statistics`
--

INSERT INTO `statistics` (`id`, `year`, `month`, `NoOfSales`) VALUES
(1, 2022, 'January', 34),
(2, 2022, 'February', 45),
(3, 2022, 'March', 54),
(4, 2022, 'April', 22),
(5, 2022, 'May', 13),
(6, 2022, 'June', 33),
(7, 2022, 'July', 41),
(8, 2022, 'August', 27),
(9, 2022, 'September', 35),
(10, 2022, 'October', 49),
(11, 2022, 'November', 32),
(12, 2022, 'December', 75),
(13, 2023, 'January', 45),
(14, 2023, 'February', 10),
(15, 2023, 'March', 24),
(16, 2023, 'April', 34),
(17, 2023, 'May', 6),
(18, 2023, 'June', 27),
(19, 2023, 'July', 45),
(20, 2023, 'August', 39),
(21, 2023, 'September', 42),
(22, 2023, 'October', 37),
(23, 2023, 'November', 24),
(24, 2023, 'December', 69),
(25, 2024, 'January', 19),
(26, 2024, 'February', 26),
(27, 2024, 'March', 32),
(28, 2024, 'April', 37),
(29, 2024, 'May', 24),
(30, 2024, 'June', 12),
(31, 2024, 'July', 49),
(32, 2024, 'August', 37),
(33, 2024, 'September', 24),
(34, 2024, 'October', 26),
(35, 2024, 'November', 39),
(36, 2024, 'December', 73),
(37, 2025, 'January', 31);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `Status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `Status`) VALUES
(6, 'Admin', 'Admin@gmail.com', 'Admin', 'Admin'),
(7, 'Faith', 'faith@gmail.com', 'faith237', 'STAFF'),
(9, 'Gilles Armand', 'armand@gmail.com', '', 'STAFF'),
(10, 'Walker', 'walker@gmail.com', 'walker1237', 'STAFF'),
(11, 'Dongmo', 'dongmo@gmail.com', 'dongmo237', 'customer'),
(12, 'Melissa', 'melimtx@gmail.com', 'tx', 'customer'),
(13, 'walker', 'walker@gmail.com', 'walker237', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bicycle`
--
ALTER TABLE `bicycle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `statistics`
--
ALTER TABLE `statistics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bicycle`
--
ALTER TABLE `bicycle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `statistics`
--
ALTER TABLE `statistics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
