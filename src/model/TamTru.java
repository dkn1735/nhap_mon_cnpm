// TamTru.java
package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;
import java.util.Objects;

public class TamTru {

    private final StringProperty maTamTru = new SimpleStringProperty();
    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty noiTamTru = new SimpleStringProperty();
    private final StringProperty lyDo = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayBatDau = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> ngayKetThuc = new SimpleObjectProperty<>();

    // Constructors
    public TamTru() {}

    public TamTru(String maTamTru, String cccd, String noiTamTru, String lyDo, Date ngayBatDau, Date ngayKetThuc) {
        this.maTamTru.set(maTamTru);
        this.cccd.set(cccd);
        this.noiTamTru.set(noiTamTru);
        this.lyDo.set(lyDo);
        this.ngayBatDau.set(ngayBatDau);
        this.ngayKetThuc.set(ngayKetThuc);
    }

    // Getters and Setters
    public String getMaTamTru() {
        return maTamTru.get();
    }

    public void setMaTamTru(String maTamTru) {
        this.maTamTru.set(maTamTru);
    }

    public StringProperty maTamTruProperty() {
        return maTamTru;
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

    public String getNoiTamTru() {
        return noiTamTru.get();
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru.set(noiTamTru);
    }

    public StringProperty noiTamTruProperty() {
        return noiTamTru;
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
        TamTru tamTru = (TamTru) o;
        return Objects.equals(getMaTamTru(), tamTru.getMaTamTru()) && Objects.equals(getCccd(), tamTru.getCccd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaTamTru(), getCccd());
    }
}