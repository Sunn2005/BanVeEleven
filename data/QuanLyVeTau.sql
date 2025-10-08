-- Tạo database DTHP
CREATE DATABASE GaEleven
COLLATE Vietnamese_CI_AS;
GO

-- Sử dụng database DTHP
USE GaEleven;
GO

-- Tạo bảng Ga
CREATE TABLE Ga (
    maGa VARCHAR(50) PRIMARY KEY,
    tenGa NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(200) NOT NULL,
    chiSoKm INT NOT NULL,
    trangThai BIT NOT NULL
);

-- Tạo bảng ChuyenTau
CREATE TABLE ChuyenTau (
    maTau VARCHAR(50) PRIMARY KEY,
	gaDi VARCHAR(50) NOT NULL,
    gaDen VARCHAR(50) NOT NULL,
    ngayDi DATE NOT NULL,
    gioDi TIME NOT NULL,
	ngayDen DATE NOT NULL,
	gioDen TIME NOT NULL,
    FOREIGN KEY (gaDen) REFERENCES Ga(maGa)
);

-- Bảng trung gian giữa Ga và ChuyenTau (n-n)
CREATE TABLE ChuyenTau_Ga (
    maTau VARCHAR(50),
    maGa VARCHAR(50),
	ngayDi DATE NOT NULL,
    gioDi TIME NOT NULL,
	ngayDen DATE NOT NULL,
	gioDen TIME NOT NULL,
    PRIMARY KEY (maTau, maGa),
    FOREIGN KEY (maTau) REFERENCES ChuyenTau(maTau),
    FOREIGN KEY (maGa) REFERENCES Ga(maGa)
);

CREATE TABLE Toa (
    maToa VARCHAR(50) PRIMARY KEY,
    loaiToa NVARCHAR(50) NOT NULL,
    maTau VARCHAR(50) NOT NULL,
    FOREIGN KEY (maTau) REFERENCES ChuyenTau(maTau)
);

-- Tạo bảng Ghe
CREATE TABLE Ghe (
    soGhe INT NOT NULL,
    maToa VARCHAR(50) NOT NULL,
    trangThai BIT NOT NULL,
    PRIMARY KEY (soGhe, maToa),
    FOREIGN KEY (maToa) REFERENCES Toa(maToa)
);

-- Tạo bảng Ca
CREATE TABLE Ca (
    maCa VARCHAR(50) PRIMARY KEY,
    tenCa NVARCHAR(100) NOT NULL,
    thoiGianBatDau TIME NOT NULL,
    thoiGianKetThuc TIME NOT NULL
);

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    maNV VARCHAR(50) PRIMARY KEY,
    tenNV NVARCHAR(100) NOT NULL,
    ngaySinh DATE,
    gioiTinh BIT,
	ca VARCHAR(50) NOT NULL,
	cccd VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    sdt VARCHAR(20),
    trangThai BIT,
    chucVu INT NOT NULL,
	FOREIGN KEY (ca) REFERENCES Ca(maCa)
);

-- Tạo bảng KhachHang
CREATE TABLE KhachHang (
    maKH VARCHAR(50) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    email VARCHAR(100),
    sdt VARCHAR(20) NOT NULL,
    cccd VARCHAR(20)
);

-- Tạo bảng HoaDon
CREATE TABLE HoaDon (
    maHoaDon VARCHAR(50) PRIMARY KEY,
    ngayLapHoaDon DATETIME NOT NULL,
    nhanVien VARCHAR(50) NOT NULL,
    khachHang VARCHAR(50) NOT NULL,
    daHoanVe BIT NOT NULL,
	daHoanTien BIT NOT NULL,
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNV),
    FOREIGN KEY (khachHang) REFERENCES KhachHang(maKH)
);

-- Tạo bảng ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
    maChiTiet VARCHAR(50) PRIMARY KEY,
    hoaDon VARCHAR(50) NOT NULL,
    soLuong INT NOT NULL,
    thue FLOAT,
    FOREIGN KEY (hoaDon) REFERENCES HoaDon(maHoaDon)
);

-- Tạo bảng Ve
CREATE TABLE Ve (
    maVe VARCHAR(50) PRIMARY KEY,
    tau VARCHAR(50) NOT NULL,
    toa VARCHAR(50) NOT NULL,
    soGhe INT NOT NULL,
    khachHang VARCHAR(50) NOT NULL,
    ngayDi DATE NOT NULL,
    gioDi TIME NOT NULL,
	ngayDen DATE NOT NULL,
	gioDen TIME NOT NULL,
	gaDi VARCHAR(50) NOT NULL,
    gaDen VARCHAR(50) NOT NULL,
	hang NVARCHAR(100) NOT NULL,
	khuyenMai NVARCHAR(100) NOT NULL,
    trangThai BIT,
	chiTiet VARCHAR(50) NOT NULL,
    FOREIGN KEY (tau) REFERENCES ChuyenTau(maTau),
    FOREIGN KEY (toa) REFERENCES Toa(maToa),
    FOREIGN KEY (soGhe, toa) REFERENCES Ghe(soGhe, maToa),
    FOREIGN KEY (khachHang) REFERENCES KhachHang(maKH),
    FOREIGN KEY (gaDen) REFERENCES Ga(maGa),
	FOREIGN KEY (chiTiet) REFERENCES ChiTietHoaDon(maChiTiet)
);

-- Tạo bảng TaiKhoan
CREATE TABLE TaiKhoan (
    maTaiKhoan VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(60) NOT NULL,
    phanQuyen INT NOT NULL,
    nhanVien VARCHAR(50),
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNV)
);

-- Thêm ca vào bảng Ca
INSERT INTO Ca (maCa, tenCa, thoiGianBatDau, thoiGianKetThuc) VALUES
('CA01', N'Ca 1', '06:00:00', '13:59:00'),
('CA02', N'Ca 2', '14:00:00', '21:59:00'),
('CA03', N'Ca 3', '22:00:00', '05:59:00');

