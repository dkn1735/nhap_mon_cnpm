// DangKyTamVangController.java
package controller.form;

import dao.TamVangDAO;
import dao.NhanKhauDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DangKyTamVangController implements Initializable {

    @FXML
    private TextField textMaTamVang;
    @FXML
    private ComboBox<String> cbCCCD;
    @FXML
    private TextField textNoiTamVang;
    @FXML
    private TextArea textLyDoTamVang;
    @FXML
    private DatePicker dpNgayBatDau;
    @FXML
    private DatePicker dpNgayKetThuc;

    private TamVangDAO tamVangDAO = new TamVangDAO();
    private NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbCCCD.getItems().addAll(nhanKhauDAO.getAllCccd());
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải danh sách CCCD: " + e.getMessage());
        }
    }    

    @FXML
    private void dangKy(ActionEvent event) {
        if (!validateInputs()) return;

        try {
            model.TamVang tamVang = new model.TamVang();
            tamVang.setMaTamVang(textMaTamVang.getText());
            tamVang.setCccd(cbCCCD.getValue());
            tamVang.setNoiTamVang(textNoiTamVang.getText());
            tamVang.setLyDo(textLyDoTamVang.getText());
            tamVang.setNgayBatDau(Date.from(dpNgayBatDau.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            tamVang.setNgayKetThuc(Date.from(dpNgayKetThuc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            tamVangDAO.insert(tamVang);

            showAlert("Thành công", "Đăng ký tạm vắng thành công!");
            lamMoi(event);
        } catch (SQLException e) {
            showAlert("Lỗi", "Đăng ký tạm vắng thất bại: " + e.getMessage());
        }
    }

    @FXML
    private void lamMoi(ActionEvent event) {
        textMaTamVang.clear();
        cbCCCD.setValue(null);
        textNoiTamVang.clear();
        textLyDoTamVang.clear();
        dpNgayBatDau.setValue(null);
        dpNgayKetThuc.setValue(null);
    }

    private boolean validateInputs() {
        if (textMaTamVang.getText().isEmpty() || textMaTamVang.getText().length() != 6) {
            showAlert("Lỗi", "Mã tạm vắng phải là 6 ký tự!");
            return false;
        }
        if (cbCCCD.getValue() == null) {
            showAlert("Lỗi", "Vui lòng chọn CCCD!");
            return false;
        }
        try {
            if (tamVangDAO.checkIfExists(cbCCCD.getValue())) {
                showAlert("Lỗi", "CCCD này đã đang tạm vắng!");
                return false;
            }
        } catch (SQLException e) {
            showAlert("Lỗi", "Kiểm tra tạm vắng thất bại: " + e.getMessage());
            return false;
        }
        if (textNoiTamVang.getText().isEmpty()) {
            showAlert("Lỗi", "Nơi tạm vắng không được để trống!");
            return false;
        }
        if (textLyDoTamVang.getText().isEmpty()) {
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