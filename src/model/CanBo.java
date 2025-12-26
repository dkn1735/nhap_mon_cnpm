// CanBo.java
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CanBo {

    private final StringProperty taiKhoan = new SimpleStringProperty();
    private final StringProperty matKhau = new SimpleStringProperty();
    private final StringProperty tenCanBo = new SimpleStringProperty();
    private final StringProperty chucVu = new SimpleStringProperty();

    // Constructors
    public CanBo() {}

    public CanBo(String taiKhoan, String matKhau, String tenCanBo, String chucVu) {
        this.taiKhoan.set(taiKhoan);
        this.matKhau.set(matKhau);
        this.tenCanBo.set(tenCanBo);
        this.chucVu.set(chucVu);
    }

    // Getters and Setters
    public String getTaiKhoan() {
        return taiKhoan.get();
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan.set(taiKhoan);
    }

    public StringProperty taiKhoanProperty() {
        return taiKhoan;
    }

    public String getMatKhau() {
        return matKhau.get();
    }

    public void setMatKhau(String matKhau) {
        this.matKhau.set(matKhau);
    }

    public StringProperty matKhauProperty() {
        return matKhau;
    }

    public String getTenCanBo() {
        return tenCanBo.get();
    }

    public void setTenCanBo(String tenCanBo) {
        this.tenCanBo.set(tenCanBo);
    }

    public StringProperty tenCanBoProperty() {
        return tenCanBo;
    }

    public String getChucVu() {
        return chucVu.get();
    }

    public void setChucVu(String chucVu) {
        this.chucVu.set(chucVu);
    }

    public StringProperty chucVuProperty() {
        return chucVu;
    }
}