-- Thêm 4 nhân viên vào bảng NhanVien
INSERT INTO NhanVien (maNV, tenNV, ngaySinh, gioiTinh, ca, cccd, email, sdt, trangThai, chucVu) VALUES
('NV001', N'Trần Thanh Xuân', '2005-03-30', 0, 'CA01', '049204013502', 'tranthanhxuan400@gmail.com', '0919128639', 1, 1),
('NV002', N'Trần Huỳnh Duy Hiệu', '2005-08-12', 1, 'CA01', '049203000000', 'duyhieu12@gmail.com', '0334548218', 1, 2),
('NV003', N'Nguyễn Hữu Lộc', '2005-12-14', 0, 'CA02', '079204033973', 'huuloc041214@gmail.com', '0765593697', 1, 2),
('NV004', N'Cù Văn Ngọc', '2005-12-23', 0, 'CA03', '052204011638', 'cuvanngoc2k4@gmail.com', '0366271754', 1, 2),
('NV005', N'Nguyễn Văn A', '2005-12-23', 0, 'CA02', '052204011639', 'nguyenvana2k4@gmail.com', '0366271755', 1, 1),
('NV006', N'Lê Tấn B', '2005-04-30', 0, 'CA03', '049204013504', 'letanb@gmail.com', '0919128638', 1, 1);

-- Thêm tài khoản vào bảng TaiKhoan
INSERT INTO TaiKhoan (maTaiKhoan, matKhau, phanQuyen, nhanVien) VALUES
('TKQL001','TKQL001@',1,'NV001'),
('TKNV001', 'TKNV001@', 2, 'NV002'),
('TKNV002', 'TKNV002@', 2, 'NV003'),
('TKNV003', 'TKNV003@', 2, 'NV004'),
('TKQL002','TKQL002@',1,'NV005'),
('TKQL003','TKQL003@',1,'NV006');

