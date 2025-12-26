// TamVangDAO.java
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanKhauTamVang;
import model.TamVang;
import util.JDBCUtil;

public class TamVangDAO {

    public void insert(TamVang tamVang) throws SQLException {
        String sql = "INSERT INTO TamVang (maTamVang, cccd, noiTamVang, lyDo, ngayBatDau, ngayKetThuc) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tamVang.getMaTamVang());
            pstmt.setString(2, tamVang.getCccd());
            pstmt.setString(3, tamVang.getNoiTamVang());
            pstmt.setString(4, tamVang.getLyDo());
            pstmt.setDate(5, new Date(tamVang.getNgayBatDau().getTime()));
            pstmt.setDate(6, new Date(tamVang.getNgayKetThuc().getTime()));
            pstmt.executeUpdate();
        }
    }

    public boolean checkIfExists(String cccd) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TamVang WHERE cccd = ?";
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
    
    public List<NhanKhauTamVang> getAll() throws SQLException {
        List<NhanKhauTamVang> list = new ArrayList<>();
        String sql = "SELECT nk.*, tv.maTamVang, tv.noiTamVang, tv.lyDo, tv.ngayBatDau, tv.ngayKetThuc " +
                     "FROM NhanKhau nk JOIN TamVang tv ON nk.Cccd = tv.cccd";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NhanKhauTamVang nt = new NhanKhauTamVang();
                nt.setCccd(rs.getString("Cccd"));
                nt.setHoTen(rs.getString("hoTen"));
                nt.setGioiTinh(rs.getString("gioiTinh"));
                nt.setBiDanh(rs.getString("biDanh"));
                nt.setNgaySinh(rs.getDate("ngaySinh"));
                nt.setQueQuan(rs.getString("queQuan"));
                nt.setDanToc(rs.getString("danToc"));
                nt.setTonGiao(rs.getString("tonGiao"));
                nt.setNgheNghiep(rs.getString("ngheNghiep"));
                nt.setMaTamVang(rs.getString("maTamVang"));
                nt.setNoiTamVang(rs.getString("noiTamVang"));
                nt.setLyDo(rs.getString("lyDo"));
                nt.setNgayBatDau(rs.getDate("ngayBatDau"));
                nt.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                list.add(nt);
            }
        }
        return list;
    }
    
    public List<NhanKhauTamVang> search(String keyword) throws SQLException {
        List<NhanKhauTamVang> list = new ArrayList<>();
        String sql = "SELECT nk.*, tv.maTamVang, tv.noiTamVang, tv.lyDo, tv.ngayBatDau, tv.ngayKetThuc " +
                     "FROM NhanKhau nk JOIN TamVang tv ON nk.Cccd = tv.cccd " +
                     "WHERE nk.Cccd LIKE ? OR nk.hoTen LIKE ? OR tv.maTamVang LIKE ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NhanKhauTamVang nt = new NhanKhauTamVang();
                    nt.setCccd(rs.getString("Cccd"));
                    nt.setHoTen(rs.getString("hoTen"));
                    nt.setGioiTinh(rs.getString("gioiTinh"));
                    nt.setBiDanh(rs.getString("biDanh"));
                    nt.setNgaySinh(rs.getDate("ngaySinh"));
                    nt.setQueQuan(rs.getString("queQuan"));
                    nt.setDanToc(rs.getString("danToc"));
                    nt.setTonGiao(rs.getString("tonGiao"));
                    nt.setNgheNghiep(rs.getString("ngheNghiep"));
                    nt.setMaTamVang(rs.getString("maTamVang"));
                    nt.setNoiTamVang(rs.getString("noiTamVang"));
                    nt.setLyDo(rs.getString("lyDo"));
                    nt.setNgayBatDau(rs.getDate("ngayBatDau"));
                    nt.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                    list.add(nt);
                    }
            }
        }
        return list;
    }

    public NhanKhauTamVang getByMaTamVang(String maTamVang) throws SQLException {
        String sql = "SELECT nk.*, tv.maTamVang, tv.noiTamVang, tv.lyDo, tv.ngayBatDau, tv.ngayKetThuc " +
                     "FROM NhanKhau nk JOIN TamVang tv ON nk.Cccd = tv.cccd WHERE tv.maTamVang = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maTamVang);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NhanKhauTamVang nt = new NhanKhauTamVang();
                    nt.setCccd(rs.getString("Cccd"));
                    nt.setHoTen(rs.getString("hoTen"));
                    nt.setGioiTinh(rs.getString("gioiTinh"));
                    nt.setBiDanh(rs.getString("biDanh"));
                    nt.setNgaySinh(rs.getDate("ngaySinh"));
                    nt.setQueQuan(rs.getString("queQuan"));
                    nt.setDanToc(rs.getString("danToc"));
                    nt.setTonGiao(rs.getString("tonGiao"));
                    nt.setNgheNghiep(rs.getString("ngheNghiep"));
                    nt.setMaTamVang(rs.getString("maTamVang"));
                    nt.setNoiTamVang(rs.getString("noiTamVang"));
                    nt.setLyDo(rs.getString("lyDo"));
                    nt.setNgayBatDau(rs.getDate("ngayBatDau"));
                    nt.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                    return nt;
                    }
            }
        }
        return null;
    }

    public void update(NhanKhauTamVang nt) throws SQLException {
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

        // Update TamVang
        String sqlTv = "UPDATE TamVang SET noiTamVang = ?, lyDo = ?, ngayBatDau = ?, ngayKetThuc = ? WHERE maTamVang = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmtTv = conn.prepareStatement(sqlTv)) {
            pstmtTv.setString(1, nt.getNoiTamVang());
            pstmtTv.setString(2, nt.getLyDo());
            pstmtTv.setDate(3, new Date(nt.getNgayBatDau().getTime()));
            pstmtTv.setDate(4, new Date(nt.getNgayKetThuc().getTime()));
            pstmtTv.setString(5, nt.getMaTamVang());
            pstmtTv.executeUpdate();
        }
    }

    public void delete(String maTamVang) throws SQLException {
        String sql = "DELETE FROM TamVang WHERE maTamVang = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maTamVang);
            pstmt.executeUpdate();
        }
    }
}