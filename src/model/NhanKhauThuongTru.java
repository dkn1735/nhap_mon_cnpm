// NhanKhauThuongTru.java
package model;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NhanKhauThuongTru {

    private final StringProperty cccd = new SimpleStringProperty();
    private final StringProperty hoTen = new SimpleStringProperty();
    private final StringProperty gioiTinh = new SimpleStringProperty();
    private final StringProperty biDanh = new SimpleStringProperty();
    private final ObjectProperty<Date> ngaySinh = new SimpleObjectProperty<>();
    private final StringProperty queQuan = new SimpleStringProperty();
    private final StringProperty danToc = new SimpleStringProperty();
    private final StringProperty tonGiao = new SimpleStringProperty();
    private final StringProperty ngheNghiep = new SimpleStringProperty();
    private final StringProperty soHoKhau = new SimpleStringProperty();
    private final StringProperty quanHeChuHo = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayChuyenDen = new SimpleObjectProperty<>();
    private final StringProperty noiTruocKhiChuyenDen = new SimpleStringProperty();
    private final ObjectProperty<Date> ngayChuyenDi = new SimpleObjectProperty<>();
    private final StringProperty noiChuyenDi = new SimpleStringProperty();

    // Constructors
    public NhanKhauThuongTru() {}

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

    public String getSoHoKhau() {
        return soHoKhau.get();
    }

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau.set(soHoKhau);
    }

    public StringProperty soHoKhauProperty() {
        return soHoKhau;
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
}