-- Thêm các nhà ga vào bảng Ga
INSERT INTO Ga (maGa, tenGa, diaChi, chiSoKm, trangThai) VALUES
('GA001', N'Ga Hà Nội', N'Hà Nội', 0, 1),
('GA002', N'Ga Giáp Bát', N'Hà Nội', 6, 1),
('GA003', N'Ga Văn Điển', N'Hà Nội', 10, 1),
('GA004', N'Ga Thường Tín', N'Hà Nội', 19, 1),
('GA005', N'Ga Chợ Tía', N'Hà Nội', 27, 1),
('GA006', N'Ga Phú Xuyên', N'Hà Nội', 33, 1),
('GA007', N'Ga Đồng Văn', N'Hà Nam', 49, 1),
('GA008', N'Ga Phủ Lý', N'Hà Nam', 58, 1),
('GA009', N'Ga Bình Lục', N'Hà Nam', 76, 1),
('GA010', N'Ga Cầu Họ', N'Hà Nam', 80, 1),
('GA011', N'Ga Đặng Xá', N'Nam Định', 85, 1),
('GA012', N'Ga Nam Định', N'Nam Định', 87, 1),
('GA013', N'Ga Trình Xuyên', N'Nam Định', 94, 1),
('GA014', N'Ga Núi Gôi', N'Nam Định', 100, 1),
('GA015', N'Ga Cát Đằng', N'Nam Định', 105, 1),
('GA016', N'Ga Ninh Bình', N'Ninh Bình', 115, 1),
('GA017', N'Ga Cầu Yên', N'Ninh Bình', 123, 1),
('GA018', N'Ga Ghềnh', N'Ninh Bình', 130, 1),
('GA019', N'Ga Đồng Giao', N'Ninh Bình', 136, 1),
('GA020', N'Ga Bỉm Sơn', N'Thanh Hóa', 149, 1),
('GA021', N'Ga Đò Lèn', N'Thanh Hóa', 159, 1),
('GA022', N'Ga Nghĩa Trang', N'Thanh Hóa', 167, 1),
('GA023', N'Ga Thanh Hóa', N'Thanh Hóa', 175, 1),
('GA024', N'Ga Yên Thái', N'Thanh Hóa', 187, 1),
('GA025', N'Ga Minh Khôi', N'Thanh Hóa', 193, 1),
('GA026', N'Ga Thị Long', N'Thanh Hóa', 199, 1),
('GA027', N'Ga Văn Trai', N'Thanh Hóa', 207, 1),
('GA028', N'Ga Khoa Trường', N'Thanh Hóa', 215, 1),
('GA029', N'Ga Trường Lâm', N'Thanh Hóa', 222, 1),
('GA030', N'Ga Hoàng Mai', N'Nghệ An', 232, 1),
('GA031', N'Ga Yên Lý', N'Nghệ An', 246, 1),
('GA032', N'Ga Chợ Sy', N'Nghệ An', 256, 1),
('GA033', N'Ga Mỹ Lý', N'Nghệ An', 266, 1),
('GA034', N'Ga Quán Hành', N'Nghệ An', 296, 1),
('GA035', N'Ga Vinh', N'Nghệ An', 319, 1),
('GA036', N'Ga Yên Xuân', N'Nghệ An', 326, 1),
('GA037', N'Ga Yên Trung', N'Nghệ An', 334, 1),
('GA038', N'Ga Đức Lạc', N'Hà Tĩnh', 346, 1),
('GA039', N'Ga Yên Duệ', N'Hà Tĩnh', 353, 1),
('GA040', N'Ga Hòa Duyệt', N'Hà Tĩnh', 365, 1),
('GA041', N'Ga Thanh Luyện', N'Hà Tĩnh', 376, 1),
('GA042', N'Ga Chu Lễ', N'Hà Tĩnh', 386, 1),
('GA043', N'Ga Hương Phố', N'Hà Tĩnh', 396, 1),
('GA044', N'Ga Phúc Trạch', N'Hà Tĩnh', 405, 1),
('GA045', N'Ga La Khê', N'Hà Tĩnh', 416, 1),
('GA046', N'Ga Tân Ấp', N'Quảng Bình', 430, 1),
('GA047', N'Ga Đồng Chuối', N'Quảng Bình', 440, 1),
('GA048', N'Ga Kim Lũ', N'Quảng Bình', 450, 1),
('GA049', N'Ga Đồng Lê', N'Quảng Bình', 460, 1),
('GA050', N'Ga Ngọc Lâm', N'Quảng Bình', 470, 1),
('GA051', N'Ga Lạc Sơn', N'Quảng Bình', 480, 1),
('GA052', N'Ga Lệ Sơn', N'Quảng Bình', 490, 1),
('GA053', N'Ga Minh Lệ', N'Quảng Bình', 500, 1),
('GA054', N'Ga Ngân Sơn', N'Quảng Bình', 510, 1),
('GA055', N'Ga Thọ Lộc', N'Quảng Bình', 520, 1),
('GA056', N'Ga Hoàn Lão', N'Quảng Bình', 530, 1),
('GA057', N'Ga Phúc Tự', N'Quảng Bình', 540, 1),
('GA058', N'Ga Đồng Hới', N'Quảng Bình', 552, 1),
('GA059', N'Ga Lệ Kỳ', N'Quảng Bình', 529, 1),
('GA060', N'Ga Long Đại', N'Quảng Bình', 539, 1),
('GA061', N'Ga Mỹ Đức', N'Quảng Bình', 551, 1),
('GA062', N'Ga Phú Hòa', N'Quảng Bình', 558, 1),
('GA063', N'Ga Mỹ Trạch', N'Quảng Bình', 565, 1),
('GA064', N'Ga Thượng Lâm', N'Quảng Bình', 572, 1),
('GA065', N'Ga Sa Lung', N'Quảng Trị', 588, 1),
('GA066', N'Ga Tiên An', N'Quảng Trị', 599, 1),
('GA067', N'Ga Hà Thanh', N'Quảng Trị', 610, 1),
('GA068', N'Ga Đông Hà', N'Quảng Trị', 622, 1),
('GA069', N'Ga Quảng Trị', N'Quảng Trị', 634, 1),
('GA070', N'Ga Diên Sanh', N'Quảng Trị', 643, 1),
('GA071', N'Ga Mỹ Chánh', N'Quảng Trị', 652, 1),
('GA072', N'Ga Phò Trạch', N'Thừa Thiên Huế', 660, 1),
('GA073', N'Ga Hiền Sỹ', N'Thừa Thiên Huế', 670, 1),
('GA074', N'Ga Văn Xá', N'Thừa Thiên Huế', 678, 1),
('GA075', N'Ga Huế', N'Thừa Thiên Huế', 688, 1),
('GA076', N'Ga Hương Thủy', N'Thừa Thiên Huế', 699, 1),
('GA077', N'Ga Truồi', N'Thừa Thiên Huế', 715, 1),
('GA078', N'Ga Cầu Hai', N'Thừa Thiên Huế', 729, 1),
('GA079', N'Ga Thừa Lưu', N'Thừa Thiên Huế', 742, 1),
('GA080', N'Ga Lăng Cô', N'Thừa Thiên Huế', 755, 1),
('GA081', N'Ga Hải Vân Bắc', N'Thừa Thiên Huế', 761, 1),
('GA082', N'Ga Hải Vân', N'Thừa Thiên Huế', 767, 1),
('GA083', N'Ga Hải Vân Nam', N'Đà Nẵng', 772, 1),
('GA084', N'Ga Kim Liên', N'Đà Nẵng', 777, 1),
('GA085', N'Ga Đà Nẵng', N'Đà Nẵng', 791, 1),
('GA086', N'Ga Thanh Khê', N'Đà Nẵng', 793, 1),
('GA087', N'Ga Lệ Trạch', N'Đà Nẵng', 804, 1),
('GA088', N'Ga Nông Sơn', N'Quảng Nam', 814, 1),
('GA089', N'Ga Trà Kiệu', N'Quảng Nam', 825, 1),
('GA090', N'Ga Phú Cang', N'Quảng Nam', 842, 1),
('GA091', N'Ga Tam Thành', N'Quảng Nam', 855, 1),
('GA092', N'Ga An Mỹ', N'Quảng Nam', 857, 1),
('GA093', N'Ga Tam Kỳ', N'Quảng Nam', 865, 1),
('GA094', N'Ga Diêm Phổ', N'Quảng Nam', 879, 1),
('GA095', N'Ga Núi Thành', N'Quảng Nam', 890, 1),
('GA096', N'Ga Trì Bình', N'Quảng Ngãi', 901, 1),
('GA097', N'Ga Bình Sơn', N'Quảng Ngãi', 909, 1),
('GA098', N'Ga Đại Lộc', N'Quảng Ngãi', 920, 1),
('GA099', N'Ga Quảng Ngãi', N'Quảng Ngãi', 928, 1),
('GA100', N'Ga Hòa Vinh Tây', N'Quảng Ngãi', 940, 1),
('GA101', N'Ga Mộ Đức', N'Quảng Ngãi', 949, 1),
('GA102', N'Ga Thạch Trụ', N'Quảng Ngãi', 959, 1),
('GA103', N'Ga Đức Phổ', N'Quảng Ngãi', 968, 1),
('GA104', N'Ga Thủy Thạch', N'Quảng Ngãi', 977, 1),
('GA105', N'Ga Sa Huỳnh', N'Quảng Ngãi', 991, 1),
('GA106', N'Ga Tam Quan', N'Bình Định', 1004, 1),
('GA107', N'Ga Bồng Sơn', N'Bình Định', 1017, 1),
('GA108', N'Ga Vạn Phú', N'Bình Định', 1033, 1),
('GA109', N'Ga Phù Mỹ', N'Bình Định', 1050, 1),
('GA110', N'Ga Khánh Phước', N'Bình Định', 1060, 1),
('GA111', N'Ga Phù Cát', N'Bình Định', 1071, 1),
('GA112', N'Ga Bình Định', N'Bình Định', 1085, 1),
('GA113', N'Ga Diêu Trì', N'Bình Định', 1096, 1),
('GA114', N'Ga Tân Vinh', N'Bình Định', 1111, 1),
('GA115', N'Ga Vân Canh', N'Bình Định', 1123, 1),
('GA116', N'Ga Phước Lãnh', N'Phú Yên', 1139, 1),
('GA117', N'Ga La Hai', N'Phú Yên', 1154, 1),
('GA118', N'Ga Xuân Sơn Nam', N'Phú Yên', 1162, 1),
('GA119', N'Ga Chí Thạnh', N'Phú Yên', 1170, 1),
('GA120', N'Ga Hòa Đa', N'Phú Yên', 1184, 1),
('GA121', N'Ga Tuy Hòa', N'Phú Yên', 1198, 1),
('GA122', N'Ga Đông Tác', N'Phú Yên', 1202, 1),
('GA123', N'Ga Phú Hiệp', N'Phú Yên', 1211, 1),
('GA124', N'Ga Hảo Sơn', N'Phú Yên', 1220, 1),
('GA125', N'Ga Đại Lãnh', N'Khánh Hòa', 1232, 1),
('GA126', N'Ga Tu Bông', N'Khánh Hòa', 1242, 1),
('GA127', N'Ga Giã', N'Khánh Hòa', 1254, 1),
('GA128', N'Ga Hòa Huỳnh', N'Khánh Hòa', 1269, 1),
('GA129', N'Ga Ninh Hòa', N'Khánh Hòa', 1281, 1),
('GA130', N'Ga Phong Thạnh', N'Khánh Hòa', 1287, 1),
('GA131', N'Ga Lương Sơn', N'Khánh Hòa', 1303, 1),
('GA132', N'Ga Nha Trang', N'Khánh Hòa', 1315, 1),
('GA133', N'Ga Cây Cầy', N'Khánh Hòa', 1330, 1),
('GA134', N'Ga Hòa Tân', N'Khánh Hòa', 1341, 1),
('GA135', N'Ga Suối Cát', N'Khánh Hòa', 1351, 1),
('GA136', N'Ga Ngã Ba', N'Khánh Hòa', 1364, 1),
('GA137', N'Ga Cam Thịnh Đông', N'Khánh Hòa', 1372, 1),
('GA138', N'Ga Kà Rôm', N'Ninh Thuận', 1382, 1),
('GA139', N'Ga Phước Nhơn', N'Ninh Thuận', 1398, 1),
('GA140', N'Ga Tháp Chàm', N'Ninh Thuận', 1408, 1),
('GA141', N'Ga Hòa Trinh', N'Ninh Thuận', 1420, 1),
('GA142', N'Ga Cà Ná', N'Ninh Thuận', 1436, 1),
('GA143', N'Ga Vĩnh Tân', N'Bình Thuận', 1446, 1),
('GA144', N'Ga Vĩnh Hảo', N'Bình Thuận', 1455, 1),
('GA145', N'Ga Sông Lòng Sông', N'Bình Thuận', 1466, 1),
('GA146', N'Ga Phong Phú', N'Bình Thuận', 1473, 1),
('GA147', N'Ga Sông Mao', N'Bình Thuận', 1484, 1),
('GA148', N'Ga Châu Hanh', N'Bình Thuận', 1494, 1),
('GA149', N'Ga Sông Lũy', N'Bình Thuận', 1506, 1),
('GA150', N'Ga Long Thạnh', N'Bình Thuận', 1523, 1),
('GA151', N'Ga Ma Lâm', N'Bình Thuận', 1533, 1),
('GA152', N'Ga Hàm Liêm', N'Bình Thuận', 1542, 1),
('GA153', N'Ga Bình Thuận', N'Bình Thuận', 1551, 1),
('GA154', N'Ga Hàm Cường Tây', N'Bình Thuận', 1560, 1),
('GA155', N'Ga Suối Vận', N'Bình Thuận', 1568, 1),
('GA156', N'Ga Sông Phan', N'Bình Thuận', 1583, 1),
('GA157', N'Ga Sông Dinh', N'Bình Thuận', 1596, 1),
('GA158', N'Ga Suối Kiết', N'Bình Thuận', 1603, 1),
('GA159', N'Ga Gia Huynh', N'Bình Thuận', 1614, 1),
('GA160', N'Ga Trản Táo', N'Dồng Nai', 1620, 1),
('GA161', N'Ga Gia Ray', N'Dồng Nai', 1631, 1),
('GA162', N'Ga Bảo Chánh', N'Dồng Nai', 1640, 1),
('GA163', N'Ga Long Khánh', N'Dồng Nai', 1650, 1),
('GA164', N'Ga Dầu Giây', N'Dồng Nai', 1661, 1),
('GA165', N'Ga Trung Hòa', N'Dồng Nai', 1669, 1),
('GA166', N'Ga Trảng Bom', N'Dồng Nai', 1678, 1),
('GA167', N'Ga Hố Nai', N'Dồng Nai', 1688, 1),
('GA168', N'Ga Biên Hòa', N'Dồng Nai', 1697, 1),
('GA169', N'Ga Dĩ An', N'Bình Dương', 1707, 1),
('GA170', N'Ga Sóng Thần', N'Bình Dương', 1711, 1),
('GA171', N'Ga Bình Triệu', N'Thành phố Hồ Chí Minh', 1718, 1),
('GA172', N'Ga Gò Vấp', N'Thành phố Hồ Chí Minh', 1722, 1),
('GA173', N'Ga Sài Gòn', N'Thành phố Hồ Chí Minh', 1726, 1);

