package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThuPhiDetails {

    private final StringProperty soHoKhau = new SimpleStringProperty();
    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty hoTen = new SimpleStringProperty();
    private final StringProperty diaChi = new SimpleStringProperty();
    private final IntegerProperty soTien = new SimpleIntegerProperty();

    public String getSoHoKhau() {
        return soHoKhau.get();
    }

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau.set(soHoKhau);
    }

    public StringProperty soHoKhauProperty() {
        return soHoKhau;
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

    public String getDiaChi() {
        return diaChi.get();
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public StringProperty diaChiProperty() {
        return diaChi;
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
    
    public String getCCCD() {
        return cccd.get();
    }
    
    public void setCCCD(String cccd) {
        this.cccd.set(cccd);
    }
    
    public StringProperty cccdProperty() {
        return cccd;
    }
}