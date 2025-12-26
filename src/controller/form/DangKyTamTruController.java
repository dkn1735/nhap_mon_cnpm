// DangKyTamTruController.java
package controller.form;

import dao.NhanKhauDAO;
import dao.TamTruDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DangKyTamTruController implements Initializable {

    @FXML
    private TextField textCCCD;
    @FXML
    private TextField textHoTen;
    @FXML
    private TextField textGioiTinh;
    @FXML
    private TextField textBiDanh;
    @FXML
    private TextField textQueQuan;
    @FXML
    private TextField textDanToc;
    @FXML
    private TextField textTonGiao;
    @FXML
    private TextField textNgheNghiep;
    @FXML
    private DatePicker dpNgaySinh;
    @FXML
    private TextField textMaTamTru;
    @FXML
    private TextField textNoiTamTru;
    @FXML
    private TextArea textLyDo;
    @FXML
    private DatePicker dpNgayBatDau;
    @FXML
    private DatePicker dpNgayKetThuc;

    private NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();
    private TamTruDAO tamTruDAO = new TamTruDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dangKy(ActionEvent event) {
        if (!validateInputs()) return;

        try {
            String cccd = textCCCD.getText();
            boolean nkExists = nhanKhauDAO.checkIfExists(cccd);

            if (!nkExists) {
                model.NhanKhau nhanKhau = new model.NhanKhau();
                nhanKhau.setCccd(cccd);
                nhanKhau.setHoTen(textHoTen.getText());
                nhanKhau.setGioiTinh(textGioiTinh.getText());
                nhanKhau.setBiDanh(textBiDanh.getText());
                nhanKhau.setNgaySinh(Date.from(dpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                nhanKhau.setQueQuan(textQueQuan.getText());
                nhanKhau.setDanToc(textDanToc.getText());
                nhanKhau.setTonGiao(textTonGiao.getText());
                nhanKhau.setNgheNghiep(textNgheNghiep.getText());

                nhanKhauDAO.insert(nhanKhau);
            }

            model.TamTru tamTru = new model.TamTru();
            tamTru.setMaTamTru(textMaTamTru.getText());
            tamTru.setCccd(cccd);
            tamTru.setNoiTamTru(textNoiTamTru.getText());
            tamTru.setLyDo(textLyDo.getText());
            tamTru.setNgayBatDau(Date.from(dpNgayBatDau.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tamTru.setNgayKetThuc(Date.from(dpNgayKetThuc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            tamTruDAO.insert(tamTru);

            showAlert("Thành công", "Đăng ký tạm trú thành công!");
            lamMoi(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Đăng ký tạm trú thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void lamMoi(ActionEvent event) {
        textCCCD.clear();
        textHoTen.clear();
        textGioiTinh.clear();
        textBiDanh.clear();
        textQueQuan.clear();
        textDanToc.clear();
        textTonGiao.clear();
        textNgheNghiep.clear();
        dpNgaySinh.setValue(null);
        textMaTamTru.clear();
        textNoiTamTru.clear();
        textLyDo.clear();
        dpNgayBatDau.setValue(null);
        dpNgayKetThuc.setValue(null);
    }
    
    private boolean validateInputs() {
        String cccd = textCCCD.getText();
        if (cccd.isEmpty() || cccd.length() != 12 || !cccd.matches("\\d+")) {
            showAlert("Lỗi", "CCCD phải là 12 chữ số!");
            return false;
        }
        try {
            if (tamTruDAO.checkIfExists(cccd)) {
                showAlert("Lỗi", "CCCD này đã đang tạm trú!");
                return false;
            }
        } catch (SQLException e) {
            showAlert("Lỗi", "Kiểm tra tạm trú thất bại: " + e.getMessage());
            return false;
        }
        if (textHoTen.getText().isEmpty()) {
            showAlert("Lỗi", "Họ tên không được để trống!");
            return false;
        }
        if (!textGioiTinh.getText().equals("Nam") && !textGioiTinh.getText().equals("Nữ")) {
            showAlert("Lỗi", "Giới tính phải là Nam hoặc Nữ!");
            return false;
        }
        if (dpNgaySinh.getValue() == null || dpNgaySinh.getValue().isAfter(LocalDate.now())) {
            showAlert("Lỗi", "Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (textQueQuan.getText().isEmpty() || textDanToc.getText().isEmpty()) {
            showAlert("Lỗi", "Quê quán và dân tộc không được để trống!");
            return false;
        }
        if (textMaTamTru.getText().isEmpty() || textMaTamTru.getText().length() != 6) {
            showAlert("Lỗi", "Mã tạm trú phải là 6 ký tự!");
            return false;
        }
        if (textNoiTamTru.getText().isEmpty()) {
            showAlert("Lỗi", "Nơi tạm trú không được để trống!");
            return false;
        }
        if (textLyDo.getText().isEmpty()) {
            showAlert("Lỗi", "Lý do không được để trống!");
            return false;
        }
        if (dpNgayBatDau.getValue() == null || dpNgayBatDau.getValue().isAfter(LocalDate.now())) {
            showAlert("Lỗi", "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày hiện tại!");
            return false;
        }
        if (dpNgayKetThuc.getValue() == null || dpNgayKetThuc.getValue().isBefore(dpNgayBatDau.getValue()) || dpNgayKetThuc.getValue().equals(dpNgayBatDau.getValue())) {
            showAlert("Lỗi", "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}