// ThuPhi.java
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class ThuPhi {

    private final StringProperty maPhi = new SimpleStringProperty();
    private final StringProperty cccd = new SimpleStringProperty();
    private final IntegerProperty soTien = new SimpleIntegerProperty();

    // Constructors
    public ThuPhi() {}

    public ThuPhi(String maPhi, String cccd, int soTien) {
        this.maPhi.set(maPhi);
        this.cccd.set(cccd);
        this.soTien.set(soTien);
    }

    // Getters and Setters
    public String getMaPhi() {
        return maPhi.get();
    }

    public void setMaPhi(String maPhi) {
        this.maPhi.set(maPhi);
    }

    public StringProperty maPhiProperty() {
        return maPhi;
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

    public int getSoTien() {
        return soTien.get();
    }

    public void setSoTien(int soTien) {
        this.soTien.set(soTien);
    }

    public IntegerProperty soTienProperty() {
        return soTien;
    }

    // Override equals and hashCode for composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThuPhi thuPhi = (ThuPhi) o;
        return Objects.equals(getMaPhi(), thuPhi.getMaPhi()) && Objects.equals(getCccd(), thuPhi.getCccd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaPhi(), getCccd());
    }
}