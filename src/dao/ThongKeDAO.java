// ThongKeDAO.java
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import util.JDBCUtil;

public class ThongKeDAO {

    public Map<String, Integer> getGioiTinhCount(Date from, Date to) throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT gioiTinh, COUNT(*) AS count FROM NhanKhau nk JOIN ThuongTru tt ON nk.Cccd = tt.cccd " +
                     "WHERE ngayChuyenDen BETWEEN ? AND ? GROUP BY gioiTinh";
        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, from);
            pstmt.setDate(2, to);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    map.put(rs.getString("gioiTinh"), rs.getInt("count"));
                }
            }
        }
        return map;
    }

    public Map<String, Integer> getDoTuoiCount(Date from, Date to) throws SQLException {
        Map<String, Integer> map = new HashMap<>();

        String sql = """
            SELECT
                CASE
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 0 AND 3 THEN N'Mầm non'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 4 AND 6 THEN N'Mẫu giáo'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 7 AND 11 THEN N'Cấp 1'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 12 AND 15 THEN N'Cấp 2'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 16 AND 18 THEN N'Cấp 3'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 19 AND 60 THEN N'Lao động'
                    ELSE N'Nghỉ hưu'
                END AS nhomTuoi,
                COUNT(*) AS count
            FROM NhanKhau nk
            JOIN ThuongTru tt ON nk.cccd = tt.cccd
            WHERE tt.ngayChuyenDen BETWEEN ? AND ?
            GROUP BY
                CASE
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 0 AND 3 THEN N'Mầm non'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 4 AND 6 THEN N'Mẫu giáo'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 7 AND 11 THEN N'Cấp 1'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 12 AND 15 THEN N'Cấp 2'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 16 AND 18 THEN N'Cấp 3'
                    WHEN DATEDIFF(YEAR, nk.ngaySinh, GETDATE()) BETWEEN 19 AND 60 THEN N'Lao động'
                    ELSE N'Nghỉ hưu'
                END
            """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, from);
            pstmt.setDate(2, to);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    map.put(rs.getString("nhomTuoi"), rs.getInt("count"));
                }
            }
        }
        return map;
    }

    public Map<String, Integer> getCuTruCount(Date from, Date to) throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        // Thường trú: COUNT ThuongTru
        String sqlThuong = "SELECT COUNT(*) FROM ThuongTru WHERE ngayChuyenDen BETWEEN ? AND ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlThuong)) {
            pstmt.setDate(1, from);
            pstmt.setDate(2, to);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    map.put("Thường trú", rs.getInt(1));
                }
            }
        }

        // Tạm trú: COUNT TamTru
        String sqlTamTru = "SELECT COUNT(*) FROM TamTru WHERE ngayBatDau BETWEEN ? AND ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlTamTru)) {
            pstmt.setDate(1, from);
            pstmt.setDate(2, to);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    map.put("Tạm trú", rs.getInt(1));
                }
            }
        }

        // Tạm vắng: COUNT TamVang
        String sqlTamVang = "SELECT COUNT(*) FROM TamVang WHERE ngayBatDau BETWEEN ? AND ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlTamVang)) {
            pstmt.setDate(1, from);
            pstmt.setDate(2, to);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    map.put("Tạm vắng", rs.getInt(1));
                }
            }
        }
        return map;
    }

    public int getTongHoKhau() throws SQLException {
        String sql = "SELECT COUNT(*) FROM HoKhau";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTongThuongTru() throws SQLException {
        String sql = "SELECT COUNT(*) FROM ThuongTru";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTongTamTru() throws SQLException {
        String sql = "SELECT COUNT(*) FROM TamTru";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTongTamVang() throws SQLException {
        String sql = "SELECT COUNT(*) FROM TamVang";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}