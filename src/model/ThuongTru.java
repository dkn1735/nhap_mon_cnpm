// ThuongTru.java
package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;
import java.util.Objects;

public class ThuongTru {

    private final StringProperty soHoKhau = new SimpleStringProperty();
    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty quanHeChuHo = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayChuyenDen = new SimpleObjectProperty<>();
    private final StringProperty noiTruocKhiChuyenDen = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayChuyenDi = new SimpleObjectProperty<>();
    private final StringProperty noiChuyenDi = new SimpleStringProperty();

    // Constructors
    public ThuongTru() {}

    public ThuongTru(String soHoKhau, String cccd, String quanHeChuHo, Date ngayChuyenDen, String noiTruocKhiChuyenDen, Date ngayChuyenDi, String noiChuyenDi) {
        this.soHoKhau.set(soHoKhau);
        this.cccd.set(cccd);
        this.quanHeChuHo.set(quanHeChuHo);
        this.ngayChuyenDen.set(ngayChuyenDen);
        this.noiTruocKhiChuyenDen.set(noiTruocKhiChuyenDen);
        this.ngayChuyenDi.set(ngayChuyenDi);
        this.noiChuyenDi.set(noiChuyenDi);
    }

    // Getters and Setters
    public String getSoHoKhau() {
        return soHoKhau.get();
    }

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau.set(soHoKhau);
    }

    public StringProperty soHoKhauProperty() {
        return soHoKhau;
    }

    public String getCccd() {
        return cccd.get();
    }

    public void setCccd(String cccd) {
        this.cccd.set(cccd);
    }

    public StringProperty cccdProperty() {
        return cccd;
    }

    public String getQuanHeChuHo() {
        return quanHeChuHo.get();
    }

    public void setQuanHeChuHo(String quanHeChuHo) {
        this.quanHeChuHo.set(quanHeChuHo);
    }

    public StringProperty quanHeChuHoProperty() {
        return quanHeChuHo;
    }

    public Date getNgayChuyenDen() {
        return ngayChuyenDen.get();
    }

    public void setNgayChuyenDen(Date ngayChuyenDen) {
        this.ngayChuyenDen.set(ngayChuyenDen);
    }

    public ObjectProperty<Date> ngayChuyenDenProperty() {
        return ngayChuyenDen;
    }

    public String getNoiTruocKhiChuyenDen() {
        return noiTruocKhiChuyenDen.get();
    }

    public void setNoiTruocKhiChuyenDen(String noiTruocKhiChuyenDen) {
        this.noiTruocKhiChuyenDen.set(noiTruocKhiChuyenDen);
    }

    public StringProperty noiTruocKhiChuyenDenProperty() {
        return noiTruocKhiChuyenDen;
    }

    public Date getNgayChuyenDi() {
        return ngayChuyenDi.get();
    }

    public void setNgayChuyenDi(Date ngayChuyenDi) {
        this.ngayChuyenDi.set(ngayChuyenDi);
    }

    public ObjectProperty<Date> ngayChuyenDiProperty() {
        return ngayChuyenDi;
    }

    public String getNoiChuyenDi() {
        return noiChuyenDi.get();
    }

    public void setNoiChuyenDi(String noiChuyenDi) {
        this.noiChuyenDi.set(noiChuyenDi);
    }

    public StringProperty noiChuyenDiProperty() {
        return noiChuyenDi;
    }

    // Override equals and hashCode for composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThuongTru thuongTru = (ThuongTru) o;
        return Objects.equals(getSoHoKhau(), thuongTru.getSoHoKhau()) && Objects.equals(getCccd(), thuongTru.getCccd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoHoKhau(), getCccd());
    }
}