// TamVang.java
package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;
import java.util.Objects;

public class TamVang {

    private final StringProperty maTamVang = new SimpleStringProperty();
    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty noiTamVang = new SimpleStringProperty();
    private final StringProperty lyDo = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayBatDau = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> ngayKetThuc = new SimpleObjectProperty<>();

    // Constructors
    public TamVang() {}

    public TamVang(String maTamVang, String cccd, String noiTamVang, String lyDo, Date ngayBatDau, Date ngayKetThuc) {
        this.maTamVang.set(maTamVang);
        this.cccd.set(cccd);
        this.noiTamVang.set(noiTamVang);
        this.lyDo.set(lyDo);
        this.ngayBatDau.set(ngayBatDau);
        this.ngayKetThuc.set(ngayKetThuc);
    }

    // Getters and Setters
    public String getMaTamVang() {
        return maTamVang.get();
    }

    public void setMaTamVang(String maTamVang) {
        this.maTamVang.set(maTamVang);
    }

    public StringProperty maTamVangProperty() {
        return maTamVang;
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

    public String getNoiTamVang() {
        return noiTamVang.get();
    }

    public void setNoiTamVang(String noiTamVang) {
        this.noiTamVang.set(noiTamVang);
    }

    public StringProperty noiTamVangProperty() {
        return noiTamVang;
    }

    public String getLyDo() {
        return lyDo.get();
    }

    public void setLyDo(String lyDo) {
        this.lyDo.set(lyDo);
    }

    public StringProperty lyDoProperty() {
        return lyDo;
    }

    public Date getNgayBatDau() {
        return ngayBatDau.get();
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau.set(ngayBatDau);
    }

    public ObjectProperty<Date> ngayBatDauProperty() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc.get();
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc.set(ngayKetThuc);
    }

    public ObjectProperty<Date> ngayKetThucProperty() {
        return ngayKetThuc;
    }

    // Override equals and hashCode for composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TamVang tamVang = (TamVang) o;
        return Objects.equals(getMaTamVang(), tamVang.getMaTamVang()) && Objects.equals(getCccd(), tamVang.getCccd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaTamVang(), getCccd());
    }
}