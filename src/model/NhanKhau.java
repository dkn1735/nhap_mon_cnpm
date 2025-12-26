package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class NhanKhau {

    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty hoTen = new SimpleStringProperty();
    private final StringProperty gioiTinh = new SimpleStringProperty();
    private final StringProperty biDanh = new SimpleStringProperty();
    private final ObjectProperty<Date> ngaySinh = new SimpleObjectProperty<>();
    private final StringProperty queQuan = new SimpleStringProperty();
    private final StringProperty danToc = new SimpleStringProperty();
    private final StringProperty tonGiao = new SimpleStringProperty();
    private final StringProperty ngheNghiep = new SimpleStringProperty();

    // Constructors
    public NhanKhau() {}

    public NhanKhau(String cccd, String hoTen, String gioiTinh, String biDanh, Date ngaySinh, String queQuan, String danToc, String tonGiao, String ngheNghiep) {
        this.cccd.set(cccd);
        this.hoTen.set(hoTen);
        this.gioiTinh.set(gioiTinh);
        this.biDanh.set(biDanh);
        this.ngaySinh.set(ngaySinh);
        this.queQuan.set(queQuan);
        this.danToc.set(danToc);
        this.tonGiao.set(tonGiao);
        this.ngheNghiep.set(ngheNghiep);
    }

    // Getters and Setters
    public String getCccd() {
        return cccd.get();
    }

    public void setCccd(String cccd) {
        this.cccd.set(cccd);
    }

    public StringProperty cccdProperty() {
        return cccd;
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

    public String getGioiTinh() {
        return gioiTinh.get();
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh.set(gioiTinh);
    }

    public StringProperty gioiTinhProperty() {
        return gioiTinh;
    }

    public String getBiDanh() {
        return biDanh.get();
    }

    public void setBiDanh(String biDanh) {
        this.biDanh.set(biDanh);
    }

    public StringProperty biDanhProperty() {
        return biDanh;
    }

    public Date getNgaySinh() {
        return ngaySinh.get();
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh.set(ngaySinh);
    }

    public ObjectProperty<Date> ngaySinhProperty() {
        return ngaySinh;
    }

    public String getQueQuan() {
        return queQuan.get();
    }

    public void setQueQuan(String queQuan) {
        this.queQuan.set(queQuan);
    }

    public StringProperty queQuanProperty() {
        return queQuan;
    }

    public String getDanToc() {
        return danToc.get();
    }

    public void setDanToc(String danToc) {
        this.danToc.set(danToc);
    }

    public StringProperty danTocProperty() {
        return danToc;
    }

    public String getTonGiao() {
        return tonGiao.get();
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao.set(tonGiao);
    }

    public StringProperty tonGiaoProperty() {
        return tonGiao;
    }

    public String getNgheNghiep() {
        return ngheNghiep.get();
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep.set(ngheNghiep);
    }

    public StringProperty ngheNghiepProperty() {
        return ngheNghiep;
    }
}