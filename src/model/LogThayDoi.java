// LogThayDoi.java
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class LogThayDoi {

    private final IntegerProperty logId = new SimpleIntegerProperty();
    private final StringProperty bang = new SimpleStringProperty();
    private final StringProperty hanhDong = new SimpleStringProperty();
    private final StringProperty duLieuCu = new SimpleStringProperty();
    private final StringProperty duLieuMoi = new SimpleStringProperty();
    private final ObjectProperty<Date> thoiGian = new SimpleObjectProperty<>();
    private final StringProperty nguoiThucHien = new SimpleStringProperty();

    // Constructors
    public LogThayDoi() {}

    public LogThayDoi(int logId, String bang, String hanhDong, String duLieuCu, String duLieuMoi, Date thoiGian, String nguoiThucHien) {
        this.logId.set(logId);
        this.bang.set(bang);
        this.hanhDong.set(hanhDong);
        this.duLieuCu.set(duLieuCu);
        this.duLieuMoi.set(duLieuMoi);
        this.thoiGian.set(thoiGian);
        this.nguoiThucHien.set(nguoiThucHien);
    }

    // Getters and Setters
    public int getLogId() {
        return logId.get();
    }

    public void setLogId(int logId) {
        this.logId.set(logId);
    }

    public IntegerProperty logIdProperty() {
        return logId;
    }

    public String getBang() {
        return bang.get();
    }

    public void setBang(String bang) {
        this.bang.set(bang);
    }

    public StringProperty bangProperty() {
        return bang;
    }

    public String getHanhDong() {
        return hanhDong.get();
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong.set(hanhDong);
    }

    public StringProperty hanhDongProperty() {
        return hanhDong;
    }

    public String getDuLieuCu() {
        return duLieuCu.get();
    }

    public void setDuLieuCu(String duLieuCu) {
        this.duLieuCu.set(duLieuCu);
    }

    public StringProperty duLieuCuProperty() {
        return duLieuCu;
    }

    public String getDuLieuMoi() {
        return duLieuMoi.get();
    }

    public void setDuLieuMoi(String duLieuMoi) {
        this.duLieuMoi.set(duLieuMoi);
    }

    public StringProperty duLieuMoiProperty() {
        return duLieuMoi;
    }

    public Date getThoiGian() {
        return thoiGian.get();
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian.set(thoiGian);
    }

    public ObjectProperty<Date> thoiGianProperty() {
        return thoiGian;
    }

    public String getNguoiThucHien() {
        return nguoiThucHien.get();
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        this.nguoiThucHien.set(nguoiThucHien);
    }

    public StringProperty nguoiThucHienProperty() {
        return nguoiThucHien;
    }
}