-- Thêm chuyến tàu
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen) VALUES
('TA001', 'GA001', 'GA173', '2025-12-21', '06:00:00', '2025-12-22', '18:00:00');

-- Thêm các trạm dừng cho chuyến tàu
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen) VALUES
('TA001', 'GA075', '2025-12-21', '06:00:00', '2025-12-21', '20:33:00'), -- Huế
('TA001', 'GA095', '2025-12-21', '06:00:00', '2025-12-21', '23:07:00'), -- Núi Thành
('TA001', 'GA132', '2025-12-21', '06:00:00', '2025-12-22', '09:46:00'); -- Nha Trang

-- Thêm các toa cho chuyến tàu TA001
INSERT INTO Toa (maToa, loaiToa, maTau) VALUES
('TA001_01', N'Giường nằm', 'TA001'),
('TA001_02', N'Giường nằm', 'TA001'),
('TA001_03', N'Ghế mềm', 'TA001'),
('TA001_04', N'Ghế mềm', 'TA001'),
('TA001_05', N'VIP', 'TA001');

-- Thêm các ghế cho mỗi toa tàu
-- Toa Giường nằm có 32 ghế, Ghế mềm có 64 ghế, toa VIP có 20 ghế
-- Thêm ghế cho TA001
DECLARE @i INT;
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA001_01', 1);
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA001_02', 1);
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA001_03', 1);
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA001_04', 1);
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA001_05', 1);
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA002
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA002', 'GA173', 'GA001', '2025-12-23', '06:00:00', '2025-12-24', '19:00:00');

-- Thêm các ga dừng
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA002', 'GA153', '2025-12-23', '08:30:00', '2025-12-23', '09:30:00'),
('TA002', 'GA099', '2025-12-23', '11:00:00', '2025-12-23', '21:50:00'),  
('TA002', 'GA035', '2025-12-23', '14:00:00', '2025-12-24', '11:20:00');  

-- Thêm toa ghế mềm
INSERT INTO Toa (maToa, loaiToa, maTau)
VALUES 
('TA002_01', N'Ghế mềm', 'TA002'),
('TA002_02', N'Ghế mềm', 'TA002'),
('TA002_03', N'Ghế mềm', 'TA002');

-- Thêm toa giường nằm
INSERT INTO Toa (maToa, loaiToa, maTau)
VALUES 
('TA002_04', N'Giường nằm', 'TA002'),
('TA002_05', N'Giường nằm', 'TA002'),
('TA002_06', N'Giường nằm', 'TA002');

-- Thêm các ghế trong các toa
-- Toa ghế mềm (64 ghế/toa)
DECLARE @toaGhếMềm INT = 64;
DECLARE @toaGiườngNằm INT = 32;

