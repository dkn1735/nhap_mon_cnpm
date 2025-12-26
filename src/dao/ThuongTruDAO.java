// ThuongTruDAO.java
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanKhauThuongTru;
import model.ThuongTru;
import model.ThuongTruWithTen;
import util.JDBCUtil;

public class ThuongTruDAO {

    public static boolean checkExistChuHo(String soHoKhau) throws SQLException {
        String sql = """
        SELECT COUNT(*)
        FROM ThuongTru
        WHERE soHoKhau = ?
          AND QuanHeChuHo = N'Chủ hộ'
        """;
        
        int numChuHo = 0;
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    numChuHo = rs.getInt(1); // số lượng chủ hộ
                }
            }
        }
        return numChuHo == 1;
    }

    public void insert(ThuongTru thuongTru) throws SQLException {
        String sql = "INSERT INTO ThuongTru (soHoKhau, cccd, QuanHeChuHo, ngayChuyenDen, noiTruocKhiChuyenDen, ngayChuyenDi, noiChuyenDi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, thuongTru.getSoHoKhau());
            pstmt.setString(2, thuongTru.getCccd());
            pstmt.setString(3, thuongTru.getQuanHeChuHo());
            pstmt.setDate(4, new Date(thuongTru.getNgayChuyenDen().getTime()));
            pstmt.setString(5, thuongTru.getNoiTruocKhiChuyenDen());
            if (thuongTru.getNgayChuyenDi() != null) {
                pstmt.setDate(6, new Date(thuongTru.getNgayChuyenDi().getTime()));
            } else {
                pstmt.setNull(6, java.sql.Types.DATE);
            }
            pstmt.setString(7, thuongTru.getNoiChuyenDi());
            pstmt.executeUpdate();
        }
    }
    
    public void insert(ThuongTruWithTen thuongTru) throws SQLException {
        String sql = "INSERT INTO ThuongTru (soHoKhau, cccd, QuanHeChuHo, ngayChuyenDen, noiTruocKhiChuyenDen, ngayChuyenDi, noiChuyenDi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, thuongTru.getSoHoKhau());
            pstmt.setString(2, thuongTru.getCccd());
            pstmt.setString(3, thuongTru.getQuanHeChuHo());
            pstmt.setDate(4, new Date(thuongTru.getNgayChuyenDen().getTime()));
            pstmt.setString(5, thuongTru.getNoiTruocKhiChuyenDen());
            if (thuongTru.getNgayChuyenDi() != null) {
                pstmt.setDate(6, new Date(thuongTru.getNgayChuyenDi().getTime()));
            } else {
                pstmt.setNull(6, java.sql.Types.DATE);
            }
            pstmt.setString(7, thuongTru.getNoiChuyenDi());
            pstmt.executeUpdate();
        }
    }

    public List<ThuongTru> getBySoHoKhau(String soHoKhau) throws SQLException {
        List<ThuongTru> list = new ArrayList<>();
        String sql = "SELECT * FROM ThuongTru WHERE soHoKhau = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ThuongTru tt = new ThuongTru();
                    tt.setSoHoKhau(rs.getString("soHoKhau"));
                    tt.setCccd(rs.getString("cccd"));
                    tt.setQuanHeChuHo(rs.getString("QuanHeChuHo"));
                    tt.setNgayChuyenDen(rs.getDate("ngayChuyenDen"));
                    tt.setNoiTruocKhiChuyenDen(rs.getString("noiTruocKhiChuyenDen"));
                    tt.setNgayChuyenDi(rs.getDate("ngayChuyenDi"));
                    tt.setNoiChuyenDi(rs.getString("noiChuyenDi"));
                    list.add(tt);
                }
            }
        }
        return list;
    }

    public void update(ThuongTru thuongTru) throws SQLException {
        String sql = "UPDATE ThuongTru SET QuanHeChuHo = ?, ngayChuyenDen = ?, noiTruocKhiChuyenDen = ?, ngayChuyenDi = ?, noiChuyenDi = ? WHERE soHoKhau = ? AND cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, thuongTru.getQuanHeChuHo());
            pstmt.setDate(2, new Date(thuongTru.getNgayChuyenDen().getTime()));
            pstmt.setString(3, thuongTru.getNoiTruocKhiChuyenDen());
            if (thuongTru.getNgayChuyenDi() != null) {
                pstmt.setDate(4, new Date(thuongTru.getNgayChuyenDi().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            pstmt.setString(5, thuongTru.getNoiChuyenDi());
            pstmt.setString(6, thuongTru.getSoHoKhau());
            pstmt.setString(7, thuongTru.getCccd());
            pstmt.executeUpdate();
        }
    }

    public void delete(String soHoKhau, String cccd) throws SQLException {
        String sql = "DELETE FROM ThuongTru WHERE soHoKhau = ? AND cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            pstmt.setString(2, cccd);
            pstmt.executeUpdate();
        }
    }
    
    public List<ThuongTruWithTen> getBySoHoKhauWithTen(String soHoKhau) throws SQLException {
        List<ThuongTruWithTen> list = new ArrayList<>();
        String sql = "SELECT tt.*, nk.hoTen " +
                     "FROM ThuongTru tt " +
                     "JOIN NhanKhau nk ON tt.cccd = nk.Cccd " +
                     "WHERE tt.soHoKhau = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, soHoKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ThuongTruWithTen ttWithTen = new ThuongTruWithTen();
                    ttWithTen.setSoHoKhau(rs.getString("soHoKhau"));
                    ttWithTen.setCccd(rs.getString("cccd"));
                    ttWithTen.setQuanHeChuHo(rs.getString("QuanHeChuHo"));
                    ttWithTen.setNgayChuyenDen(rs.getDate("ngayChuyenDen"));
                    ttWithTen.setNoiTruocKhiChuyenDen(rs.getString("noiTruocKhiChuyenDen"));
                    ttWithTen.setNgayChuyenDi(rs.getDate("ngayChuyenDi"));
                    ttWithTen.setNoiChuyenDi(rs.getString("noiChuyenDi"));
                    ttWithTen.setHoTen(rs.getString("hoTen"));
                    list.add(ttWithTen);
                }
            }
        }
        return list;
    }

    public void updateQuanHeChuHo(String soHoKhau, String cccd, String quanHeChuHo) throws SQLException {
        String sql = "UPDATE ThuongTru SET QuanHeChuHo = ? WHERE soHoKhau = ? AND cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, quanHeChuHo);
            pstmt.setString(2, soHoKhau);
            pstmt.setString(3, cccd);
            pstmt.executeUpdate();
        }
    }
    
    public List<NhanKhauThuongTru> getAll() throws SQLException {
        List<NhanKhauThuongTru> list = new ArrayList<>();
        String sql = "SELECT nk.*, tt.soHoKhau, tt.QuanHeChuHo, tt.ngayChuyenDen, tt.noiTruocKhiChuyenDen, tt.ngayChuyenDi, tt.noiChuyenDi " +
                     "FROM NhanKhau nk JOIN ThuongTru tt ON nk.Cccd = tt.cccd";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NhanKhauThuongTru nt = new NhanKhauThuongTru();
                nt.setCccd(rs.getString("Cccd"));
                nt.setHoTen(rs.getString("hoTen"));
                nt.setGioiTinh(rs.getString("gioiTinh"));
                nt.setBiDanh(rs.getString("biDanh"));
                nt.setNgaySinh(rs.getDate("ngaySinh"));
                nt.setQueQuan(rs.getString("queQuan"));
                nt.setDanToc(rs.getString("danToc"));
                nt.setTonGiao(rs.getString("tonGiao"));
                nt.setNgheNghiep(rs.getString("ngheNghiep"));
                nt.setSoHoKhau(rs.getString("soHoKhau"));
                nt.setQuanHeChuHo(rs.getString("QuanHeChuHo"));
                nt.setNgayChuyenDen(rs.getDate("ngayChuyenDen"));
                nt.setNoiTruocKhiChuyenDen(rs.getString("noiTruocKhiChuyenDen"));
                nt.setNgayChuyenDi(rs.getDate("ngayChuyenDi"));
                nt.setNoiChuyenDi(rs.getString("noiChuyenDi"));
                list.add(nt);
            }
        }
        return list;
    }
    
    public List<NhanKhauThuongTru> search(String keyword) throws SQLException {
        List<NhanKhauThuongTru> list = new ArrayList<>();
        String sql = "SELECT nk.*, tt.soHoKhau, tt.QuanHeChuHo, tt.ngayChuyenDen, tt.noiTruocKhiChuyenDen, tt.ngayChuyenDi, tt.noiChuyenDi " +
                     "FROM NhanKhau nk JOIN ThuongTru tt ON nk.Cccd = tt.cccd " +
                     "WHERE nk.Cccd LIKE ? OR nk.hoTen LIKE ? OR tt.soHoKhau LIKE ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NhanKhauThuongTru nt = new NhanKhauThuongTru();
                    nt.setCccd(rs.getString("Cccd"));
                    nt.setHoTen(rs.getString("hoTen"));
                    nt.setGioiTinh(rs.getString("gioiTinh"));
                    nt.setBiDanh(rs.getString("biDanh"));
                    nt.setNgaySinh(rs.getDate("ngaySinh"));
                    nt.setQueQuan(rs.getString("queQuan"));
                    nt.setDanToc(rs.getString("danToc"));
                    nt.setTonGiao(rs.getString("tonGiao"));
                    nt.setNgheNghiep(rs.getString("ngheNghiep"));
                    nt.setSoHoKhau(rs.getString("soHoKhau"));
                    nt.setQuanHeChuHo(rs.getString("QuanHeChuHo"));
                    nt.setNgayChuyenDen(rs.getDate("ngayChuyenDen"));
                    nt.setNoiTruocKhiChuyenDen(rs.getString("noiTruocKhiChuyenDen"));
                    nt.setNgayChuyenDi(rs.getDate("ngayChuyenDi"));
                    nt.setNoiChuyenDi(rs.getString("noiChuyenDi"));
                    list.add(nt);
                }
            }
        }
        return list;
    }
    
    public NhanKhauThuongTru getByCccd(String cccd) throws SQLException {
        String sql = "SELECT nk.*, tt.soHoKhau, tt.QuanHeChuHo, tt.ngayChuyenDen, tt.noiTruocKhiChuyenDen, tt.ngayChuyenDi, tt.noiChuyenDi " +
                     "FROM NhanKhau nk JOIN ThuongTru tt ON nk.Cccd = tt.cccd WHERE nk.Cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cccd);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NhanKhauThuongTru nt = new NhanKhauThuongTru();
                    nt.setCccd(rs.getString("Cccd"));
                    nt.setHoTen(rs.getString("hoTen"));
                    nt.setGioiTinh(rs.getString("gioiTinh"));
                    nt.setBiDanh(rs.getString("biDanh"));
                    nt.setNgaySinh(rs.getDate("ngaySinh"));
                    nt.setQueQuan(rs.getString("queQuan"));
                    nt.setDanToc(rs.getString("danToc"));
                    nt.setTonGiao(rs.getString("tonGiao"));
                    nt.setNgheNghiep(rs.getString("ngheNghiep"));
//                    nt.setSoHoKhau(rs.getString("soHoKhau"));
                    nt.setQuanHeChuHo(rs.getString("QuanHeChuHo"));
                    nt.setNgayChuyenDen(rs.getDate("ngayChuyenDen"));
                    nt.setNoiTruocKhiChuyenDen(rs.getString("noiTruocKhiChuyenDen"));
                    nt.setNgayChuyenDi(rs.getDate("ngayChuyenDi"));
                    nt.setNoiChuyenDi(rs.getString("noiChuyenDi"));
                    return nt;
                }
            }
        }
        return null;
    }
    
    public void update(NhanKhauThuongTru nt) throws SQLException {
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
        
        // Update ThuongTru
        String sqlTt = "UPDATE ThuongTru SET QuanHeChuHo = ?, ngayChuyenDen = ?, noiTruocKhiChuyenDen = ?, ngayChuyenDi = ?, noiChuyenDi = ?, soHoKhau = ? WHERE cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmtTt = conn.prepareStatement(sqlTt)) {
            pstmtTt.setString(1, nt.getQuanHeChuHo());
            pstmtTt.setDate(2, new Date(nt.getNgayChuyenDen().getTime()));
            pstmtTt.setString(3, nt.getNoiTruocKhiChuyenDen());
            if (nt.getNgayChuyenDi() != null) {
                pstmtTt.setDate(4, new Date(nt.getNgayChuyenDi().getTime()));
            } else {
                pstmtTt.setNull(4, java.sql.Types.DATE);
            }
            pstmtTt.setString(5, nt.getNoiChuyenDi());
            pstmtTt.setString(6, nt.getSoHoKhau());
            pstmtTt.setString(7, nt.getCccd());
            pstmtTt.executeUpdate();
        }
    }

    public void delete(String cccd) throws SQLException {
        String sql = "DELETE FROM ThuongTru WHERE cccd = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cccd);
            pstmt.executeUpdate();
        }
    }
}