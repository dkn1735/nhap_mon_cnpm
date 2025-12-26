// Phi.java
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Phi {

    private final StringProperty maPhi = new SimpleStringProperty();
    private final StringProperty tenPhi = new SimpleStringProperty();
    private final StringProperty loaiPhi = new SimpleStringProperty();
    private final IntegerProperty mucThu = new SimpleIntegerProperty();

    // Constructors
    public Phi() {}

    public Phi(String maPhi, String tenPhi, String loaiPhi, int mucThu) {
        this.maPhi.set(maPhi);
        this.tenPhi.set(tenPhi);
        this.loaiPhi.set(loaiPhi);
        this.mucThu.set(mucThu);
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

    public String getTenPhi() {
        return tenPhi.get();
    }

    public void setTenPhi(String tenPhi) {
        this.tenPhi.set(tenPhi);
    }

    public StringProperty tenPhiProperty() {
        return tenPhi;
    }

    public String getLoaiPhi() {
        return loaiPhi.get();
    }

    public void setLoaiPhi(String loaiPhi) {
        this.loaiPhi.set(loaiPhi);
    }

    public StringProperty loaiPhiProperty() {
        return loaiPhi;
    }

    public int getMucThu() {
        return mucThu.get();
    }

    public void setMucThu(int mucThu) {
        this.mucThu.set(mucThu);
    }

    public IntegerProperty mucThuProperty() {
        return mucThu;
    }
}