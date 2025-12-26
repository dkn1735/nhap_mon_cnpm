package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CanBo;
import util.JDBCUtil;
import static util.JDBCUtil.getConnection;

public class CanBoDAO {

    public static CanBo login(String taiKhoan, String matKhau) {
        String sql = "SELECT * FROM CanBo WHERE taiKhoan = ? AND matKhau = ?";
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, taiKhoan);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CanBo canBo = new CanBo();
                canBo.setTaiKhoan(rs.getString("taiKhoan"));
                canBo.setMatKhau(rs.getString("matKhau"));
                canBo.setTenCanBo(rs.getString("tenCanBo"));
                canBo.setChucVu(rs.getString("chucVu"));
                return canBo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(CanBo canBo) {
        String sql = "INSERT INTO CanBo (taiKhoan, matKhau, tenCanBo, chucVu) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, canBo.getTaiKhoan());
            ps.setString(2, canBo.getMatKhau());
            ps.setString(3, canBo.getTenCanBo());
            ps.setString(4, canBo.getChucVu());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}