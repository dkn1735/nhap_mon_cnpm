package controller;

import dao.CanBoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CanBo;

public class DangNhap_DangKyController implements Initializable {

    @FXML
    private BorderPane paneDangNhap;
    @FXML
    private TextField textDNTaiKhoan;
    @FXML
    private PasswordField textDNMatKhau;
    @FXML
    private BorderPane paneDangKy;
    @FXML
    private TextField textDKTaiKhoan;
    @FXML
    private PasswordField textDKMatKhau1;
    @FXML
    private PasswordField textDKMatKhau2;
    @FXML
    private TextField textDKTenCanBo;
    @FXML
    private ComboBox<String> cbChucVu;
    
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ẩn pane đăng ký ban đầu
        paneDangKy.setVisible(false);
        // Populate ComboBox chức vụ (có thể chỉnh sửa tùy theo yêu cầu)
        cbChucVu.getItems().addAll("Tổ trưởng", "Tổ phó", "Kế toán");
    }    

    @FXML
    private void chuyenSangDangKy(ActionEvent event) {
        paneDangNhap.setVisible(false);
        paneDangKy.setVisible(true);
    }

    @FXML
    private void dangNhap(ActionEvent event) throws IOException {
        String taiKhoan = textDNTaiKhoan.getText().trim();
        String matKhau = textDNMatKhau.getText().trim();
        
        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            showAlert(AlertType.ERROR, "Thiếu thông tin", "Vui lòng nhập đầy đủ tài khoản và mật khẩu.");
            return;
        }
        
        CanBo canBo = CanBoDAO.login(taiKhoan, matKhau);
        if (canBo != null) {
            showAlert(AlertType.INFORMATION, "Thành công", "Đăng nhập thành công!");
            // TODO: Chuyển sang màn hình chính (ví dụ: load scene khác)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setCanBo(canBo);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
        } else {
            showAlert(AlertType.ERROR, "Lỗi", "Tài khoản hoặc mật khẩu không đúng.");
        }
    }

    @FXML
    private void quayLai(ActionEvent event) {
        paneDangKy.setVisible(false);
        paneDangNhap.setVisible(true);
        // Xóa các trường đăng ký nếu cần
        textDKTaiKhoan.clear();
        textDKMatKhau1.clear();
        textDKMatKhau2.clear();
        textDKTenCanBo.clear();
        cbChucVu.setValue(null);
    }

    @FXML
    private void dangKy(ActionEvent event) {
        String taiKhoan = textDKTaiKhoan.getText().trim();
        String matKhau1 = textDKMatKhau1.getText().trim();
        String matKhau2 = textDKMatKhau2.getText().trim();
        String tenCanBo = textDKTenCanBo.getText().trim();
        String chucVu = cbChucVu.getValue();
        
        if (taiKhoan.isEmpty() || matKhau1.isEmpty() || matKhau2.isEmpty() || tenCanBo.isEmpty() || chucVu == null) {
            showAlert(AlertType.ERROR, "Thiếu thông tin", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        
        if (!matKhau1.equals(matKhau2)) {
            showAlert(AlertType.ERROR, "Lỗi", "Mật khẩu không khớp.");
            return;
        }
        
        CanBo canBo = new CanBo(taiKhoan, matKhau1, tenCanBo, chucVu);
        if (CanBoDAO.register(canBo)) {
            showAlert(AlertType.INFORMATION, "Thành công", "Đăng ký thành công!");
            quayLai(event); // Quay lại đăng nhập
        } else {
            showAlert(AlertType.ERROR, "Lỗi", "Tài khoản đã tồn tại hoặc xảy ra lỗi.");
        }
    }
    
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}