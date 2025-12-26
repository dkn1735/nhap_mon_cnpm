// ThuongTruWithTen.java (wrapper cho chi tiết hộ)
package model;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThuongTruWithTen {

    private final StringProperty soHoKhau = new SimpleStringProperty();
    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty quanHeChuHo = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayChuyenDen = new SimpleObjectProperty<>();
    private final StringProperty noiTruocKhiChuyenDen = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayChuyenDi = new SimpleObjectProperty<>();
    private final StringProperty noiChuyenDi = new SimpleStringProperty();
    private final StringProperty hoTen = new SimpleStringProperty();

    // Getters and Setters
    public String getSoHoKhau() {
        return soHoKhau.get();
    }

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau.set(soHoKhau);
    }

    public String getCccd() {
        return cccd.get();
    }

    public void setCccd(String cccd) {
        this.cccd.set(cccd);
    }

    public String getQuanHeChuHo() {
        return quanHeChuHo.get();
    }

    public void setQuanHeChuHo(String quanHeChuHo) {
        this.quanHeChuHo.set(quanHeChuHo);
    }

    public Date getNgayChuyenDen() {
        return ngayChuyenDen.get();
    }

    public void setNgayChuyenDen(Date ngayChuyenDen) {
        this.ngayChuyenDen.set(ngayChuyenDen);
    }

    public String getNoiTruocKhiChuyenDen() {
        return noiTruocKhiChuyenDen.get();
    }

    public void setNoiTruocKhiChuyenDen(String noiTruocKhiChuyenDen) {
        this.noiTruocKhiChuyenDen.set(noiTruocKhiChuyenDen);
    }

    public Date getNgayChuyenDi() {
        return ngayChuyenDi.get();
    }

    public void setNgayChuyenDi(Date ngayChuyenDi) {
        this.ngayChuyenDi.set(ngayChuyenDi);
    }

    public String getNoiChuyenDi() {
        return noiChuyenDi.get();
    }

    public void setNoiChuyenDi(String noiChuyenDi) {
        this.noiChuyenDi.set(noiChuyenDi);
    }

    public String getHoTen() {
        return hoTen.get();
    }

    public void setHoTen(String hoTen) {
        this.hoTen.set(hoTen);
    }

    public StringProperty hoTenProperty() {
        return hoTen;
    }

    public StringProperty quanHeChuHoProperty() {
        return quanHeChuHo;
    }
}