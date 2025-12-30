package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        // Thông tin kết nối
        String url = "jdbc:sqlserver://SHERRY:1433;databaseName=du_lieu_cu_tru;encrypt=false;trustServerCertificate=true;";
        String user = "sa";        // tên user SQL Server
        String password = "123456"; // mật khẩu SQL Server
        
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    
    public static void createDatabaseIfNotExists() throws SQLException {
        String dbName = "du_lieu_cu_tru";
        String url = "jdbc:sqlserver://SHERRY:1433;encrypt=false;trustServerCertificate=true;";
        String user = "sa";
        String password = "123456";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT name FROM sys.databases WHERE name = '" + dbName + "'");
            if (!rs.next()) {
                stmt.executeUpdate("CREATE DATABASE " + dbName);
            }
        }
    }
    
    public static void createTablesIfNotExists() throws SQLException {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            
            // Check and create CanBo table
            if (!tableExists(connection, "CanBo")) {
                stmt.executeUpdate("CREATE TABLE CanBo (" +
                    "taiKhoan VARCHAR(100) PRIMARY KEY, " +
                    "matKhau VARCHAR(100) NOT NULL, " +
                    "tenCanBo NVARCHAR(50) NOT NULL, " +
                    "chucVu NVARCHAR(20) NOT NULL CHECK (chucVu IN (N'Tổ trưởng', N'Tổ phó', N'Kế toán'))" +
                ")");
            }
            
            // Check and create HoKhau table
            if (!tableExists(connection, "HoKhau")) {
                stmt.executeUpdate("CREATE TABLE HoKhau (" +
                    "soHoKhau VARCHAR(6) PRIMARY KEY, " +
                    "diaChi NVARCHAR(200) NOT NULL UNIQUE" +
                ")");
            }
            
            // Check and create NhanKhau table
            if (!tableExists(connection, "NhanKhau")) {
                stmt.executeUpdate("CREATE TABLE NhanKhau (" +
                    "Cccd VARCHAR(12) PRIMARY KEY, " +
                    "hoTen NVARCHAR(50) NOT NULL, " +
                    "gioiTinh NVARCHAR(3) NOT NULL CHECK (gioiTinh IN (N'Nam', N'Nữ')), " +
                    "biDanh NVARCHAR(50) NULL, " +
                    "ngaySinh DATE NOT NULL CHECK (ngaySinh <= GETDATE()), " +
                    "queQuan NVARCHAR(50) NOT NULL, " +
                    "danToc NVARCHAR(20) NOT NULL, " +
                    "tonGiao NVARCHAR(30) NULL, " +
                    "ngheNghiep NVARCHAR(50) NULL" +
                ")");
            }
            
            // Check and create ThuongTru table
            if (!tableExists(connection, "ThuongTru")) {
                stmt.executeUpdate("CREATE TABLE ThuongTru (" +
                    "soHoKhau VARCHAR(6) NOT NULL, " +
                    "cccd VARCHAR(12) NOT NULL, " +
                    "QuanHeChuHo NVARCHAR(30) NOT NULL, " +
                    "ngayChuyenDen DATE NOT NULL CHECK (ngayChuyenDen <= GETDATE()), " +
                    "noiTruocKhiChuyenDen NVARCHAR(200) NOT NULL, " +
                    "ngayChuyenDi DATE NULL, " +
                    "noiChuyenDi NVARCHAR(200) NULL, " +
                    "PRIMARY KEY (soHoKhau, cccd), " +
                    "FOREIGN KEY (soHoKhau) REFERENCES HoKhau(soHoKhau) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (cccd) REFERENCES NhanKhau(Cccd) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "CONSTRAINT CK_NgayChuyenDi CHECK (ngayChuyenDi IS NULL OR (ngayChuyenDi >= ngayChuyenDen AND ngayChuyenDi <= GETDATE()))" +
                ")");
            }
            
            // Check and create TamVang table
            if (!tableExists(connection, "TamVang")) {
                stmt.executeUpdate("CREATE TABLE TamVang (" +
                    "maTamVang VARCHAR(6) NOT NULL, " +
                    "cccd VARCHAR(12) NOT NULL, " +
                    "noiTamVang NVARCHAR(200) NOT NULL, " +
                    "lyDo NVARCHAR(200) NOT NULL, " +
                    "ngayBatDau DATE NOT NULL, " +
                    "ngayKetThuc DATE NOT NULL, " +
                    "PRIMARY KEY (maTamVang, cccd), " +
                    "FOREIGN KEY (cccd) REFERENCES NhanKhau(Cccd) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "CONSTRAINT CK_NgayBatDau_TamVang CHECK (ngayBatDau <= GETDATE()), " +
                    "CONSTRAINT CK_NgayKetThuc_TamVang CHECK (ngayKetThuc > ngayBatDau)" +
                ")");
            }
            
            // Check and create TamTru table
            if (!tableExists(connection, "TamTru")) {
                stmt.executeUpdate("CREATE TABLE TamTru (" +
                    "maTamTru VARCHAR(6) NOT NULL, " +
                    "cccd VARCHAR(12) NOT NULL, " +
                    "noiTamTru NVARCHAR(200) NOT NULL, " +
                    "lyDo NVARCHAR(200) NOT NULL, " +
                    "ngayBatDau DATE NOT NULL, " +
                    "ngayKetThuc DATE NOT NULL, " +
                    "PRIMARY KEY (maTamTru, cccd), " +
                    "FOREIGN KEY (cccd) REFERENCES NhanKhau(Cccd) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "CONSTRAINT CK_NgayBatDau_TamTru CHECK (ngayBatDau <= GETDATE()), " +
                    "CONSTRAINT CK_NgayKetThuc_TamTru CHECK (ngayKetThuc > ngayBatDau)" +
                ")");
            }
            
            // Check and create Phi table
            if (!tableExists(connection, "Phi")) {
                stmt.executeUpdate("CREATE TABLE Phi (" +
                    "MaPhi VARCHAR(4) PRIMARY KEY, " +
                    "TenPhi NVARCHAR(100) NOT NULL, " +
                    "LoaiPhi NVARCHAR(20) NOT NULL CHECK (LoaiPhi IN (N'Bắt buộc', N'Không bắt buộc')), " +
                    "MucThu INT NULL, " +
                    "CONSTRAINT CK_MucThu CHECK ((LoaiPhi = N'Bắt buộc' AND MucThu IS NOT NULL) OR (LoaiPhi = N'Không bắt buộc'))" +
                ")");
            }
            
            // Check and create ThuPhi table
            if (!tableExists(connection, "ThuPhi")) {
                stmt.executeUpdate("CREATE TABLE ThuPhi (" +
                    "MaPhi VARCHAR(4) NOT NULL, " +
                    "cccd VARCHAR(12) NOT NULL, " +
                    "SoTien INT NOT NULL, " +
                    "PRIMARY KEY (MaPhi, cccd), " +
                    "FOREIGN KEY (MaPhi) REFERENCES Phi(MaPhi) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (cccd) REFERENCES NhanKhau(Cccd) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");
            }
        }
    }
    
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }
}