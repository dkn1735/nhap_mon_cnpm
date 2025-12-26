// HoKhau.java
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HoKhau {

    private final StringProperty soHoKhau = new SimpleStringProperty();
    private final StringProperty diaChi = new SimpleStringProperty();

    // Constructors
    public HoKhau() {}

    public HoKhau(String soHoKhau, String diaChi) {
        this.soHoKhau.set(soHoKhau);
        this.diaChi.set(diaChi);
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

    public String getDiaChi() {
        return diaChi.get();
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public StringProperty diaChiProperty() {
        return diaChi;
    }
}