-- Thêm ghế cho các toa ghế mềm
WHILE @i <= 64
BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai)
    VALUES (@i, 'TA002_01', 0), (@i, 'TA002_02', 0), (@i, 'TA002_03', 0);
    SET @i = @i + 1;
END;

-- Thêm ghế cho các toa giường nằm
SET @i = 1;
WHILE @i <= 32
BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai)
    VALUES (@i, 'TA002_04', 0), (@i, 'TA002_05', 0), (@i, 'TA002_06', 0);
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA003
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA003', 'GA001', 'GA045', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00');

-- Thêm các ga dừng cho chuyến tàu TA003
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA003', 'GA020', '2025-12-21', '08:30:00', '2025-12-21', '09:00:00'),
('TA003', 'GA035', '2025-12-21', '11:00:00', '2025-12-21', '14:30:00');

-- Thêm toa cho chuyến tàu TA003
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA003_01', N'Giường nằm', 'TA003'),
('TA003_02', N'Giường nằm', 'TA003'),
('TA003_03', N'Ghế mềm', 'TA003'),
('TA003_04', N'Ghế mềm', 'TA003'),
('TA003_05', N'Ghế mềm', 'TA003'),
('TA003_06', N'VIP', 'TA003'),
('TA003_07', N'VIP', 'TA003'),
('TA003_08', N'VIP', 'TA003');

-- Thêm ghế cho các toa trong chuyến tàu TA003
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_06', 1); -- VIP
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_07', 1); -- VIP
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA003_08', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA004
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA004', 'GA015', 'GA080', '2025-12-21', '09:00:00', '2025-12-21', '22:00:00');

-- Thêm các ga dừng cho chuyến tàu TA004
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA004', 'GA020', '2025-12-21', '09:30:00', '2025-12-21', '10:30:00'),
('TA004', 'GA030', '2025-12-21', '13:30:00', '2025-12-21', '14:30:00'),
('TA004', 'GA070', '2025-12-21', '16:30:00', '2025-12-21', '19:00:00');

-- Thêm toa cho chuyến tàu TA004
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA004_01', N'Giường nằm', 'TA004'),
('TA004_02', N'Giường nằm', 'TA004'),
('TA004_03', N'Giường nằm', 'TA004'),
('TA004_04', N'Ghế mềm', 'TA004'),
('TA004_05', N'Ghế mềm', 'TA004'),
('TA004_06', N'Ghế mềm', 'TA004'),
('TA004_07', N'VIP', 'TA004');

-- Thêm ghế cho các toa trong chuyến tàu TA004
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA004_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA004_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA004_03', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA004_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA004_05', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA004_07', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA005
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA005', 'GA075', 'GA095', '2025-12-21', '07:00:00', '2025-12-21', '15:30:00');

-- Thêm các ga dừng cho chuyến tàu TA005
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA005', 'GA090', '2025-12-21', '07:30:00', '2025-12-21', '10:00:00');

-- Thêm toa cho chuyến tàu TA005
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA005_01', N'Giường nằm', 'TA005'),
('TA005_02', N'Giường nằm', 'TA005'),
('TA005_03', N'Ghế mềm', 'TA005'),
('TA005_04', N'Ghế mềm', 'TA005'),
('TA005_05', N'VIP', 'TA005');

-- Thêm ghế cho các toa trong chuyến tàu TA005
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA005_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA005_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA005_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA005_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA005_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA006
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA006', 'GA045', 'GA120', '2025-12-23', '10:00:00', '2025-12-23', '22:00:00');

-- Thêm các ga dừng cho chuyến tàu TA006
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA006', 'GA050', '2025-12-23', '10:30:00', '2025-12-23', '11:00:00'),
('TA006', 'GA100', '2025-12-23', '12:30:00', '2025-12-23', '13:30:00'),
('TA006', 'GA110', '2025-12-23', '14:30:00', '2025-12-23', '16:00:00');

-- Thêm toa cho chuyến tàu TA006
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA006_01', N'Giường nằm', 'TA006'),
('TA006_02', N'Giường nằm', 'TA006'),
('TA006_03', N'Ghế mềm', 'TA006'),
('TA006_04', N'Ghế mềm', 'TA006'),
('TA006_05', N'VIP', 'TA006'),
('TA006_06', N'VIP', 'TA006');

-- Thêm ghế cho các toa trong chuyến tàu TA006
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA006_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA006_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA006_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA006_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA006_05', 1); -- VIP
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA006_06', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA007
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA007', 'GA120', 'GA150', '2025-12-23', '09:30:00', '2025-12-23', '21:30:00');

-- Thêm các ga dừng cho chuyến tàu TA007
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA007', 'GA130', '2025-12-23', '10:00:00', '2025-12-23', '11:00:00'),
('TA007', 'GA140', '2025-12-23', '12:00:00', '2025-12-23', '13:30:00');

-- Thêm toa cho chuyến tàu TA007
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA007_01', N'Giường nằm', 'TA007'),
('TA007_02', N'Giường nằm', 'TA007'),
('TA007_03', N'Ghế mềm', 'TA007'),
('TA007_04', N'Ghế mềm', 'TA007'),
('TA007_05', N'VIP', 'TA007');

-- Thêm ghế cho các toa trong chuyến tàu TA007
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA007_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA007_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA007_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA007_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA007_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA008
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA008', 'GA150', 'GA110', '2025-12-24', '07:30:00', '2025-12-24', '18:00:00');

-- Thêm các ga dừng cho chuyến tàu TA008
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA008', 'GA140', '2025-12-24', '08:00:00', '2025-12-24', '09:00:00'),
('TA008', 'GA130', '2025-12-24', '10:30:00', '2025-12-24', '11:30:00'),
('TA008', 'GA120', '2025-12-24', '13:00:00', '2025-12-24', '14:30:00');

-- Thêm toa cho chuyến tàu TA008
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA008_01', N'Giường nằm', 'TA008'),
('TA008_02', N'Giường nằm', 'TA008'),
('TA008_03', N'Ghế mềm', 'TA008'),
('TA008_04', N'Ghế mềm', 'TA008'),
('TA008_05', N'VIP', 'TA008');

-- Thêm ghế cho các toa trong chuyến tàu TA008
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA008_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA008_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA008_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA008_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA008_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA009
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA009', 'GA110', 'GA100', '2025-12-24', '06:00:00', '2025-12-24', '17:00:00');

-- Thêm toa cho chuyến tàu TA009
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA009_01', N'Giường nằm', 'TA009'),
('TA009_02', N'Giường nằm', 'TA009'),
('TA009_03', N'Ghế mềm', 'TA009'),
('TA009_04', N'Ghế mềm', 'TA009'),
('TA009_05', N'VIP', 'TA009');

