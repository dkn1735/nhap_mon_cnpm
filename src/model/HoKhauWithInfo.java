// HoKhauWithInfo.java (wrapper cho list hộ khẩu)
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HoKhauWithInfo {

    private final StringProperty soHoKhau = new SimpleStringProperty();
    private final StringProperty diaChi = new SimpleStringProperty();
    private final StringProperty tenChuHo = new SimpleStringProperty();
    private final IntegerProperty slThanhVien = new SimpleIntegerProperty();

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

    public String getTenChuHo() {
        return tenChuHo.get();
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo.set(tenChuHo);
    }

    public StringProperty tenChuHoProperty() {
        return tenChuHo;
    }

    public int getSlThanhVien() {
        return slThanhVien.get();
    }

    public void setSlThanhVien(int slThanhVien) {
        this.slThanhVien.set(slThanhVien);
    }

    public IntegerProperty slThanhVienProperty() {
        return slThanhVien;
    }
}