// NhanKhauDAO.java
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanKhau;
import model.Phi;
import util.JDBCUtil;

public class NhanKhauDAO {

    public void insert(NhanKhau nhanKhau) throws SQLException {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            String sqlNhanKhau = "INSERT INTO NhanKhau (Cccd, hoTen, gioiTinh, biDanh, ngaySinh, queQuan, danToc, tonGiao, ngheNghiep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtNhanKhau = conn.prepareStatement(sqlNhanKhau)) {
                pstmtNhanKhau.setString(1, nhanKhau.getCccd());
                pstmtNhanKhau.setString(2, nhanKhau.getHoTen());
                pstmtNhanKhau.setString(3, nhanKhau.getGioiTinh());
                pstmtNhanKhau.setString(4, nhanKhau.getBiDanh());
                pstmtNhanKhau.setDate(5, new Date(nhanKhau.getNgaySinh().getTime()));
                pstmtNhanKhau.setString(6, nhanKhau.getQueQuan());
                pstmtNhanKhau.setString(7, nhanKhau.getDanToc());
                pstmtNhanKhau.setString(8, nhanKhau.getTonGiao());
                pstmtNhanKhau.setString(9, nhanKhau.getNgheNghiep());
                pstmtNhanKhau.executeUpdate();
            }

            PhiDAO phiDAO = new PhiDAO();
            List<Phi> allPhi = phiDAO.getAll();

            if (!allPhi.isEmpty()) {
                String sqlThuPhi = "INSERT INTO ThuPhi (MaPhi, cccd, SoTien) VALUES (?, ?, 0)";
                try (PreparedStatement pstmtThuPhi = conn.prepareStatement(sqlThuPhi)) {
                    for (Phi phi : allPhi) {
                        pstmtThuPhi.setString(1, phi.getMaPhi());
                        pstmtThuPhi.setString(2, nhanKhau.getCccd());
                        pstmtThuPhi.addBatch();
                    }
                    pstmtThuPhi.executeBatch();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    // Log or handle rollback exception
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public List<NhanKhau> getAll() throws SQLException {
        List<NhanKhau> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanKhau";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NhanKhau nk = new NhanKhau();
                nk.setCccd(rs.getString("Cccd"));
                nk.setHoTen(rs.getString("hoTen"));
                nk.setGioiTinh(rs.getString("gioiTinh"));
                nk.setBiDanh(rs.getString("biDanh"));
                nk.setNgaySinh(rs.getDate("ngaySinh"));
                nk.setQueQuan(rs.getString("queQuan"));
                nk.setDanToc(rs.getString("danToc"));
                nk.setTonGiao(rs.getString("tonGiao"));
                nk.setNgheNghiep(rs.getString("ngheNghiep"));
                list.add(nk);
            }
        }
        return list;
    }

    // Lấy nhân khẩu chưa thuộc hộ nào (không có trong ThuongTru, TamTru, TamVang)
    public List<NhanKhau> getNhanKhauChuaCoHo() throws SQLException {
        List<NhanKhau> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanKhau nk WHERE NOT EXISTS (SELECT 1 FROM ThuongTru tt WHERE tt.cccd = nk.Cccd) " +
                     "AND NOT EXISTS (SELECT 1 FROM TamTru t WHERE t.cccd = nk.Cccd) " +
                     "AND NOT EXISTS (SELECT 1 FROM TamVang tv WHERE tv.cccd = nk.Cccd)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NhanKhau nk = new NhanKhau();
                nk.setCccd(rs.getString("Cccd"));
                nk.setHoTen(rs.getString("hoTen"));
                nk.setGioiTinh(rs.getString("gioiTinh"));
                nk.setBiDanh(rs.getString("biDanh"));
                nk.setNgaySinh(rs.getDate("ngaySinh"));
                nk.setQueQuan(rs.getString("queQuan"));
                nk.setDanToc(rs.getString("danToc"));
                nk.setTonGiao(rs.getString("tonGiao"));
                nk.setNgheNghiep(rs.getString("ngheNghiep"));
                list.add(nk);
            }
        }
        return list;
    }
    
    public List<String> getAllCccd() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT Cccd FROM NhanKhau";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("Cccd"));
            }
        }
        return list;
    }
    
    public boolean checkIfExists(String cccd) throws SQLException {
        String sql = "SELECT COUNT(*) FROM NhanKhau WHERE Cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cccd);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public boolean hasAssociations(Connection conn, String cccd) throws SQLException {
        String sql = """
            SELECT 1
            WHERE EXISTS (SELECT 1 FROM ThuongTru WHERE cccd = ?)
               OR EXISTS (SELECT 1 FROM TamTru WHERE cccd = ?)
               OR EXISTS (SELECT 1 FROM TamVang WHERE cccd = ?)
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cccd);
            pstmt.setString(2, cccd);
            pstmt.setString(3, cccd);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // chỉ cần tồn tại 1 dòng
            }
        }
    }

    public void delete(String cccd) throws SQLException {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            String sqlThuPhi = "DELETE FROM ThuPhi WHERE cccd = ?";
            try (PreparedStatement pstmtThuPhi = conn.prepareStatement(sqlThuPhi)) {
                pstmtThuPhi.setString(1, cccd);
                pstmtThuPhi.executeUpdate();
            }

            String sqlNhanKhau = "DELETE FROM NhanKhau WHERE cccd = ?";
            try (PreparedStatement pstmtNhanKhau = conn.prepareStatement(sqlNhanKhau)) {
                pstmtNhanKhau.setString(1, cccd);
                pstmtNhanKhau.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    // Log or handle rollback exception
                }
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