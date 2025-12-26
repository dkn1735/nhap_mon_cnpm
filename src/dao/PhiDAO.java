package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Phi;
import util.JDBCUtil;

public class PhiDAO {

    public void insert(Phi phi) throws SQLException {
        String sql = "INSERT INTO Phi (MaPhi, TenPhi, LoaiPhi, MucThu) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phi.getMaPhi());
            pstmt.setString(2, phi.getTenPhi());
            pstmt.setString(3, phi.getLoaiPhi());
            pstmt.setInt(4, phi.getMucThu());
            pstmt.executeUpdate();
        }
    }

    public List<Phi> getAll() throws SQLException {
        List<Phi> list = new ArrayList<>();
        String sql = "SELECT * FROM Phi";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Phi p = new Phi();
                p.setMaPhi(rs.getString("MaPhi"));
                p.setTenPhi(rs.getString("TenPhi"));
                p.setLoaiPhi(rs.getString("LoaiPhi"));
                p.setMucThu(rs.getInt("MucThu"));
                list.add(p);
            }
        }
        return list;
    }

    public List<Phi> search(String keyword) throws SQLException {
        List<Phi> list = new ArrayList<>();
        String sql = "SELECT * FROM Phi WHERE MaPhi LIKE ? OR TenPhi LIKE ?";
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Phi p = new Phi();
                    p.setMaPhi(rs.getString("MaPhi"));
                    p.setTenPhi(rs.getString("TenPhi"));
                    p.setLoaiPhi(rs.getString("LoaiPhi"));
                    p.setMucThu(rs.getInt("MucThu"));
                    list.add(p);
                }
            }
        }
        return list;
    }

    public void delete(String selectedMaPhi) throws SQLException {
        String sql = "DELETE FROM ThuPhi WHERE MaPhi = ? " + 
                     "DELETE FROM Phi WHERE MaPhi = ?";
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, selectedMaPhi);
            pstmt.setString(2, selectedMaPhi);
            pstmt.executeUpdate();
        }
    }
}