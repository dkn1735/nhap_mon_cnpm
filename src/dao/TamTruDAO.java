// TamTruDAO.java
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanKhauTamTru;
import model.TamTru;
import util.JDBCUtil;

public class TamTruDAO {

    public void insert(TamTru tamTru) throws SQLException {
        String sql = "INSERT INTO TamTru (maTamTru, cccd, noiTamTru, lyDo, ngayBatDau, ngayKetThuc) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tamTru.getMaTamTru());
            pstmt.setString(2, tamTru.getCccd());
            pstmt.setString(3, tamTru.getNoiTamTru());
            pstmt.setString(4, tamTru.getLyDo());
            pstmt.setDate(5, new Date(tamTru.getNgayBatDau().getTime()));
            pstmt.setDate(6, new Date(tamTru.getNgayKetThuc().getTime()));
            pstmt.executeUpdate();
        }
    }

    public boolean checkIfExists(String cccd) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TamTru WHERE cccd = ?";
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
    
    public List<NhanKhauTamTru> getAll() throws SQLException {
        List<NhanKhauTamTru> list = new ArrayList<>();
        String sql = "SELECT nk.*, tt.maTamTru, tt.noiTamTru, tt.lyDo, tt.ngayBatDau, tt.ngayKetThuc " +
                     "FROM NhanKhau nk JOIN TamTru tt ON nk.Cccd = tt.cccd";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NhanKhauTamTru nt = new NhanKhauTamTru();
                nt.setCccd(rs.getString("Cccd"));
                nt.setHoTen(rs.getString("hoTen"));
                nt.setGioiTinh(rs.getString("gioiTinh"));
                nt.setBiDanh(rs.getString("biDanh"));
                nt.setNgaySinh(rs.getDate("ngaySinh"));
                nt.setQueQuan(rs.getString("queQuan"));
                nt.setDanToc(rs.getString("danToc"));
                nt.setTonGiao(rs.getString("tonGiao"));
                nt.setNgheNghiep(rs.getString("ngheNghiep"));
                nt.setMaTamTru(rs.getString("maTamTru"));
                nt.setNoiTamTru(rs.getString("noiTamTru"));
                nt.setLyDo(rs.getString("lyDo"));
                nt.setNgayBatDau(rs.getDate("ngayBatDau"));
                nt.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                list.add(nt);
            }
        }
        return list;
    }
    
    public List<NhanKhauTamTru> search(String keyword) throws SQLException {
        List<NhanKhauTamTru> list = new ArrayList<>();
        String sql = "SELECT nk.*, tt.maTamTru, tt.noiTamTru, tt.lyDo, tt.ngayBatDau, tt.ngayKetThuc " +
                     "FROM NhanKhau nk JOIN TamTru tt ON nk.Cccd = tt.cccd " +
                     "WHERE nk.Cccd LIKE ? OR nk.hoTen LIKE ? OR tt.maTamTru LIKE ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NhanKhauTamTru nt = new NhanKhauTamTru();
                    nt.setCccd(rs.getString("Cccd"));
                    nt.setHoTen(rs.getString("hoTen"));
                    nt.setGioiTinh(rs.getString("gioiTinh"));
                    nt.setBiDanh(rs.getString("biDanh"));
                    nt.setNgaySinh(rs.getDate("ngaySinh"));
                    nt.setQueQuan(rs.getString("queQuan"));
                    nt.setDanToc(rs.getString("danToc"));
                    nt.setTonGiao(rs.getString("tonGiao"));
                    nt.setNgheNghiep(rs.getString("ngheNghiep"));
                    nt.setMaTamTru(rs.getString("maTamTru"));
                    nt.setNoiTamTru(rs.getString("noiTamTru"));
                    nt.setLyDo(rs.getString("lyDo"));
                    nt.setNgayBatDau(rs.getDate("ngayBatDau"));
                    nt.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                    list.add(nt);
                    }
            }
        }
        return list;
    }

    public NhanKhauTamTru getByMaTamTru(String maTamTru) throws SQLException {
        String sql = "SELECT nk.*, tt.maTamTru, tt.noiTamTru, tt.lyDo, tt.ngayBatDau, tt.ngayKetThuc " +
                     "FROM NhanKhau nk JOIN TamTru tt ON nk.Cccd = tt.cccd WHERE tt.maTamTru = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maTamTru);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NhanKhauTamTru nt = new NhanKhauTamTru();
                    nt.setCccd(rs.getString("Cccd"));
                    nt.setHoTen(rs.getString("hoTen"));
                    nt.setGioiTinh(rs.getString("gioiTinh"));
                    nt.setBiDanh(rs.getString("biDanh"));
                    nt.setNgaySinh(rs.getDate("ngaySinh"));
                    nt.setQueQuan(rs.getString("queQuan"));
                    nt.setDanToc(rs.getString("danToc"));
                    nt.setTonGiao(rs.getString("tonGiao"));
                    nt.setNgheNghiep(rs.getString("ngheNghiep"));
                    nt.setMaTamTru(rs.getString("maTamTru"));
                    nt.setNoiTamTru(rs.getString("noiTamTru"));
                    nt.setLyDo(rs.getString("lyDo"));
                    nt.setNgayBatDau(rs.getDate("ngayBatDau"));
                    nt.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                    return nt;
                }
            }
        }
        return null;
    }

    public void update(NhanKhauTamTru nt) throws SQLException {
        // Update NhanKhau
        String sqlNk = "UPDATE NhanKhau SET hoTen = ?, gioiTinh = ?, biDanh = ?, ngaySinh = ?, queQuan = ?, danToc = ?, tonGiao = ?, ngheNghiep = ? WHERE Cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmtNk = conn.prepareStatement(sqlNk)) {
            pstmtNk.setString(1, nt.getHoTen());
            pstmtNk.setString(2, nt.getGioiTinh());
            pstmtNk.setString(3, nt.getBiDanh());
            pstmtNk.setDate(4, new Date(nt.getNgaySinh().getTime()));
            pstmtNk.setString(5, nt.getQueQuan());
            pstmtNk.setString(6, nt.getDanToc());
            pstmtNk.setString(7, nt.getTonGiao());
            pstmtNk.setString(8, nt.getNgheNghiep());
            pstmtNk.setString(9, nt.getCccd());
            pstmtNk.executeUpdate();
        }
        // Update TamTru
        String sqlTt = "UPDATE TamTru SET noiTamTru = ?, lyDo = ?, ngayBatDau = ?, ngayKetThuc = ? WHERE maTamTru = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmtTt = conn.prepareStatement(sqlTt)) {
            pstmtTt.setString(1, nt.getNoiTamTru());
            pstmtTt.setString(2, nt.getLyDo());
            pstmtTt.setDate(3, new Date(nt.getNgayBatDau().getTime()));
            pstmtTt.setDate(4, new Date(nt.getNgayKetThuc().getTime()));
            pstmtTt.setString(5, nt.getMaTamTru());
            pstmtTt.executeUpdate();
        }
    }

    public void delete(String maTamTru) throws SQLException {
        String sql = "DELETE FROM TamTru WHERE maTamTru = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maTamTru);
            pstmt.executeUpdate();
            }
    }
    
    public void deleteByCCCD(String cccd) throws SQLException {
        String sql = "DELETE FROM TamTru WHERE cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cccd);
            pstmt.executeUpdate();
        }
    }
}