-- Thêm ghế cho các toa trong chuyến tàu TA009
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA009_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA009_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA009_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA009_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA009_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA010
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA010', 'GA160', 'GA140', '2025-12-25', '06:30:00', '2025-12-25', '16:00:00');

-- Thêm các ga dừng cho chuyến tàu TA010
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA010', 'GA150', '2025-12-25', '09:00:00', '2025-12-25', '10:30:00');

-- Thêm toa cho chuyến tàu TA010
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA010_01', N'Giường nằm', 'TA010'),
('TA010_02', N'Giường nằm', 'TA010'),
('TA010_03', N'Ghế mềm', 'TA010'),
('TA010_04', N'Ghế mềm', 'TA010'),
('TA010_05', N'VIP', 'TA010');

-- Thêm ghế cho các toa trong chuyến tàu TA010
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA010_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA010_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA010_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA010_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA010_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA011
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA011', 'GA140', 'GA130', '2025-12-25', '07:00:00', '2025-12-25', '17:00:00');

-- Thêm các ga dừng cho chuyến tàu TA011
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA011', 'GA135', '2025-12-25', '08:00:00', '2025-12-25', '09:30:00');

-- Thêm toa cho chuyến tàu TA011
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA011_01', N'Giường nằm', 'TA011'),
('TA011_02', N'Giường nằm', 'TA011'),
('TA011_03', N'Ghế mềm', 'TA011'),
('TA011_04', N'Ghế mềm', 'TA011'),
('TA011_05', N'VIP', 'TA011');

-- Thêm ghế cho các toa trong chuyến tàu TA011
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA011_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA011_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA011_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA011_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA011_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA012
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA012', 'GA120', 'GA130', '2025-12-26', '05:30:00', '2025-12-26', '15:30:00');

-- Thêm các ga dừng cho chuyến tàu TA012
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA012', 'GA123', '2025-12-26', '06:30:00', '2025-12-26', '08:00:00');

-- Thêm toa cho chuyến tàu TA012
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA012_01', N'Giường nằm', 'TA012'),
('TA012_02', N'Giường nằm', 'TA012'),
('TA012_03', N'Ghế mềm', 'TA012'),
('TA012_04', N'Ghế mềm', 'TA012'),
('TA012_05', N'VIP', 'TA012');

-- Thêm ghế cho các toa trong chuyến tàu TA012
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA012_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA012_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA012_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA012_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA012_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA013
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA013', 'GA100', 'GA140', '2025-12-26', '07:00:00', '2025-12-26', '17:30:00');

-- Thêm các ga dừng cho chuyến tàu TA013
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA013', 'GA110', '2025-12-26', '08:00:00', '2025-12-26', '09:30:00'),
('TA013', 'GA130', '2025-12-26', '10:00:00', '2025-12-26', '11:30:00');

-- Thêm toa cho chuyến tàu TA013
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA013_01', N'Giường nằm', 'TA013'),
('TA013_02', N'Giường nằm', 'TA013'),
('TA013_03', N'Ghế mềm', 'TA013'),
('TA013_04', N'Ghế mềm', 'TA013'),
('TA013_05', N'VIP', 'TA013');

-- Thêm ghế cho các toa trong chuyến tàu TA013
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA013_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA013_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA013_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA013_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA013_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA014
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA014', 'GA040', 'GA080', '2025-12-27', '06:00:00', '2025-12-27', '16:00:00');

-- Thêm các ga dừng cho chuyến tàu TA014
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA014', 'GA060', '2025-12-27', '07:00:00', '2025-12-27', '08:30:00'),
('TA014', 'GA070', '2025-12-27', '09:00:00', '2025-12-27', '10:30:00'),
('TA014', 'GA078', '2025-12-27', '11:00:00', '2025-12-27', '12:30:00');

-- Thêm toa cho chuyến tàu TA014
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA014_01', N'Giường nằm', 'TA014'),
('TA014_02', N'Giường nằm', 'TA014'),
('TA014_03', N'Ghế mềm', 'TA014'),
('TA014_04', N'Ghế mềm', 'TA014'),
('TA014_05', N'VIP', 'TA014');

-- Thêm ghế cho các toa trong chuyến tàu TA014
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA014_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA014_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA014_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA014_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA014_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA015
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA015', 'GA169', 'GA099', '2025-12-27', '08:00:00', '2025-12-27', '18:00:00');

-- Thêm các ga dừng cho chuyến tàu TA015
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA015', 'GA150', '2025-12-27', '09:00:00', '2025-12-27', '10:30:00'),
('TA015', 'GA120', '2025-12-27', '11:00:00', '2025-12-27', '12:30:00'),
('TA015', 'GA100', '2025-12-27', '13:00:00', '2025-12-27', '14:30:00');

-- Thêm toa cho chuyến tàu TA015
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA015_01', N'Giường nằm', 'TA015'),
('TA015_02', N'Giường nằm', 'TA015'),
('TA015_03', N'Ghế mềm', 'TA015'),
('TA015_04', N'Ghế mềm', 'TA015'),
('TA015_05', N'VIP', 'TA015');

-- Thêm ghế cho các toa trong chuyến tàu TA015
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA015_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA015_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA015_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA015_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA015_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA016
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA016', 'GA100', 'GA045', '2025-12-28', '07:00:00', '2025-12-28', '17:00:00');

-- Thêm các ga dừng cho chuyến tàu TA016
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA016', 'GA090', '2025-12-28', '08:00:00', '2025-12-28', '09:30:00'),
('TA016', 'GA060', '2025-12-28', '10:00:00', '2025-12-28', '11:30:00'),
('TA016', 'GA050', '2025-12-28', '12:00:00', '2025-12-28', '13:30:00');

-- Thêm toa cho chuyến tàu TA016
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA016_01', N'Giường nằm', 'TA016'),
('TA016_02', N'Giường nằm', 'TA016'),
('TA016_03', N'Ghế mềm', 'TA016'),
('TA016_04', N'Ghế mềm', 'TA016'),
('TA016_05', N'VIP', 'TA016');

-- Thêm ghế cho các toa trong chuyến tàu TA016
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA016_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA016_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA016_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA016_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA016_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA017
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA017', 'GA139', 'GA035', '2025-12-28', '09:00:00', '2025-12-28', '19:00:00');

