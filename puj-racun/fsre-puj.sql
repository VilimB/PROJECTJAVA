-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2019 at 12:19 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fsre-puj`
--

-- --------------------------------------------------------

--
-- Table structure for table `artikli`
--

CREATE TABLE `artikli` (
  `artikal_id` int(11) NOT NULL,
  `naziv` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `cijena` int(255) NOT NULL,
  `slika` varchar(180) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `artikli`
--

INSERT INTO `artikli` (`artikal_id`, `naziv`, `cijena`, `slika`) VALUES
(1, 'Kava', 1, 'https://cdn3.iconfinder.com/data/icons/food-and-drinks-icons/512/Coffee-512.png'),
(461, 'Coca-Cola', 3, 'https://www.coca-cola.ba/content/dam/GO/coca-cola/bosnia/one-brand/Updates/16287/logo_coca1.png'),
(462, 'Jana', 2, 'http://www.ponoshrvatske.hr/www/cache/ed/53/ed53c268206931319d706b95c2e3411e.jpg'),
(463, 'Ozujsko', 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3sFkVD40zdhQWT0g8GX9HMX1l5E9UjI3edaF0j5-xFZOCbM2K'),
(464, 'Schweppes', 4, 'http://palmerags.com/wp-content/uploads/2018/02/lg_schweppes.png'),
(465, 'RedBull', 3, 'https://pbs.twimg.com/profile_images/902866553292472321/qkG9XSHH_400x400.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `id` int(11) NOT NULL,
  `korisnicko_ime` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lozinka` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `uloga` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id`, `korisnicko_ime`, `lozinka`, `uloga`) VALUES
(10, 'admin', 'admin', 'admin'),
(31, 'marko', '1234', 'korisnik'),
(32, 'jurica', '1234', 'korisnik'),
(33, 'ivan', 'ivan123', 'admin'),
(34, 'korisnik', 'korisnik', 'korisnik');

-- --------------------------------------------------------

--
-- Table structure for table `racun`
--

CREATE TABLE `racun` (
  `racun_id` int(11) NOT NULL,
  `id_korisnika` int(11) DEFAULT NULL,
  `ime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `cijena` int(11) NOT NULL,
  `kol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `racun_stavka`
--

CREATE TABLE `racun_stavka` (
  `racun_stavka_id` int(11) NOT NULL,
  `artikal_id` int(11) NOT NULL,
  `racun_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artikli`
--
ALTER TABLE `artikli`
  ADD PRIMARY KEY (`artikal_id`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `racun`
--
ALTER TABLE `racun`
  ADD PRIMARY KEY (`racun_id`),
  ADD KEY `id_korisnika` (`id_korisnika`);

--
-- Indexes for table `racun_stavka`
--
ALTER TABLE `racun_stavka`
  ADD PRIMARY KEY (`racun_stavka_id`),
  ADD KEY `racun_a_1` (`artikal_id`),
  ADD KEY `racun_id` (`racun_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `artikli`
--
ALTER TABLE `artikli`
  MODIFY `artikal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=467;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `racun`
--
ALTER TABLE `racun`
  MODIFY `racun_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- AUTO_INCREMENT for table `racun_stavka`
--
ALTER TABLE `racun_stavka`
  MODIFY `racun_stavka_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `racun`
--
ALTER TABLE `racun`
  ADD CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnik` (`id`);

--
-- Constraints for table `racun_stavka`
--
ALTER TABLE `racun_stavka`
  ADD CONSTRAINT `racun_a_1` FOREIGN KEY (`artikal_id`) REFERENCES `artikli` (`artikal_id`),
  ADD CONSTRAINT `racun_stavka_ibfk_1` FOREIGN KEY (`racun_id`) REFERENCES `racun` (`racun_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
