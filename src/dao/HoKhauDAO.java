// HoKhauDAO.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoKhau;
import model.HoKhauWithInfo;
import util.JDBCUtil;

public class HoKhauDAO {

    public void insert(HoKhau hoKhau) throws SQLException {
        String sql = "INSERT INTO HoKhau (soHoKhau, diaChi) VALUES (?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hoKhau.getSoHoKhau());
            pstmt.setString(2, hoKhau.getDiaChi());
            pstmt.executeUpdate();
        }
    }

    public List<String> getAllSoHoKhau() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT soHoKhau FROM HoKhau";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("soHoKhau"));
            }
        }
        return list;
    }

    public String getDiaChiBySoHoKhau(String soHoKhau) throws SQLException {
        String sql = "SELECT diaChi FROM HoKhau WHERE soHoKhau = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("diaChi");
                }
            }
        }
        return null;
    }

    public String getChuHoBySoHoKhau(String soHoKhau) throws SQLException {
        String sql = "SELECT nk.hoTen FROM ThuongTru tt JOIN NhanKhau nk ON tt.cccd = nk.Cccd WHERE tt.soHoKhau = ? AND tt.QuanHeChuHo = N'Chủ hộ'";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("hoTen");
                }
            }
        }
        return null;
    }
    
    public List<HoKhauWithInfo> getAllWithInfo() throws SQLException {
        List<HoKhauWithInfo> list = new ArrayList<>();
        String sql = "SELECT hk.soHoKhau, hk.diaChi, " +
                     "(SELECT nk.hoTen FROM ThuongTru tt INNER JOIN NhanKhau nk ON tt.cccd = nk.Cccd WHERE tt.soHoKhau = hk.soHoKhau AND tt.QuanHeChuHo = N'Chủ hộ') AS tenChuHo, " +
                     "(SELECT COUNT(*) FROM ThuongTru tt WHERE tt.soHoKhau = hk.soHoKhau) AS slThanhVien " +
                     "FROM HoKhau hk";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                HoKhauWithInfo info = new HoKhauWithInfo();
                info.setSoHoKhau(rs.getString("soHoKhau"));
                info.setDiaChi(rs.getString("diaChi"));
                info.setTenChuHo(rs.getString("tenChuHo"));
                info.setSlThanhVien(rs.getInt("slThanhVien"));
                list.add(info);
            }
        }
        return list;
    }

    public List<HoKhauWithInfo> searchWithInfo(String keyword) throws SQLException {
        List<HoKhauWithInfo> list = new ArrayList<>();
        String sql = "SELECT hk.soHoKhau, hk.diaChi, " +
                     "(SELECT nk.hoTen FROM ThuongTru tt INNER JOIN NhanKhau nk ON tt.cccd = nk.Cccd WHERE tt.soHoKhau = hk.soHoKhau AND tt.QuanHeChuHo = N'Chủ hộ') AS tenChuHo, " +
                     "(SELECT COUNT(*) FROM ThuongTru tt WHERE tt.soHoKhau = hk.soHoKhau) AS slThanhVien " +
                     "FROM HoKhau hk " +
                     "WHERE hk.soHoKhau LIKE ? OR hk.diaChi LIKE ? OR " +
                     "EXISTS (SELECT 1 FROM ThuongTru tt INNER JOIN NhanKhau nk ON tt.cccd = nk.Cccd WHERE tt.soHoKhau = hk.soHoKhau AND tt.QuanHeChuHo = N'Chủ hộ' AND nk.hoTen LIKE ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HoKhauWithInfo info = new HoKhauWithInfo();
                    info.setSoHoKhau(rs.getString("soHoKhau"));
                    info.setDiaChi(rs.getString("diaChi"));
                    info.setTenChuHo(rs.getString("tenChuHo"));
                    info.setSlThanhVien(rs.getInt("slThanhVien"));
                    list.add(info);
                }
            }
        }
        return list;
    }

    public boolean canDelete(String soHoKhau) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ThuongTru WHERE soHoKhau = ? AND ngayChuyenDi IS NULL";
        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }

    public void delete(String soHoKhau) throws SQLException {
        Connection conn = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false); // bắt đầu transaction

            // 1️⃣ Lấy danh sách CCCD trong hộ khẩu
            List<String> cccds = new ArrayList<>();
            String sqlGetCccds = "SELECT cccd FROM ThuongTru WHERE soHoKhau = ?";

            try (PreparedStatement pstmtGet = conn.prepareStatement(sqlGetCccds)) {
                pstmtGet.setString(1, soHoKhau);
                try (ResultSet rs = pstmtGet.executeQuery()) {
                    while (rs.next()) {
                        cccds.add(rs.getString("cccd"));
                    }
                }
            }

            // 2️⃣ Xóa ThuongTru
            String sqlDeleteThuongTru = "DELETE FROM ThuongTru WHERE soHoKhau = ?";
            try (PreparedStatement pstmtDeleteThuongTru = conn.prepareStatement(sqlDeleteThuongTru)) {
                pstmtDeleteThuongTru.setString(1, soHoKhau);
                pstmtDeleteThuongTru.executeUpdate();
            }

            // 3️⃣ Xóa NhanKhau nếu không còn liên kết
            NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();

            String sqlDeleteNhanKhau = "DELETE FROM NhanKhau WHERE cccd = ?";
            try (PreparedStatement pstmtDeleteNk = conn.prepareStatement(sqlDeleteNhanKhau)) {

                for (String cccd : cccds) {
                    if (!nhanKhauDAO.hasAssociations(conn, cccd)) {
                        pstmtDeleteNk.setString(1, cccd);
                        pstmtDeleteNk.executeUpdate();
                    }
                }
            }

            // 4️⃣ Xóa HoKhau
            String sqlDeleteHoKhau = "DELETE FROM HoKhau WHERE soHoKhau = ?";
            try (PreparedStatement pstmtDeleteHo = conn.prepareStatement(sqlDeleteHoKhau)) {
                pstmtDeleteHo.setString(1, soHoKhau);
                pstmtDeleteHo.executeUpdate();
            }

            conn.commit(); // ✅ thành công → commit

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // ❌ lỗi → rollback toàn bộ
            }
            throw e;

        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}