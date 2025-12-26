// ThuPhiDAO.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ThuPhiDetails;
import util.JDBCUtil;

public class ThuPhiDAO {
    
    public void insert(String maPhi) throws SQLException {
        NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();
        List<String> list = nhanKhauDAO.getAllCccd();

        String sql = "INSERT INTO ThuPhi (MaPhi, cccd, SoTien) VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (String cccd : list) {
                    pstmt.setString(1, maPhi);
                    pstmt.setString(2, cccd);
                    pstmt.setInt(3, 0);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    public List<ThuPhiDetails> getByMaPhi(String maPhi) throws SQLException {
        List<ThuPhiDetails> list = new ArrayList<>();
        String sql = "SELECT tp.cccd, nk.hoTen, COALESCE(hk.soHoKhau, '') AS soHoKhau, COALESCE(hk.diaChi, ttr.noiTamTru) AS diaChi, tp.SoTien " +
                     "FROM ThuPhi tp " +
                     "JOIN NhanKhau nk ON tp.cccd = nk.Cccd " +
                     "LEFT JOIN ThuongTru ttu ON tp.cccd = ttu.cccd " +
                     "LEFT JOIN HoKhau hk ON ttu.soHoKhau = hk.soHoKhau " +
                     "LEFT JOIN TamTru ttr ON tp.cccd = ttr.cccd " +
                     "WHERE tp.MaPhi = ?";
        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhi);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ThuPhiDetails d = new ThuPhiDetails();
                    d.setSoHoKhau(rs.getString("soHoKhau"));
                    d.setCCCD(rs.getString("cccd"));
                    d.setHoTen(rs.getString("hoTen"));
                    d.setDiaChi(rs.getString("diaChi"));
                    d.setSoTien(rs.getInt("SoTien"));
                    list.add(d);
                }
            }
        }
        return list;
    }

    public List<ThuPhiDetails> searchByMaPhi(String maPhi, String keyword) throws SQLException {
        List<ThuPhiDetails> list = new ArrayList<>();
        String sql = "SELECT tp.cccd, nk.hoTen, COALESCE(hk.soHoKhau, '') AS soHoKhau, COALESCE(hk.diaChi, ttr.noiTamTru) AS diaChi, tp.SoTien " +
                     "FROM ThuPhi tp " +
                     "JOIN NhanKhau nk ON tp.cccd = nk.Cccd " +
                     "LEFT JOIN ThuongTru ttu ON tp.cccd = ttu.cccd " +
                     "LEFT JOIN HoKhau hk ON ttu.soHoKhau = hk.soHoKhau " +
                     "LEFT JOIN TamTru ttr ON tp.cccd = ttr.cccd " +
                     "WHERE tp.MaPhi = ? AND (COALESCE(hk.soHoKhau, '') LIKE ? OR tp.cccd LIKE ? OR nk.hoTen LIKE ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, maPhi);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            pstmt.setString(4, searchTerm);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ThuPhiDetails d = new ThuPhiDetails();
                    d.setSoHoKhau(rs.getString("soHoKhau"));
                    d.setCCCD(rs.getString("cccd"));
                    d.setHoTen(rs.getString("hoTen"));
                    d.setDiaChi(rs.getString("diaChi"));
                    d.setSoTien(rs.getInt("SoTien"));
                    list.add(d);
                }
            }
        }
        return list;
    }

    public int getTongThu(String maPhi) throws SQLException {
        String sql = "SELECT SUM(SoTien) FROM ThuPhi WHERE MaPhi = ?";
        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhi);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public void update(String maPhi, String cccd, int soTien) throws SQLException {
        String sql = "UPDATE ThuPhi SET soTien = ? WHERE maPhi = ? AND cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, soTien);
            pstmt.setString(2, maPhi);
            pstmt.setString(3, cccd);
            pstmt.executeUpdate();
        }
    }
}