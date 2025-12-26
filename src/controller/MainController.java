package controller;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CanBo;

public class MainController implements Initializable {

    @FXML
    private Label arrowGiayTo;
    @FXML
    private VBox giayToMenu;
    private boolean giayToExpanded = false;
    @FXML
    private Pane contentPane;
    @FXML
    private MenuButton mbCanBo;
    private CanBo canBo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadTrangChu(null);
        } catch (IOException ex) {
            System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }    

    @FXML
    private void toggleGiayTo(MouseEvent event) {
        giayToExpanded = !giayToExpanded;

        giayToMenu.setVisible(giayToExpanded);
        giayToMenu.setManaged(giayToExpanded);

        arrowGiayTo.setText(giayToExpanded ? "▼" : "▶");
    }

    private void loadView(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        contentPane.getChildren().setAll(root);
    }
    
    @FXML
    private void loadNhanKhau(ActionEvent event) throws IOException {
        loadView("/view/NhanKhau.fxml");
    }

    @FXML
    private void loadHoKhau(ActionEvent event) throws IOException {
        loadView("/view/HoKhau.fxml");
    }

    @FXML
    private void loadDKThuongTru(ActionEvent event) throws IOException {
        loadView("/view/form/DangKyThuongTru.fxml");
    }

    @FXML
    private void loadDKTamTru(ActionEvent event) throws IOException {
        loadView("/view/form/DangKyTamTru.fxml");
    }

    @FXML
    private void loadDKTamVang(ActionEvent event) throws IOException {
        loadView("/view/form/DangKyTamVang.fxml");
    }

    @FXML
    private void loadPhi(ActionEvent event) throws IOException {
        loadView("/view/Phi.fxml");
    }

    @FXML
    private void loadTrangChu(ActionEvent event) throws IOException {
        loadView("/view/TrangChu.fxml");
    }

    @FXML
    private void doiMatKhau(ActionEvent event) {
    }

    @FXML
    private void dangXuat(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn đăng xuất?");

        if (alert.showAndWait().get() == ButtonType.OK) {           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DangNhap_DangKy.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mbCanBo.getScene().getWindow();
            DangNhap_DangKyController controller = loader.getController();
            controller.setStage(stage);
            stage.setMaximized(false);
            stage.setScene(scene);
            stage.setTitle("Đăng nhập");
        }
    }
    
    public void setCanBo(CanBo canBo) {
        this.canBo = canBo;
        mbCanBo.setText(canBo.getChucVu() + " - " + canBo.getTenCanBo());
    }
}
