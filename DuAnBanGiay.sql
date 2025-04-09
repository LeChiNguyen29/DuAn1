CREATE DATABASE DuAnBanGiay
GO

USE DuAnBanGiay
GO

CREATE TABLE NhanVien(
	id int IDENTITY(1,1) PRIMARY KEY,
	maNhanVien varchar(10) NOT NULL,
	hoTen nvarchar(50) NOT NULL,
	gioiTinh nvarchar(5) NOT NULL,
	ngaySinh date NOT NULL,
	diaChi ntext,
	soCCCD varchar(15) NOT NULL,
	soDienThoai varchar(12) NOT NULL,
	matKhau varchar(15) NOT NULL,
	vaiTro nvarchar(50) NOT NULL,
	ghiChu nvarchar(50),
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE KhachHang(
	id int IDENTITY(1,1) PRIMARY KEY,
	maKhachHang varchar(10) NOT NULL,
	hoTen nvarchar(50) NOT NULL,
	soDienThoai varchar(12) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE Voucher(
	id int IDENTITY(1,1) PRIMARY KEY,
	maVoucher varchar(10) NOT NULL,
	tenVoucher nvarchar(50) NOT NULL,
	ngayBatDau date NOT NULL,
	ngayKetThuc date NOT NULL,
	loaiVoucher nvarchar(5),
	giaTri decimal(10,0) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE MauSac(
	id int IDENTITY(1,1) PRIMARY KEY,
	maMauSac varchar(10) NOT NULL,
	tenMau nvarchar(50) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE NhaSanXuat(
	id int IDENTITY(1,1) PRIMARY KEY,
	maNhaSanXuat varchar(10) NOT NULL,
	tenNhaSanXuat nvarchar(50) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE DanhMuc(
	id int IDENTITY(1,1) PRIMARY KEY,
	maDanhMuc varchar(10) NOT NULL,
	tenDanhMuc nvarchar(50) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE ChatLieu(
	id int IDENTITY(1,1) PRIMARY KEY,
	maChatLieu varchar(10) NOT NULL,
	tenChatLieu nvarchar(50) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE KichCo(
	id int IDENTITY(1,1) PRIMARY KEY,
	maKichCo varchar(10) NOT NULL,
	size int NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE SanPham(
	id int IDENTITY(1,1) PRIMARY KEY,
	maSanPham varchar(10) NOT NULL,
	tenSanPham nvarchar(50) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL
)
GO

CREATE TABLE HoaDon(
	id int IDENTITY(1,1) PRIMARY KEY,
	maHoaDon varchar(10) NOT NULL,
	loaiThanhToan nvarchar(50), 
	tongTien decimal(15,0),
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL,
	idKhachHang int,
	idNhanVien int,
	idVoucher int,
	FOREIGN KEY(idKhachHang) REFERENCES KhachHang(id),
	FOREIGN KEY(idNhanVien) REFERENCES NhanVien(id),
	FOREIGN KEY(idVoucher) REFERENCES Voucher(id)
)
GO

CREATE TABLE SanPhamChiTiet(
	id int IDENTITY(1,1) PRIMARY KEY,
	maSanPhamChiTiet varchar(10) NOT NULL,
	anhSanPham text,
	giaSanPham decimal NOT NULL,
	soLuong int NOT NULL,
	moTa ntext,
	idChatLieu int NOT NULL,
	idDanhMuc int NOT NULL,
	idKichCo int NOT NULL,
	idMauSac int NOT NULL,
	idNhaSanXuat int NOT NULL,
	idSanPham int NOT NULL,
	trangThai int NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	FOREIGN KEY(idChatLieu) REFERENCES ChatLieu(id),
	FOREIGN KEY(idDanhMuc) REFERENCES DanhMuc(id),
	FOREIGN KEY(idKichCo) REFERENCES KichCo(id),
	FOREIGN KEY(idMauSac) REFERENCES MauSac(id),
	FOREIGN KEY(idNhaSanXuat) REFERENCES NhaSanXuat(id),
	FOREIGN KEY(idSanPham) REFERENCES SanPham(id)
)
GO

CREATE TABLE HoaDonChiTiet(
	id int IDENTITY(1,1) PRIMARY KEY,
	maHoaDonChiTiet varchar(10) NOT NULL,
	soLuong int NOT NULL,
	tongTien decimal(15,0) NOT NULL,
	ngayTao datetime,
	ngaySua datetime,
	trangThai int NOT NULL,
	idHoaDon int NOT NULL,
	idSanPhamChiTiet int NOT NULL,
	FOREIGN KEY(idHoaDon) REFERENCES HoaDon(id),
	FOREIGN KEY(idSanPhamChiTiet) REFERENCES SanPhamChiTiet(id)
)
GO

-- INSERT INTO

INSERT INTO NhanVien 
    (maNhanVien, hoTen, gioiTinh, ngaySinh, diaChi, soCCCD, soDienThoai, matKhau, vaiTro, ghiChu, trangThai, ngayTao)
VALUES 
    ('NV001', N'Nguyễn Văn Cao', N'Nam', '1985-01-01', N'Hà Nội', '123456789012', '0987654321', 'matkhau1', N'Quản Lý', N'Chịu trách nhiệm quản lý cửa hàng', 0, GETDATE()),
    ('NV002', N'Lê Thị Trang', N'Nữ', '1990-02-10', N'TP. Hồ Chí Minh', '987654321098', '0123456789', 'matkhau2', N'Nhân Viên Bán Hàng', N'Chăm sóc khách hàng và tư vấn sản phẩm', 0, GETDATE()),
    ('NV003', N'Trần Văn An', N'Nam', '1988-03-20', N'Hải Phòng', '234567890123', '0912345678', 'matkhau3', N'Nhân Viên Bán Hàng', N'Tư vấn và bán sản phẩm giày', 0, GETDATE()),
    ('NV004', N'Phạm Thị Bình', N'Nữ', '1992-04-30', N'Đà Nẵng', '345678901234', '0923456789', 'matkhau4', N'Quản Lý Kho', N'Quản lý kho hàng và kiểm kê', 0, GETDATE()),
    ('NV005', N'Ngô Văn Cường', N'Nam', '1989-05-15', N'Quảng Ninh', '456789012345', '0934567890', 'matkhau5', N'Nhân Viên Giao Hàng', N'Giao hàng cho khách hàng', 0, GETDATE()),
    ('NV006', N'Bùi Thị Dung', N'Nữ', '1991-06-25', N'Bình Dương', '567890123456', '0945678901', 'matkhau6', N'Nhân Viên Thu Ngân', N'Thực hiện các giao dịch thanh toán', 0, GETDATE()),
    ('NV007', N'Vũ Văn Em', N'Nam', '1987-07-10', N'Cần Thơ', '678901234567', '0956789012', 'matkhau7', N'Nhân Viên Kỹ Thuật', N'Bảo trì và sửa chữa thiết bị', 0, GETDATE()),
    ('NV008', N'Đỗ Thị Phương', N'Nữ', '1993-08-20', N'Nghệ An', '789012345678', '0967890123', 'matkhau8', N'Nhân Viên Kế Toán', N'Quản lý tài chính và kế toán', 0, GETDATE()),
    ('NV009', N'Hồ Văn Giang', N'Nam', '1994-09-05', N'Kiên Giang', '890123456789', '0978901234', 'matkhau9', N'Nhân Viên Chăm Sóc Khách Hàng', N'Hỗ trợ khách hàng và giải đáp thắc mắc', 0, GETDATE()),
	('NV010', N'Nguyễn Thị Hạnh', N'Nữ', '1986-10-10', N'Khánh Hòa', '901234567890', '0989012345', 'matkhau10', N'Nhân Viên Marketing', N'Thực hiện các chiến dịch quảng cáo', 0, GETDATE()),
    ('NV011', N'Phạm Văn Khoa', N'Nam', '1985-11-15', N'Phú Yên', '012345678901', '0990123456', 'matkhau11', N'Nhân Viên Kho', N'Quản lý kho và xuất nhập hàng hóa', 0, GETDATE()),
    ('NV012', N'Trần Thị Lan', N'Nữ', '1990-12-20', N'Hà Tĩnh', '123456780123', '0901234561', 'matkhau12', N'Nhân Viên IT', N'Hỗ trợ kỹ thuật và bảo mật hệ thống', 0, GETDATE()),
    ('NV013', N'Nguyễn Văn Minh', N'Nam', '1992-01-01', N'Bình Thuận', '234567891234', '0912345612', 'matkhau13', N'Nhân Viên Bán Hàng Online', N'Tư vấn và bán hàng qua mạng', 0, GETDATE()),
    ('NV014', N'Lê Thị Oanh', N'Nữ', '1988-02-10', N'Long An', '345678902345', '0923456123', 'matkhau14', N'Nhân Viên Hành Chính', N'Quản lý hồ sơ và giấy tờ', 0, GETDATE()),
    ('NV015', N'Vũ Văn Phong', N'Nam', '1989-03-15', N'Hậu Giang', '456789013456', '0934561234', 'matkhau15', N'Nhân Viên Pháp Lý', N'Tư vấn và hỗ trợ pháp lý', 0, GETDATE()),
    ('NV016', N'Phan Thị Quỳnh', N'Nữ', '1991-04-20', N'Tây Ninh', '567890124567', '0945672345', 'matkhau16', N'Nhân Viên Kế Hoạch', N'Lập kế hoạch và quản lý dự án', 0, GETDATE()),
    ('NV017', N'Bùi Văn Sơn', N'Nam', '1993-05-25', N'Bình Định', '678901235678', '0956783456', 'matkhau17', N'Nhân Viên Phát Triển Kinh Doanh', N'Phát triển mối quan hệ kinh doanh', 0, GETDATE()),
    ('NV018', N'Trịnh Thị Thu', N'Nữ', '1994-06-30', N'Ninh Bình', '789012346789', '0967894567', 'matkhau18', N'Nhân Viên Tư Vấn', N'Tư vấn sản phẩm và dịch vụ', 0, GETDATE()),
    ('NV019', N'Ngô Văn Tùng', N'Nam', '1987-07-15', N'Vĩnh Phúc', '890123457890', '0978905678', 'matkhau19', N'Nhân Viên Sản Xuất', N'Quản lý sản xuất và chất lượng', 0, GETDATE()),
    ('NV020', N'Hoàng Thị Uyên', N'Nữ', '1986-08-01', N'Thái Nguyên', '901234568901', '0989016789', 'matkhau20', N'Nhân Viên Đào Tạo', N'Đào tạo và phát triển nhân viên', 0, GETDATE());


INSERT INTO KhachHang
    (maKhachHang, hoTen, soDienThoai, ngayTao, trangThai)
VALUES
 ('KH000', N'Khách Vãng Lai', '0123456789', GETDATE(), 0),
    ('KH001', N'Nguyễn Văn An', '0901234567', GETDATE(), 0),
    ('KH002', N'Nguyễn Thị Bích', '0902345678', GETDATE(), 0),
    ('KH003', N'Nguyễn Văn Cường', '0903456789', GETDATE(), 0),
    ('KH004', N'Nguyễn Thị Dung', '0904567890', GETDATE(), 0),
    ('KH005', N'Nguyễn Văn Đạt', '0905678901', GETDATE(), 0),
    ('KH006', N'Nguyễn Thị Mai', '0906789012', GETDATE(), 0),
    ('KH007', N'Nguyễn Văn Phúc', '0907890123', GETDATE(), 0),
    ('KH008', N'Nguyễn Thị Hồng', '0908901234', GETDATE(), 0),
    ('KH009', N'Nguyễn Văn Hưng', '0909012345', GETDATE(), 0),
    ('KH010', N'Nguyễn Thị Lan', '0900123456', GETDATE(), 0),
	('KH011', N'Nguyễn Văn Khoa', '0901234567', GETDATE(), 0),
    ('KH012', N'Nguyễn Thị Thanh', '0902345678', GETDATE(), 0),
    ('KH013', N'Nguyễn Văn Minh', '0903456789', GETDATE(), 0),
    ('KH014', N'Nguyễn Thị Ngọc', '0904567890', GETDATE(), 0),
    ('KH015', N'Nguyễn Văn Sơn', '0905678901', GETDATE(), 0),
    ('KH016', N'Nguyễn Thị Quyên', '0906789012', GETDATE(), 0),
    ('KH017', N'Nguyễn Văn Tuấn', '0907890123', GETDATE(), 0),
    ('KH018', N'Nguyễn Thị Uyen', '0908901234', GETDATE(), 0),
    ('KH019', N'Nguyễn Văn Vinh', '0909012345', GETDATE(), 0),
    ('KH020', N'Nguyễn Thị Xuân', '0900123456', GETDATE(), 0),
    ('KH021', N'Nguyễn Văn Lâm', '0901234568', GETDATE(), 0),
    ('KH022', N'Nguyễn Thị Kiều', '0902345679', GETDATE(), 0),
    ('KH023', N'Nguyễn Văn Hạnh', '0903456790', GETDATE(), 0),
    ('KH024', N'Nguyễn Thị Hoài', '0904567901', GETDATE(), 0),
    ('KH025', N'Nguyễn Văn Hải', '0905678012', GETDATE(), 0),
    ('KH026', N'Nguyễn Thị Lan', '0906789123', GETDATE(), 0),
    ('KH027', N'Nguyễn Văn Tiến', '0907890234', GETDATE(), 0),
    ('KH028', N'Nguyễn Thị Tâm', '0908901345', GETDATE(), 0),
    ('KH029', N'Nguyễn Văn Bình', '0909012456', GETDATE(), 0),
    ('KH030', N'Nguyễn Thị Mai', '0900123567', GETDATE(), 0),
    ('KH031', N'Nguyễn Văn Khuê', '0901234678', GETDATE(), 0),
    ('KH032', N'Nguyễn Thị Thùy', '0902345789', GETDATE(), 0),
    ('KH033', N'Nguyễn Văn Minh', '0903456890', GETDATE(), 0),
    ('KH034', N'Nguyễn Thị Bình', '0904567902', GETDATE(), 0),
    ('KH035', N'Nguyễn Văn Luân', '0905678013', GETDATE(), 0),
    ('KH036', N'Nguyễn Thị Hà', '0906789124', GETDATE(), 0),
    ('KH037', N'Nguyễn Văn Nam', '0907890235', GETDATE(), 0),
    ('KH038', N'Nguyễn Thị Hương', '0908901346', GETDATE(), 0),
    ('KH039', N'Nguyễn Văn Tùng', '0909012457', GETDATE(), 0),
    ('KH040', N'Nguyễn Thị Tình', '0900123568', GETDATE(), 0),
    ('KH041', N'Nguyễn Văn Tuấn', '0901234789', GETDATE(), 0),
    ('KH042', N'Nguyễn Thị Mến', '0902345890', GETDATE(), 0),
    ('KH043', N'Nguyễn Văn Sơn', '0903456901', GETDATE(), 0),
    ('KH044', N'Nguyễn Thị Vân', '0904567012', GETDATE(), 0),
    ('KH045', N'Nguyễn Văn Bảo', '0905678123', GETDATE(), 0),
    ('KH046', N'Nguyễn Thị Nguyệt', '0906789234', GETDATE(), 0),
    ('KH047', N'Nguyễn Văn Lộc', '0907890345', GETDATE(), 0),
    ('KH048', N'Nguyễn Thị Đào', '0908901456', GETDATE(), 0),
    ('KH049', N'Nguyễn Văn Thái', '0909012567', GETDATE(), 0),
    ('KH050', N'Nguyễn Thị Hoa', '0900123678', GETDATE(), 0);


INSERT INTO ChatLieu
 (maChatLieu, tenChatLieu, trangThai, ngayTao) 
VALUES 
 ('CL001', N'Da', 0, GETDATE()),
 ('CL002', N'Vải', 0, GETDATE()),
 ('CL003', N'Da Lộn', 0, GETDATE()),
 ('CL003', N'Cao Su', 0, GETDATE())
GO

INSERT INTO DanhMuc
 (maDanhMuc, tenDanhMuc, trangThai, ngayTao)
VALUES 
 ('LSP001', N'Giày Chạy Bộ', 0, GETDATE()),
 ('LSP002', N'Giày Bóng Rổ', 0, GETDATE()),
 ('LSP003', N'Giày Đá Banh', 0, GETDATE()),
 ('LSP004', N'Giày Đi Bộ', 0, GETDATE()),
 ('LSP005', N'Giày Quần Vợt', 0, GETDATE())
GO

INSERT INTO KichCo
 (maKichCo, size, trangThai, ngayTao)
VALUES 
 ('KC001', '36', 0, GETDATE()),
 ('KC002', '37', 0, GETDATE()),
 ('KC003', '38', 0, GETDATE()),
 ('KC004', '39', 0, GETDATE()),
 ('KC005', '40', 0, GETDATE()),
 ('KC006', '41', 0, GETDATE()),
 ('KC007', '42', 0, GETDATE()),
 ('KC008', '43', 0, GETDATE())
GO

INSERT INTO MauSac
 (maMauSac, tenMau, trangThai, ngayTao) 
VALUES 
 ('MS001', N'Xanh', 0, GETDATE()),
 ('MS002', N'Đỏ', 0, GETDATE()),
 ('MS003', N'Trắng', 0, GETDATE()),
 ('MS004', N'Vàng', 0, GETDATE()),
 ('MS005', N'Đen', 0, GETDATE()),
 ('MS006', N'Nâu', 0, GETDATE())
GO

INSERT INTO NhaSanXuat
 (maNhaSanXuat, tenNhaSanXuat, trangThai, ngayTao) 
VALUES 
 ('NSX001', N'Nike', 0, GETDATE()),
 ('NSX002', N'Adidas', 0, GETDATE()),
 ('NSX003', N'Puma', 0, GETDATE()),
 ('NSX004', N'Skechers', 0, GETDATE()),
 ('NSX005', N'Converse', 0, GETDATE()),
 ('NSX006', N'Vans', 0, GETDATE())
GO

INSERT INTO SanPham 
    (maSanPham, tenSanPham, trangThai, ngayTao)
VALUES
    ('SP001', N'Air Max 90', 0, GETDATE()),
    ('SP002', N'Ultraboost', 0, GETDATE()),
    ('SP003', N'Chuck Taylor', 0, GETDATE()),
    ('SP004', N'Suede Classic', 0, GETDATE()),
    ('SP005', N'Old Skool', 0, GETDATE()),
    ('SP006', N'New Balance 574', 0, GETDATE());

SELECT * FROM KichCo;
SELECT * FROM ChatLieu;
SELECT * FROM MauSac;
SELECT * FROM DanhMuc;
SELECT * FROM NhaSanXuat;
SELECT * FROM SanPham;
SELECT * FROM NhanVien;