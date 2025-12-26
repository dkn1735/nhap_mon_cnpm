package controller.form;

import dao.CanBoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.CanBo;

public class DoiMatKhauController implements Initializable {

    @FXML
    private TextField textMatKhauCu;
    @FXML
    private PasswordField textMatKhauMoi1;
    @FXML
    private PasswordField textMatKhauMoi2;
    @FXML
    private Button btnThoat;
    
    private CanBo canBo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void thoat(ActionEvent event) {
        btnThoat.getScene().getWindow().hide();
    }

    @FXML
    private void doiMatKhau(ActionEvent event) throws SQLException {
        String matKhauCu = textMatKhauCu.getText();
        String matKhauMoi1 = textMatKhauMoi1.getText();
        String matKhauMoi2 = textMatKhauMoi2.getText();
        
        if (matKhauCu.equals("") || matKhauMoi1.equals("") || matKhauMoi2.equals("")) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Yêu cầu điền đầy đủ các thông tin");
        }
        else if (!matKhauCu.equals(canBo.getMatKhau())) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Mật khẩu cũ không chính xác");
        }
        else if (!matKhauMoi1.equals(matKhauMoi2)) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Mật khẩu xác thực không khớp với mật khẩu mới");
        }
        else {
            canBo.setMatKhau(matKhauMoi1);
            if (CanBoDAO.update(canBo)) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Đổi mật khẩu thành công");
                thoat(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật mật khẩu. Vui lòng thử lại.");
            }
        }
    }

    public void setCanBo(CanBo canBo) {
        this.canBo = canBo;
    }
    
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}