-- Thêm các ga dừng cho chuyến tàu TA017
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA017', 'GA123', '2025-12-28', '10:00:00', '2025-12-28', '11:30:00'),
('TA017', 'GA086', '2025-12-28', '12:00:00', '2025-12-28', '13:30:00'),
('TA017', 'GA045', '2025-12-28', '14:00:00', '2025-12-28', '15:30:00');

-- Thêm toa cho chuyến tàu TA017
INSERT INTO Toa (maToa, loaiToa, maTau) 
VALUES 
('TA017_01', N'Giường nằm', 'TA017'),
('TA017_02', N'Giường nằm', 'TA017'),
('TA017_03', N'Ghế mềm', 'TA017'),
('TA017_04', N'Ghế mềm', 'TA017'),
('TA017_05', N'VIP', 'TA017');

-- Thêm ghế cho các toa trong chuyến tàu TA017
SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA017_01', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 32 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA017_02', 1); -- Giường nằm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA017_03', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 64 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA017_04', 1); -- Ghế mềm
    SET @i = @i + 1;
END;

SET @i = 1;
WHILE @i <= 20 BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA017_05', 1); -- VIP
    SET @i = @i + 1;
END;

-- Thêm chuyến tàu TA018
INSERT INTO ChuyenTau (maTau, gaDi, gaDen, ngayDi, gioDi, ngayDen, gioDen)
VALUES ('TA018', 'GA173', 'GA001', '2025-12-26', '06:00:00', '2025-12-27', '19:00:00');

-- Thêm các ga dừng
INSERT INTO ChuyenTau_Ga (maTau, maGa, ngayDi, gioDi, ngayDen, gioDen)
VALUES 
('TA018', 'GA153', '2025-12-26', '08:30:00', '2025-12-26', '09:30:00'),
('TA018', 'GA099', '2025-12-26', '11:00:00', '2025-12-26', '21:50:00'),  
('TA018', 'GA035', '2025-12-26', '14:00:00', '2025-12-27', '11:20:00');  

-- Thêm các toa
INSERT INTO Toa (maToa, loaiToa, maTau)
VALUES 
('TA018_01', N'Giường nằm', 'TA018'),
('TA018_02', N'Giường nằm', 'TA018'),
('TA018_03', N'Ghế mềm', 'TA018'),
('TA018_04', N'Ghế mềm', 'TA018'),
('TA018_05', N'VIP', 'TA018');

-- Ghế mềm (32 ghế mỗi toa)
-- Thêm ghế cho TA001
SET @i = 1;
WHILE @i <= 32
BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA018_01', 1);
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA018_02', 1);
    SET @i = @i + 1;
END;

-- Ghế mềm (64 ghế mỗi toa)
SET @i = 1;
WHILE @i <= 64
BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA018_03', 1);
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA018_04', 1);
    SET @i = @i + 1;
END;

-- VIP (20 ghế mỗi toa)
SET @i = 1;
WHILE @i <= 20
BEGIN
    INSERT INTO Ghe (soGhe, maToa, trangThai) VALUES (@i, 'TA018_05', 1);
    SET @i = @i + 1;
END;

-- Thêm 5 khách hàng
INSERT INTO KhachHang (maKH, tenKH, email, sdt, cccd) VALUES
('KH0001', N'Nguyễn Văn A', 'abc@gmail.com', '0912345678', '049204013502'),
('KH0002', N'Trần Thị B', 'def@gmail.com', '0912345679', '049204013503'),
('KH0003', N'Phạm Văn C', 'ghi@gmail.com', '0912345680', '049204013504'),
('KH0004', N'Nguyễn Thị D', 'jkl@gmail.com', '0912345681', '049204013505'),
('KH0005', N'Lê Văn E', 'mno@gmail.com', '0912345682', '049204013506');
-- Thêm 10 khách hàng
INSERT INTO KhachHang (maKH, tenKH, email, sdt, cccd) VALUES
('KH0006', N'Hoàng Văn F', 'hoangf@gmail.com', '0912345683', '049204013507'),
('KH0007', N'Bùi Thị G', 'buig@gmail.com', '0912345684', '049204013508'),
('KH0008', N'Ngô Văn H', 'ngoh@gmail.com', '0912345685', '049204013509'),
('KH0009', N'Trịnh Thị I', 'trinhi@gmail.com', '0912345686', '049204013510'),
('KH0010', N'Lý Văn J', 'lyj@gmail.com', '0912345687', '049204013511'),
('KH0011', N'Vũ Thị K', 'vuk@gmail.com', '0912345688', '049204013512'),
('KH0012', N'Doãn Văn L', 'doanl@gmail.com', '0912345689', '049204013513'),
('KH0013', N'Tô Thị M', 'tom@gmail.com', '0912345690', '049204013514'),
('KH0014', N'Đặng Văn N', 'dangn@gmail.com', '0912345691', '049204013515'),
('KH0015', N'Hồ Thị O', 'ho@gmail.com', '0912345692', '049204013516');

-- Thêm hóa đơn cho từng khách hàng
INSERT INTO HoaDon (maHoaDon, ngayLapHoaDon, nhanVien, khachHang, daHoanVe, daHoanTien) VALUES
('121224NV00100001', '2025-12-12', 'NV001', 'KH0006', 0, 0),
('121224NV00100002', '2025-12-12', 'NV001', 'KH0007', 0, 0),
('121224NV00100003', '2025-12-12', 'NV001', 'KH0008', 0, 0),
('121224NV00100004', '2025-12-12', 'NV001', 'KH0009', 0, 0),
('121224NV00100005', '2025-12-12', 'NV001', 'KH0010', 0, 0),
('121224NV00100006', '2025-12-12', 'NV001', 'KH0011', 0, 0),
('121224NV00100007', '2025-12-12', 'NV001', 'KH0012', 0, 0),
('121224NV00100008', '2025-12-12', 'NV001', 'KH0013', 0, 0),
('121224NV00100009', '2025-12-12', 'NV001', 'KH0014', 0, 0),
('121224NV00100010', '2025-12-12', 'NV001', 'KH0015', 0, 0);

-- Thêm chi tiết hóa đơn
INSERT INTO ChiTietHoaDon (maChiTiet, hoaDon, soLuong, thue) VALUES
('CT121224NV00100001', '121224NV00100001', 1, 0.1),
('CT121224NV00100002', '121224NV00100002', 1, 0.1),
('CT121224NV00100003', '121224NV00100003', 1, 0.1),
('CT121224NV00100004', '121224NV00100004', 1, 0.1),
('CT121224NV00100005', '121224NV00100005', 1, 0.1),
('CT121224NV00100006', '121224NV00100006', 1, 0.1),
('CT121224NV00100007', '121224NV00100007', 1, 0.1),
('CT121224NV00100008', '121224NV00100008', 1, 0.1),
('CT121224NV00100009', '121224NV00100009', 1, 0.1),
('CT121224NV00100010', '121224NV00100010', 1, 0.1);

