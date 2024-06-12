-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 13 Jun 2024 pada 01.34
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warungkostungu`
--

-- --------------------------------------------------------
CREATE database warungkostungu;
use warungkostungu;
--
-- Struktur dari tabel `daftar_akun`
--

CREATE TABLE `daftar_akun` (
  `id` varchar(20) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `daftar_akun`
--

INSERT INTO `daftar_akun` (`id`, `username`, `password`, `status`) VALUES
('adm1', '1', '1', 'admin'),
('adm2', '2', '2', 'admin'),
('kamarungu1', 'kamar1', 'kmr1', 'kamar'),
('kamarungu10', 'kamar10', 'kmr10', 'kamar'),
('kamarungu11', 'kamar11', 'kmr11', 'kamar'),
('kamarungu12', 'kamar12', 'kmr12', 'kamar'),
('kamarungu13', 'kamar13', 'kmr13', 'kamar'),
('kamarungu14', 'kamar14', 'kmr14', 'kamar'),
('kamarungu15', 'kamar15', 'kmr15', 'kamar'),
('kamarungu2', 'kamar2', 'kmr2', 'kamar'),
('kamarungu3', 'kamar3', 'kmr3', 'kamar'),
('kamarungu4', 'kamar4', 'kmr4', 'kamar'),
('kamarungu5', 'kamar5', 'kmr5', 'kamar'),
('kamarungu6', 'kamar6', 'kmr6', 'kamar'),
('kamarungu7', 'kamar7', 'kmr7', 'kamar'),
('kamarungu8', 'kamar8', 'kmr8', 'kamar'),
('kamarungu9', 'kamar9', 'kmr9', 'kamar');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `harga` int(11) NOT NULL DEFAULT 0,
  `status` varchar(30) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  `gambar` mediumblob DEFAULT NULL,
  `gambar_dis` mediumblob DEFAULT NULL,
  `jenis` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(10) UNSIGNED NOT NULL,
  `admin` varchar(30) NOT NULL,
  `pembeli` varchar(20) NOT NULL,
  `pesanan` text NOT NULL,
  `deskripsi` text DEFAULT NULL,
  `harga` text NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `tanggalpesan` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `daftar_akun`
--
ALTER TABLE `daftar_akun`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
