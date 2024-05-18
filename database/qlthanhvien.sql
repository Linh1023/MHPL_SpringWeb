-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2024 at 05:03 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlthanhvien`
--

-- --------------------------------------------------------

--
-- Table structure for table `thanhvien`
--

CREATE TABLE `thanhvien` (
  `MaTV` int(10) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `Khoa` varchar(100) DEFAULT NULL,
  `Nganh` varchar(100) DEFAULT NULL,
  `SDT` int(10) DEFAULT NULL,
  `PassWord` varchar(255) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thanhvien`
--

INSERT INTO `thanhvien` (`MaTV`, `HoTen`, `Khoa`, `Nganh`, `SDT`, `PassWord`, `Email`) VALUES
(1, 'Nguyễn Gia Mai', 'Toán UD', 'Toán', 911200100, '11', 'tranquanglinh70@gmail.com'),
(3121410, 'Lộc Hoàng', 'kế toán', 'CNTT', 963717300, '12345', 'quanglih1023m@gmail.com'),
(112141033, 'Lộc Hoàng', 'cntt', 'CNTT', 963717300, '1', 'quanglinh1023m@gmail.com'),
(1111111111, 'bhjbjh', 'kế toán', 'CNTT', 963717300, '12345', 'quanglih1023m@gmail.com'),
(1120010007, 'Nguyễn Gia Mai', 'Toán UDffff', 'Toán', 911200100, '1120010007', '1120010007@gmail.com'),
(1120020019, 'Chu Phúc Ngọc', 'SP KHTN', 'Lí', 911200200, '2147483647', '11200200019@gmail.com'),
(1120090014, 'Nguyễn Thị Mỹ Ngân', 'SP KHXH', 'Văn', 911200900, '2147483647', '11200900014@gmail.com'),
(1120090018, 'Trần Thị Anh Ngọc', 'SP KHXH', 'Văn', 911200900, '2147483647', '11200900018@gmail.com'),
(1120150137, 'Phan Thị Thanh Hằng', 'GDTH', 'GDTH', 911201501, '1120150137', '1120150137@gmail.com'),
(1120150184, 'Nguyễn Văn A', 'GDTH', 'GDTH', 963717300, '123456', 'nguyenvana@gmail.com'),
(1120380064, 'Nguyễn Ngọc Quỳnh Lực', 'Ngoại Ngữ', 'NNA', 911203800, '1120380064', '1120380064@gmail.com'),
(1120410311, 'Lê Việt Nga', 'CNTT', 'CNTT', 911204103, '2147483647', '11204110311@gmail.com'),
(1120480015, 'Trần Phạm Ngọc Ly', 'Toán UD', 'Toán', 911204800, '1120480015', '1120480015@gmail.com'),
(1120480216, 'Nguyễn Trần Thái Ngọc', 'Toán UD', 'Toán', 911204802, '2147483647', '11204800216@gmail.com'),
(1120480217, 'Trần Minh Phúc Ngọc', 'Toán UD', 'Toán', 911204802, '2147483647', '11204800217@gmail.com'),
(1121020009, 'Bùi Đình Thái My', 'SP KHTN', 'Lí', 911210200, '1121020009', '1121020009@gmail.com'),
(1121100003, 'Nguyễn Đắc Phương Linh', 'SP KHXH', 'Sử', 911211000, '1121100003', '1121100003@gmail.com'),
(1121100012, 'Trương Hoài Nga', 'SP KHXH', 'Sử', 911211000, '2147483647', '11211000012@gmail.com'),
(1121110001, 'Phạm Thị Lan Khôi', 'SP KHXH', 'Địa', 911211100, '1121110001', '1121110001@gmail.com'),
(1121130012, 'Lê Ngọc lan', 'Ngoại Ngữ', 'Anh', 911211300, '1121130012', '1121130012@gmail.com'),
(1121430037, 'Nguyễn Thị Phương Diễm', 'Luật', 'LHC', 911214300, '1121430037', '1121430037@gmail.com'),
(1121430051, 'Nguyễn Thị Tuyết Dung', 'Luật', 'LHC', 911214300, '1121430051', '1121430051@gmail.com'),
(1121430200, 'Lê Ngọc Linh', 'Luật', 'LHC', 911214302, '1121430200', '1121430200@gmail.com'),
(1121530087, 'Trần Thiếu Nam', 'TLH', 'QLGD', 1111111112, '123456', 'nguyenbghjg@gmail'),
(1122090010, 'Lê Xuân Nam', 'SP KHXH', 'Văn', 911220900, '2147483647', '11220900010@gmail.com'),
(1122090013, 'Nguyễn Mỹ Ngân', 'SP KHXH', 'Văn', 911220900, '2147483647', '11220900013@gmail.com'),
(1122090015, 'Nguyễn Thủy Triều Ngọc', 'SP KHXH', 'Văn', 911220900, '2147483647', '11220900015@gmail.com'),
(1122130055, 'Phan Văn Anh', 'Ngoại Ngữ', 'Anh', 911221300, '1122130055', '1122130055@gmail.com'),
(1122380173, 'Hoàng Bích Hà', 'Ngoại Ngữ', 'NNA', 911223801, '1122380173', '1122380173@gmail.com'),
(1122520013, 'Lê Nguyễn Phương Linh', 'ĐT-VT', 'CNKTDTTT', 911225200, '1122520013', '1122520013@gmail.com'),
(1122530016, 'Lê Thục Ly', 'QLGD', 'TLH', 911225300, '1122530016', '1122530016@gmail.com'),
(1122550008, 'Nguyễn Gia My', 'QTKD', 'QTKD', 911225500, '1122550008', '1122550008@gmail.com'),
(1123330257, 'Trần Hoàng Anh Thư', 'QTKD', 'QTKD', 963717300, '123456', 'nguyenthihihi@gmail.com'),
(1123380190, 'Trần Bùi Thảo My', 'Ngoại Ngữ', 'NNA', 911233801, '1123380190', '1123380190@gmail.com'),
(1123380205, 'Mai Thị Minh Huyền', 'Ngoại Ngữ', 'NNA', 911233802, '1123380205', '1123380205@gmail.com'),
(1123380365, 'Nguyễn Ngọc Thảo Vy', 'Ngoại Ngữ', 'NNA', 911233803, '1123380365', '1123380365@gmail.com'),
(1123430154, 'Đinh Hoàng Linh Chi', 'Luật', 'LHC', 911234301, '1123430154', '1123430154@gmail.com'),
(2147483647, 'Nguyễn Văn Nam', 'CNTT', 'HTTT', 123456789, '121212', 'aaahhhh@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `thietbi`
--

CREATE TABLE `thietbi` (
  `MaTB` int(10) NOT NULL,
  `TenTB` varchar(100) NOT NULL,
  `MoTaTB` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thietbi`
--

INSERT INTO `thietbi` (`MaTB`, `TenTB`, `MoTaTB`) VALUES
(333, '333', '333'),
(120191, 'Micro không dây 01', 'JBL VM 2001'),
(120192, 'Micro có dây 01', 'MCCD 1001'),
(120203, 'Micro không dây 02', 'BCE 9001'),
(120214, 'Micro không dây 03', 'M3001'),
(120235, 'Micro không dây 03', 'BCE UGX12'),
(120241, 'Micro không dây 01', 'MCKD 241'),
(120242, 'Micro có dây 01', 'MCCD 241'),
(120243, 'Micro có dây 02', 'MCCD 242'),
(120244, 'Micro không dây 02', 'MCKD 242'),
(120251, 'Micro không dây 01', 'MCKD 241'),
(220191, 'Máy chiếu Panasonic', 'PNSN 031'),
(320201, 'Máy ảnh Fuji', 'FJ 20201'),
(420201, 'Cassette Sony', 'CN 20201'),
(420212, 'Cassette Sony', 'CN 20211'),
(420213, 'Cassette TQ', 'CSTQ 20211'),
(420224, 'Cassette Sony', 'CS 20221'),
(420236, 'Cassette Sony', 'CS 2023'),
(520221, 'Tivi LG', 'TVLG021'),
(520222, 'Tivi Samsung', 'TVSS20221'),
(620231, 'Quạt đứng', 'QD 20231'),
(620232, 'Quạt đứng', 'QD 20232'),
(620233, 'Quạt đứng', 'QD 20233'),
(620234, 'Quạt đứng', 'QD 20234');

-- --------------------------------------------------------

--
-- Table structure for table `thongtinsd`
--

CREATE TABLE `thongtinsd` (
  `MaTT` int(10) NOT NULL,
  `MaTV` int(10) NOT NULL,
  `MaTB` int(10) DEFAULT NULL,
  `TGVao` datetime DEFAULT NULL,
  `TGMuon` datetime DEFAULT NULL,
  `TGTra` datetime DEFAULT NULL,
  `TGDatCho` datetime DEFAULT NULL,
  `xuly` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thongtinsd`
--

INSERT INTO `thongtinsd` (`MaTT`, `MaTV`, `MaTB`, `TGVao`, `TGMuon`, `TGTra`, `TGDatCho`, `xuly`) VALUES
(108, 1121530087, NULL, '2024-05-06 14:06:55', NULL, NULL, NULL, NULL),
(109, 1121530087, NULL, '2024-05-06 14:07:11', NULL, NULL, NULL, NULL),
(110, 1123330257, NULL, '2024-05-06 14:07:24', NULL, NULL, NULL, NULL),
(111, 1120150184, 120192, NULL, '2024-05-06 14:08:49', '2024-05-07 23:56:06', '2024-05-06 14:08:00', NULL),
(114, 1123330257, 120192, NULL, NULL, '2024-05-12 13:52:00', '2024-05-07 14:15:00', NULL),
(115, 1120150184, 120242, NULL, '2024-05-07 23:55:21', NULL, NULL, NULL),
(116, 1120480217, 120235, NULL, '2024-05-07 23:55:30', NULL, NULL, NULL),
(117, 1121530087, 420212, NULL, '2024-05-09 02:31:44', NULL, NULL, NULL),
(118, 1, 620234, NULL, NULL, '2024-05-12 13:52:00', '2024-05-03 13:08:00', NULL),
(120, 1, NULL, '2024-05-12 14:40:08', NULL, NULL, NULL, NULL),
(121, 1120150137, NULL, '2024-05-12 15:32:41', NULL, NULL, NULL, NULL),
(122, 1, 333, NULL, NULL, '2024-05-13 13:59:33', '2024-05-13 08:09:00', NULL),
(130, 112141033, 120191, NULL, NULL, '2024-05-13 13:59:33', '2024-05-13 01:58:00', NULL),
(131, 112141033, 120191, NULL, NULL, '2024-05-13 14:03:06', '2024-05-13 15:00:00', NULL),
(132, 112141033, 120191, NULL, NULL, NULL, '2024-05-14 14:00:00', NULL),
(133, 112141033, 120192, NULL, NULL, NULL, '2024-05-13 15:00:00', NULL),
(134, 1, 120203, NULL, '2024-05-13 14:02:09', NULL, NULL, NULL),
(135, 1, 120191, NULL, '2024-05-13 14:04:21', '2024-05-13 14:04:27', NULL, NULL),
(136, 112141033, NULL, '2024-05-13 14:04:48', NULL, NULL, NULL, NULL),
(137, 112141033, NULL, '2024-05-13 14:08:28', NULL, NULL, NULL, NULL),
(138, 112141033, NULL, '2024-05-13 14:08:38', NULL, NULL, NULL, NULL),
(139, 112141033, 120191, NULL, NULL, NULL, '2024-05-13 14:10:00', NULL),
(140, 1120010007, NULL, '2024-05-18 10:01:01', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `xuly`
--

CREATE TABLE `xuly` (
  `MaXL` int(10) NOT NULL,
  `MaTV` int(10) NOT NULL,
  `HinhThucXL` varchar(250) DEFAULT NULL,
  `SoTien` int(100) DEFAULT NULL,
  `NgayXL` datetime DEFAULT NULL,
  `TrangThaiXL` int(2) DEFAULT NULL,
  `trang_thaixl` int(11) DEFAULT NULL,
  `hinh_thucxl` varchar(255) DEFAULT NULL,
  `so_tien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `xuly`
--

INSERT INTO `xuly` (`MaXL`, `MaTV`, `HinhThucXL`, `SoTien`, `NgayXL`, `TrangThaiXL`, `trang_thaixl`, `hinh_thucxl`, `so_tien`) VALUES
(1, 1121530087, 'lm j làm', 22222, '2023-09-12 08:00:00', 0, NULL, NULL, NULL),
(2, 2147483647, 'Khóa thẻ 2 tháng', 5555, '2023-09-12 08:00:00', 1, NULL, NULL, NULL),
(3, 1123330257, 'Khóa', 300000, '2023-09-12 08:00:00', 1, NULL, NULL, NULL),
(4, 1121530087, 'lm j làm', 22222, '2023-09-12 08:00:00', 0, NULL, NULL, NULL),
(8, 2147483647, 'nộp phạt', 22222, '2024-04-20 07:02:59', 1, NULL, NULL, NULL),
(12, 112141033, 'sđ', 200, '2024-05-13 00:00:00', 0, NULL, NULL, NULL),
(87, 2147483647, NULL, 5555, '2024-04-20 17:14:10', 1, NULL, NULL, NULL),
(99, 2147483647, 'Khóa thẻ 2 tháng', 5555, '2023-09-12 08:00:00', 1, NULL, NULL, NULL),
(100, 1123330257, NULL, 300000, '2024-04-20 17:24:28', 1, NULL, NULL, NULL),
(1222, 112141033, 'khóa thẻ 1 năm', 2000, '2024-05-13 00:00:00', 1, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `thanhvien`
--
ALTER TABLE `thanhvien`
  ADD PRIMARY KEY (`MaTV`);

--
-- Indexes for table `thietbi`
--
ALTER TABLE `thietbi`
  ADD PRIMARY KEY (`MaTB`);

--
-- Indexes for table `thongtinsd`
--
ALTER TABLE `thongtinsd`
  ADD PRIMARY KEY (`MaTT`),
  ADD KEY `MaTV` (`MaTV`,`MaTB`),
  ADD KEY `MaTB` (`MaTB`),
  ADD KEY `FKtmc3yw8o5efex347qf0bwghox` (`xuly`);

--
-- Indexes for table `xuly`
--
ALTER TABLE `xuly`
  ADD PRIMARY KEY (`MaXL`),
  ADD KEY `MaTV` (`MaTV`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `thongtinsd`
--
ALTER TABLE `thongtinsd`
  MODIFY `MaTT` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `thongtinsd`
--
ALTER TABLE `thongtinsd`
  ADD CONSTRAINT `FKtmc3yw8o5efex347qf0bwghox` FOREIGN KEY (`xuly`) REFERENCES `xuly` (`MaXL`),
  ADD CONSTRAINT `thongtinsd_ibfk_1` FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`),
  ADD CONSTRAINT `thongtinsd_ibfk_2` FOREIGN KEY (`MaTB`) REFERENCES `thietbi` (`MaTB`);

--
-- Constraints for table `xuly`
--
ALTER TABLE `xuly`
  ADD CONSTRAINT `xuly_ibfk_1` FOREIGN KEY (`MaTV`) REFERENCES `thanhvien` (`MaTV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