-- Thêm vé cho từng chi tiết hóa đơn
INSERT INTO Ve (maVe, tau, toa, soGhe, khachHang, ngayDi, gioDi, ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai, chiTiet) VALUES
('VE1212240007', 'TA001', 'TA001_01', 1, 'KH0006', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100001'),
('VE1212240008', 'TA001', 'TA001_01', 2, 'KH0007', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100002'),
('VE1212240009', 'TA001', 'TA001_01', 3, 'KH0008', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100003'),
('VE1212240010', 'TA001', 'TA001_01', 4, 'KH0009', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100004'),
('VE1212240011', 'TA001', 'TA001_01', 5, 'KH0010', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100005'),
('VE1212240012', 'TA001', 'TA001_01', 6, 'KH0011', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100006'),
('VE1212240013', 'TA001', 'TA001_01', 7, 'KH0012', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100007'),
('VE1212240014', 'TA001', 'TA001_01', 8, 'KH0013', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100008'),
('VE1212240015', 'TA001', 'TA001_01', 9, 'KH0014', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100009'),
('VE1212240016', 'TA001', 'TA001_01', 10, 'KH0015', '2025-12-21', '06:00:00', '2025-12-21', '18:00:00', 'GA001', 'GA173', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00100010');

-- Cập nhật trạng thái ghế của 10 ghế
UPDATE Ghe
SET trangThai = 0
WHERE maToa = 'TA001_01' AND soGhe IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


-- Thêm 5 khách hàng mới
INSERT INTO KhachHang (maKH, tenKH, email, sdt, cccd) VALUES
('KH0016', N'Nguyễn Văn P', 'nguyenp@gmail.com', '0912345693', '049204013517'),
('KH0017', N'Lê Thị Q', 'leq@gmail.com', '0912345694', '049204013518'),
('KH0018', N'Phạm Văn R', 'phamr@gmail.com', '0912345695', '049204013519'),
('KH0019', N'Trần Thị S', 'trans@gmail.com', '0912345696', '049204013520'),
('KH0020', N'Võ Văn T', 'vot@gmail.com', '0912345697', '049204013521');
-- Thêm hóa đơn cho 5 khách hàng
INSERT INTO HoaDon (maHoaDon, ngayLapHoaDon, nhanVien, khachHang, daHoanVe, daHoanTien) VALUES
('121224NV00200012', '2025-12-12', 'NV002', 'KH0016', 0, 0),
('121224NV00200013', '2025-12-12', 'NV002', 'KH0017', 0, 0),
('121224NV00200014', '2025-12-12', 'NV002', 'KH0018', 0, 0),
('121224NV00200015', '2025-12-12', 'NV002', 'KH0019', 0, 0),
('121224NV00200016', '2025-12-12', 'NV002', 'KH0020', 0, 0);
-- Thêm chi tiết hóa đơn và vé
INSERT INTO ChiTietHoaDon (maChiTiet, hoaDon, soLuong, thue) VALUES
('CT121224NV00200012', '121224NV00200012', 2, 0.1), -- KH0016, 2 vé
('CT121224NV00200013', '121224NV00200013', 2, 0.1), -- KH0017, 2 vé
('CT121224NV00200014', '121224NV00200014', 1, 0.1), -- KH0018, 1 vé
('CT121224NV00200015', '121224NV00200015', 1, 0.1), -- KH0019, 1 vé
('CT121224NV00200016', '121224NV00200016', 2, 0.1); -- KH0020, 2 vé

INSERT INTO Ve (maVe, tau, toa, soGhe, khachHang, ngayDi, gioDi, ngayDen, gioDen, gaDi, gaDen, hang, khuyenMai, trangThai, chiTiet) VALUES
-- KH0016: 2 vé
('VE1212240018', 'TA003', 'TA003_01', 1, 'KH0016', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200012'),
('VE1212240019', 'TA003', 'TA003_01', 2, 'KH0016', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200012'),

-- KH0017: 2 vé
('VE1212240020', 'TA003', 'TA003_01', 3, 'KH0017', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200013'),
('VE1212240021', 'TA003', 'TA003_01', 4, 'KH0017', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200013'),

-- KH0018: 1 vé
('VE1212240022', 'TA003', 'TA003_01', 5, 'KH0018', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200014'),

-- KH0019: 1 vé
('VE1212240023', 'TA003', 'TA003_01', 6, 'KH0019', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200015'),

-- KH0020: 2 vé
('VE1212240024', 'TA003', 'TA003_01', 7, 'KH0020', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200016'),
('VE1212240025', 'TA003', 'TA003_01', 8, 'KH0020', '2025-12-21', '08:00:00', '2025-12-21', '20:00:00', 'GA001', 'GA045', N'Giường nằm', N'Người lớn', 0, 'CT121224NV00200016');

-- Cập nhật trạng thái ghế đã đặt
UPDATE Ghe
SET trangThai = 0
WHERE maToa = 'TA003_01' AND soGhe IN (1, 2, 3, 4, 5, 6, 7, 8);



--Trả vé--
-- Cập nhật trạng thái vé VE1212240024
UPDATE Ve
SET trangThai = 1
WHERE maVe = 'VE1212240024';

-- Cập nhật trạng thái vé VE1212240025
UPDATE Ve
SET trangThai = 1
WHERE maVe = 'VE1212240025';

-- Cập nhật trạng thái ghế cho vé VE1212240024 (Ghế số 7 trong toa TA003_01)
UPDATE Ghe
SET trangThai = 0
WHERE maToa = 'TA003_01' AND soGhe = 7;

-- Cập nhật trạng thái ghế cho vé VE1212240025 (Ghế số 8 trong toa TA003_01)
UPDATE Ghe
SET trangThai = 0
WHERE maToa = 'TA003_01' AND soGhe = 8;

-- Cập nhật trạng thái daHoanTien và daHoanVe của hóa đơn liên quan đến vé VE1212240024
UPDATE HoaDon
SET daHoanTien = 1, daHoanVe = 1
WHERE maHoaDon = '121224NV00200016';

-- Cập nhật trạng thái daHoanTien và daHoanVe của hóa đơn liên quan đến vé VE1212240025
UPDATE HoaDon
SET daHoanTien = 1, daHoanVe = 1
WHERE maHoaDon = '121224NV00200016';

